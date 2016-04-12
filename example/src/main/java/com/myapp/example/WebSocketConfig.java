// @formatter:off
package com.myapp.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.myapp.controller.ClientWebSocketHandler;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(clientHandler(), "/client");
    }

    @Bean
    public ClientWebSocketHandler clientHandler() {
        return new ClientWebSocketHandler();
    }
}
