package com.example.springBoot;

import lombok.RequiredArgsConstructor;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@org.springframework.boot.autoconfigure.SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    @Bean
    public SpringTemplateEngine templateEngine(ApplicationContext ctx) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(ctx);
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
