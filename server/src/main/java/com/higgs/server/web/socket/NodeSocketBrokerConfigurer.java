package com.higgs.server.web.socket;

import com.higgs.server.config.security.JwtTokenUtil;
import com.higgs.server.config.security.WebSocketAuthInterceptor;
import com.higgs.server.db.repo.UserLoginRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@AllArgsConstructor
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class NodeSocketBrokerConfigurer implements WebSocketMessageBrokerConfigurer {
    private final NodeUserInterceptor nodeUserInterceptor;
    private final WebSocketAuthInterceptor webSocketAuthInterceptor;

    @Override
    public void registerStompEndpoints(@NotNull final StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureClientInboundChannel(@NotNull final ChannelRegistration registration) {
        registration.interceptors(this.nodeUserInterceptor, this.webSocketAuthInterceptor);
    }

    @Override
    public void configureMessageBroker(@NotNull final MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/topic");
    }
}
