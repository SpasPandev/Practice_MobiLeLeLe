package com.example.practice_mobilelele.config;

import com.example.practice_mobilelele.service.OfferService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MobileleleMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final OfferService offerService;

    public MobileleleMethodSecurityConfig(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new MobileleleSecurityExpressionHandler(offerService);
    }
}
