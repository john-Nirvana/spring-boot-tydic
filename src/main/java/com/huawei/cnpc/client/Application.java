package com.huawei.cnpc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 服务发现中心
 * 
 * @author nirvana
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
}
