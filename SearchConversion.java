package models;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SearchConversion {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/80356747207e82af798c50c2";

    public ExchangeResponse buscarConversion(String base) {
        URI api = URI.create(BASE_URL + "/latest/" + base);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(api).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ExchangeResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener la conversión.");
        }
    }
}