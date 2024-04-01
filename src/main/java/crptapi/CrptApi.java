package crptapi;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CrptApi {
    private final TimeUnit timeUnit;
    private final Semaphore semaphore;
    private final ScheduledExecutorService scheduler;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String apiUrl;

    public CrptApi(TimeUnit timeUnit, int requestLimit, String apiUrl) {
        this.timeUnit = timeUnit;
        this.semaphore = new Semaphore(requestLimit);
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.apiUrl = apiUrl;
    }

    public void createDocument(Document document, String signature) throws InterruptedException {
        semaphore.acquire();
        scheduler.scheduleAtFixedRate(semaphore::release, 1, 1, timeUnit);
        try {
            HttpRequest request = buildRequest(document, signature);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                processResponseBody(responseBody);
            } else {
                System.out.println("Ошибка: " + response.statusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processResponseBody(String responseBody) {
        // Обработка запроса
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    private HttpRequest buildRequest(Document document, String signature) {
        String json = convertToJson(document);
        return HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    private String convertToJson(Document document) {
        Gson gson = new Gson();
        return gson.toJson(document);
    }
}