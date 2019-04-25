package com.enstagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@RestController // (1)
//@EnableAutoConfiguration // (2)
@SpringBootApplication
public class App {

//    @RequestMapping("/") // (3)
//    String home() {
//        return "Hello Enstagram"; // (4) 
//    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args); // (5)
        System.out.println("Hello Enstagram");
    }
}