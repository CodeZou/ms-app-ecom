package org.sid.billingsevice.service;

import org.sid.billingsevice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient  {
    @GetMapping(path = "/customers/{id}")
    public Customer findCustomerById(@PathVariable Long id);
}
