package com.fdsai.realtime.fraudwatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;

@Configuration
public class OpenAIConfig {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    /**
     * Instantiate the OpenAI API client using the fluent builder.
     * All other settings (baseUrl, paths, HTTP clients, error handling)
     * use Spring AI defaults.
     */
    @Bean
    public OpenAiApi openAiApi() {
        return OpenAiApi
                .builder()
                .apiKey(apiKey)
                .build();
    }
    /**
     * Create an EmbeddingModel backed by the OpenAI embeddings endpoint.
     */
    @Bean
    public EmbeddingModel embeddingModel(OpenAiApi openAiApi) {
        return new OpenAiEmbeddingModel(openAiApi);
    }
}
