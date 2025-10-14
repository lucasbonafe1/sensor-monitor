package com.example.sensor_monitor.services;

import com.example.sensor_monitor.dtos.weather.WeatherReturnDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

@Service
public class WeatherApiExternalService {
    @Value("${weather.api.url}")
    private String baseUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    public HttpClient client;

    @Autowired
    public ObjectMapper mapper;

    public WeatherReturnDTO findForecastByLocation(String location, Integer days) {
        if (days < 1){
            days = 1;
        }

        try {
            String url = baseUrl + "/forecast.json?q=" + URLEncoder.encode(location, StandardCharsets.UTF_8)
                    + "&days=" + days
                    + "&key=" + apiKey;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404)
                throw new NoSuchElementException("Local não encontrado.");

            WeatherReturnDTO dtoMapped = mapper.readValue(response.body(), WeatherReturnDTO.class);

            return dtoMapped;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar dados meteorológicos da localidade " + location, e);
        }
    }
}
