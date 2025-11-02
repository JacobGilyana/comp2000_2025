import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.*;

public class WeatherManager {

    private static class WeatherData {
        Map<String, Double> attributes = new ConcurrentHashMap<>();
        long lastUpdated = System.currentTimeMillis();
    }

    private final Map<Point, WeatherData> weatherGrid = new ConcurrentHashMap<>();
    private final Map<String, WeatherEffect> effects = new ConcurrentHashMap<>();
    private final String serverUrl;

    // how long (ms) before a cell's effect fades out
    private static final long DECAY_TIME_MS = 5000;

    public WeatherManager() {
        this("http://13.238.167.130/weather");
    }

    public WeatherManager(String serverUrl) {
        this.serverUrl = serverUrl;

        effects.put("rain", new Rain());
        effects.put("temp", new Heat());
        effects.put("windx", new Wind());
        effects.put("windy", new Wind());
    }

    public void startWeatherStream() {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl))
                .header("Accept", "text/event-stream")
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
            .thenApply(HttpResponse::body)
            .thenAccept(inputStream -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        processLine(line);
                    }
                } catch (IOException e) {
                    System.err.println("Weather stream error: " + e.getMessage());
                }
            });
    }

    private void processLine(String line) {
        if (line == null || line.isBlank()) return;
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 5) return;

        try {
            String attribute = parts[1].toLowerCase();
            int x = Integer.parseInt(parts[2]);
            int y = Integer.parseInt(parts[3]);
            double value = Double.parseDouble(parts[4]);

            Point p = new Point(x, y);
            weatherGrid.computeIfAbsent(p, k -> new WeatherData());
            WeatherData wd = weatherGrid.get(p);
            wd.attributes.put(attribute, value);
            wd.lastUpdated = System.currentTimeMillis();
        } catch (Exception ignored) {}
    }

    public void applyEffects(Grid grid, Graphics g) {
        if (grid == null || grid.cells == null) return;

        int gridWidth = grid.cells.length;
        int gridHeight = grid.cells[0].length;
        int halfW = gridWidth / 2;
        int halfH = gridHeight / 2;

        long now = System.currentTimeMillis();

        weatherGrid.forEach((point, wd) -> {
            // fade out old cells
            if (now - wd.lastUpdated > DECAY_TIME_MS) {
                wd.attributes.clear();
                return;
            }

            int gx = point.x + halfW;
            int gy = point.y + halfH;
            if (gx < 0 || gy < 0 || gx >= gridWidth || gy >= gridHeight) return;

            Cell target = grid.cells[gx][gy];
            if (target == null) return;

            wd.attributes.forEach((attr, val) -> {
                WeatherEffect eff = effects.get(attr);
                if (eff != null) {
                    eff.apply(target, g, val);
                }
            });
        });
    }

    public void clearData() {
        weatherGrid.clear();
    }
}
