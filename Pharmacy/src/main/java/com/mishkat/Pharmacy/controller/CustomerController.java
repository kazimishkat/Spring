package com.mishkat.Pharmacy.controller;

import com.mishkat.Pharmacy.entity.Customer;
import com.mishkat.Pharmacy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    private ResponseEntity<Map<String,String>> save(@RequestPart(value = "customer") String data, @RequestParam(value = "image") MultipartFile file){
        ObjectMapper objectMapper = new ObjectMapper();
        Customer cu = objectMapper.readValue(data, Customer.class);

        try{
            customerService.save(cu, file);
            Map<String, String> response = new HashMap<>();
            response.put("Message", "User Added Successfully ");

            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch (Exception e){

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Message", "User Add Faild " + e);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getALl(){
        List<Customer> list = customerService.findAll();
        return  ResponseEntity.ok(list);

    }
}
