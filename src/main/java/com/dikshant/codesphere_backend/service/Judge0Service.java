package com.dikshant.codesphere_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class Judge0Service {

    @Value("${judge0.api.url}")
    private String apiUrl;

    @Value("${judge0.api.key}")
    private String apiKey;

    public Map<String, Object> runCode(String sourceCode, String language) {
        RestTemplate restTemplate = new RestTemplate();

        // Language IDs for Judge0
        int languageId = switch (language.toLowerCase()) {
            case "java" -> 62;
            case "python" -> 71;
            case "cpp" -> 54;
            case "c" -> 50;
            case "javascript" -> 63;
            default -> 71; // fallback to python
        };

        Map<String, Object> body = new HashMap<>();
        body.put("language_id", languageId);
        body.put("source_code", sourceCode);
        body.put("stdin", "");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "judge0-ce.p.rapidapi.com");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                apiUrl + "?base64_encoded=false&wait=true",
                request,
                Map.class
        );

        return response.getBody();
    }
}
