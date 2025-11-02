

import java.net.URI;
import java.net.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class WeatherFeed {
    private static final String URL = "http://13.238.167.130/weather";
    private final Consumer<WeatherData> onWeatherUpdate;

    public WeatherFeed(Consumer<WeatherData> callback) {
        this.onWeatherUpdate = callback;
    }

    public void start() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Accept", "text/event-stream")
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
            .thenAccept(response -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.body(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        parseLine(line);
                    }
                } catch (IOException e) {
                    System.err.println("Weather feed error: " + e.getMessage());
                }
            });
    }

    private void parseLine(String line) {
        try {
            String[] parts = line.trim().split(" ");
            if (parts.length != 5) return;
            WeatherData data = new WeatherData(
                Long.parseLong(parts[0]),
                parts[1].toLowerCase(),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                Double.parseDouble(parts[4])
            );
            onWeatherUpdate.accept(data);
        } catch (Exception e) {
            // Ignore malformed lines
        }
    }
}
