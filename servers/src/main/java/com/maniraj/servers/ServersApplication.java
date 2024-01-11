package com.maniraj.servers;

import com.maniraj.servers.enumeration.Status;
import com.maniraj.servers.model.Server;
import com.maniraj.servers.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServersApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			serverRepository.save(new Server(null, "8.8.8.8", "Google Server", "64 GB", "SERVER", "http://localhost:8080/servers/images/SERVER_UP.png", Status.DOWN));
			serverRepository.save(new Server(null, "23.208.145.176", "Microsoft DB Server", "64 GB", "SERVER_DB", "http://localhost:8080/servers/images/SERVER_DB_UP.png", Status.DOWN));
			serverRepository.save(new Server(null, "74.6.160.106", "Yahoo Server", "64 GB", "SERVER_DB", "http://localhost:8080/servers/images/SERVER_DB_UP.png", Status.DOWN));
			serverRepository.save(new Server(null, "157.240.3.35", "Facebook Server", "64 GB", "SERVER_DB", "http://localhost:8080/servers/images/SERVER_DB_DOWN.png", Status.DOWN));
			serverRepository.save(new Server(null, "208.80.153.232", "Wikipedia Stack", "64 GB", "SERVER_STACK", "http://localhost:8080/servers/images/SERVER_STACK_UP.png", Status.DOWN));
			serverRepository.save(new Server(null, "104.244.42.193", "Twitter Stack", "64 GB", "SERVER_STACK", "http://localhost:8080/servers/images/SERVER_STACK_DOWN.png", Status.DOWN));
			serverRepository.save(new Server(null, "192.168.1.164", "Server", "32 GB", "SERVER", "http://localhost:8080/servers/images/SERVER_UP.png", Status.UP));
			serverRepository.save(new Server(null, "192.168.1.165", "Server", "32 GB", "SERVER", "http://localhost:8080/servers/images/SERVER_DOWN.png", Status.UP));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
				"Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control_Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
				"Jwt-Token", "Authorization", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
