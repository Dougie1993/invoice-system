package za.co.douglasmedia.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient //add this line to register on eureka
@ComponentScan(basePackages = {
		"za.co.douglasmedia.invoice.Controllers",
		"za.co.douglasmedia.invoice.Entities",
		"za.co.douglasmedia.invoice.Repositories",
		"za.co.douglasmedia.invoice.Services",
		"za.co.douglasmedia.invoice.Utils"
})
public class InvoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceApplication.class, args);
		System.out.println("Invoice Service Running");
	}

}
