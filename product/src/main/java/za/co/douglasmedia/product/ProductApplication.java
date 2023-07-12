package za.co.douglasmedia.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient //add this line to register on eureka
@ComponentScan(basePackages = {
		"za.co.douglasmedia.product.Controllers",
		"za.co.douglasmedia.product.Services",
		"za.co.douglasmedia.product.Repositories",
		"za.co.douglasmedia.product.Entities",
		"za.co.douglasmedia.product.Utils"
})
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		System.out.println("Product Service is running");
	}

}
