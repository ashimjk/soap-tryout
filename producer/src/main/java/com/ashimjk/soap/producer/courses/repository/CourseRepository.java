package com.ashimjk.soap.producer.courses.repository;

import com.ashimjk.soap.producer.courses.domain.Course;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CourseRepository {

    private static final Map<Integer, Course> courseById = new HashMap<>();

    @PostConstruct
    public void initData() {
        Course soapCourse = new Course();
        soapCourse.setId(1);
        soapCourse.setName("SOAP");
        soapCourse.setDescription("SOAP API");
        courseById.put(soapCourse.getId(), soapCourse);

        Course serviceCourse = new Course();
        serviceCourse.setId(2);
        serviceCourse.setName("Service");
        serviceCourse.setDescription("Web Service");
        courseById.put(serviceCourse.getId(), serviceCourse);

        Course javaCourse = new Course();
        javaCourse.setId(3);
        javaCourse.setName("Java");
        javaCourse.setDescription("Java-Core");
        courseById.put(javaCourse.getId(), javaCourse);
    }

    public Collection<Course> courses() {
        return courseById.values();
    }

    public Course course(int id) {
        return courseById.get(id);
    }

    public Status remove(int id) {
        return Optional.ofNullable(courseById.remove(id))
                       .map(c -> Status.SUCCESS)
                       .orElse(Status.FAILURE);
    }

    public enum Status {
        SUCCESS, FAILURE
    }

}
