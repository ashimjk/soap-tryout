package com.ashimjk.soap.consumer;

import com.ashimjk.soap.consumer.courses.CourseClient;
import com.ashimjk.soap.consumer.wsdl.GetAllCourseDetailResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoapConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapConsumerApplication.class, args);
    }

    @Bean
    public CommandLineRunner courseLookup(CourseClient client) {
        return args -> {
            GetAllCourseDetailResponse response = client.getAllCourse();

            System.out.println("------------------------------------");
            response.getCourseDetail()
                    .forEach(course -> {
                        System.out.println("ID : " + course.getId());
                        System.out.println("NAME : " + course.getName());
                        System.out.println("DESCRIPTION : " + course.getDescription());
                        System.out.println("------------------------------------");
                    });
        };
    }

}
