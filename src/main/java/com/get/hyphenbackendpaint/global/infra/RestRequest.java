package com.get.hyphenbackendpaint.global.infra;

import com.get.hyphenbackendpaint.global.config.webClient.WebClientConfig;
import com.get.hyphenbackendpaint.global.exception.global.ExternalAPIException;
import com.get.hyphenbackendpaint.global.lib.webflux.dto.response.ValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestRequest {

    private final WebClientConfig webClientConfig;

    public ValidateResponse validate(String url, String token) {
        ResponseEntity<ValidateResponse> response = webClientConfig.webClient().method(HttpMethod.POST)
                .uri(url)
                .header("Authorization", token)
                .retrieve()
                .toEntity(ValidateResponse.class)
                .block();
        if (response == null || response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            throw ExternalAPIException.EXCEPTION;
        }
        return response.getBody();
    }

    public <T> T get(String url, Class<T> responseClass) {
        ResponseEntity<T> response = webClientConfig.webClient().method(HttpMethod.GET)
                .uri(url)
                .retrieve()
                .toEntity(responseClass)
                .block();
        if (response == null || response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            throw ExternalAPIException.EXCEPTION;
        }
        return response.getBody();
    }
}
