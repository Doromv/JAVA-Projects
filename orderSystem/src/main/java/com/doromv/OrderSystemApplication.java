package com.doromv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Doromv
 * @create 2022-05-22-23:19
 */
@SpringBootApplication
@EnableTransactionManagement
public class OrderSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderSystemApplication.class,args);
    }
}
