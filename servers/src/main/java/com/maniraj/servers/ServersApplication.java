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
//			serverRepository.save(new Server(null, "192.168.1.101", "Database Server", "64 GB", "100 TB", "SERVER_DB", "http://localhost:8080/servers/images/SERVER_DB_UP.png", Status.UP));
//			serverRepository.save(new Server(null, "192.168.1.101", "Database Server", "64 GB", "SERVER_DB", "C:/Users/Maniraj Sivasubbu/Documents/GitHub/manage-servers/servers/src/main/resources/static/images/SERVER_DB_UP.png", Status.UP));
//			serverRepository.save(new Server(null, "192.168.1.160", "Database Server", "64 GB", "SERVER_DB", "C:/Users/Maniraj Sivasubbu/Documents/GitHub/manage-servers/servers/src/main/resources/static/images/SERVER_DB_UP.png", Status.UP));
//			serverRepository.save(new Server(null, "192.168.1.161", "Database Server", "64 GB", "SERVER_DB", "C:/Users/Maniraj Sivasubbu/Documents/GitHub/manage-servers/servers/src/main/resources/static/images/SERVER_DB_DOWN.png", Status.DOWN));
//			serverRepository.save(new Server(null, "192.168.1.162", "Server Stack", "64 GB", "SERVER_STACK", "C:/Users/Maniraj Sivasubbu/Documents/GitHub/manage-servers/servers/src/main/resources/static/images/SERVER_STACK_UP.png", Status.UP));
//			serverRepository.save(new Server(null, "192.168.1.163", "Server Stack", "64 GB", "SERVER_STACK", "C:/Users/Maniraj Sivasubbu/Documents/GitHub/manage-servers/servers/src/main/resources/static/images/SERVER_STACK_DOWN.png", Status.DOWN));
//			serverRepository.save(new Server(null, "192.168.1.164", "Server", "32 GB", "SERVER", "C:/Users/Maniraj Sivasubbu/Documents/GitHub/manage-servers/servers/src/main/resources/static/images/SERVER_UP.png", Status.UP));
//			serverRepository.save(new Server(null, "192.168.1.165", "Server", "32 GB", "SERVER", "C:/Users/Maniraj Sivasubbu/Documents/GitHub/manage-servers/servers/src/main/resources/static/images/SERVER_DOWN.png", Status.DOWN));

			serverRepository.save(new Server(null, "192.168.1.101", "Database Server", "64 GB", "SERVER_DB", "http://localhost:8080/servers/images/SERVER_DB_UP.png", Status.UP));
			serverRepository.save(new Server(null, "192.168.1.160", "Database Server", "64 GB", "SERVER_DB", "http://localhost:8080/servers/images/SERVER_DB_UP.png", Status.UP));
			serverRepository.save(new Server(null, "192.168.1.161", "Database Server", "64 GB", "SERVER_DB", "http://localhost:8080/servers/images/SERVER_DB_DOWN.png", Status.DOWN));
			serverRepository.save(new Server(null, "192.168.1.162", "Server Stack", "64 GB", "SERVER_STACK", "http://localhost:8080/servers/images/SERVER_STACK_UP.png", Status.UP));
			serverRepository.save(new Server(null, "192.168.1.163", "Server Stack", "64 GB", "SERVER_STACK", "http://localhost:8080/servers/images/SERVER_STACK_DOWN.png", Status.DOWN));
			serverRepository.save(new Server(null, "192.168.1.164", "Server", "32 GB", "SERVER", "http://localhost:8080/servers/images/SERVER_UP.png", Status.UP));
			serverRepository.save(new Server(null, "192.168.1.165", "Server", "32 GB", "SERVER", "http://localhost:8080/servers/images/SERVER_DOWN.png", Status.DOWN));
		};
	}
//"C:/Users/Maniraj Sivasubbu/Documents/GitHub/manage-servers/servers/src/main/resources/static/images/SERVER_UP.png"

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
