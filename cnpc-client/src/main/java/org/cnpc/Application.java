package org.cnpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 服务发现中心 注意application路径问题《controller需要和application在同一目录下,如果不在同一目录下
 * 对应的controller就无法被自动发现》
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
