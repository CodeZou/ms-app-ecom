package org.sid.billingsevice;

import org.sid.billingsevice.entities.Bill;
import org.sid.billingsevice.entities.ProductItem;
import org.sid.billingsevice.model.Customer;
import org.sid.billingsevice.model.Product;
import org.sid.billingsevice.repository.BillRepository;
import org.sid.billingsevice.repository.ProductItemRepository;
import org.sid.billingsevice.service.CustomerRestClient;
import org.sid.billingsevice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients

public class BillingSeviceApplication {

	public static void main(String[] args) {

		 SpringApplication.run(BillingSeviceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClient customerRestClient,
							ProductRestClient productRestClient){
		return args -> {
			Collection<Product> products= productRestClient.allProducts().getContent();
			Long customerId = 1L;
			Customer customer = customerRestClient.findCustomerById(customerId);
			if (customer == null) throw new RuntimeException("Customer Not Found");
			Bill bill = new Bill();
			bill.setBillDate(new Date());
			bill.setCustomerId(customerId);
			Bill savedBill = billRepository.save(bill);
			products.forEach(product -> {
				ProductItem productItem = new ProductItem();
				productItem.setBill(savedBill);
				productItem.setProductId(product.getId());
				productItem.setQuantity(1+new Random().nextInt(10));
				productItem.setPrice(product.getPrice());
				productItem.setDiscount(Math.random());
				productItemRepository.save(productItem);

			});
		};
	}

}
