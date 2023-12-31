package com.blogpersonnel.GateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;

@SpringBootApplication
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

	@Bean
	public DiscoveryClientRouteDefinitionLocator routeDynamic(ReactiveDiscoveryClient reactiveDiscoveryClient, DiscoveryLocatorProperties discoveryLocatorProperties){
		return  new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient , discoveryLocatorProperties);
	}
}
