package crptapi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CrptApi api = new CrptApi(TimeUnit.SECONDS, 1, "https://ismp.crpt.ru/api/v3/lk/documents/create");

        String json = "";
        try (BufferedReader bg = new BufferedReader(new FileReader("src/main/resources/document.json", StandardCharsets.UTF_8))) {
            json = bg.lines()
                    .map(String::trim).map(String::strip)
                    .collect(Collectors.joining(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);

        Gson gson = new Gson();
        Document document = gson.fromJson(json.trim(), Document.class);
        System.out.println(document.toString());

        String signature = "signature";
        api.createDocument(document, signature);

        api.shutdown();
    }
}