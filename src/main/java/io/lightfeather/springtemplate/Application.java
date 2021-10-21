package io.lightfeather.springtemplate;

import java.util.*;
import org.springframework.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

  @Bean
  public WebClient.Builder webClientBuilder() {
      return WebClient.builder();
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
