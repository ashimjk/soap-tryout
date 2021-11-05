package com.ashimjk.soap.producer.courses.endpoint;

import com.ashimjk.courses.*;
import com.ashimjk.soap.producer.courses.constant.CourseConstant;
import com.ashimjk.soap.producer.courses.domain.Course;
import com.ashimjk.soap.producer.courses.exception.CourseNotFoundException;
import com.ashimjk.soap.producer.courses.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Collection;
import java.util.Optional;

@Endpoint
@RequiredArgsConstructor
public class CourseDetailEndpoint {

    private final CourseRepository courseRepository;

    @PayloadRoot(namespace = CourseConstant.NAMESPACE_URL, localPart = "GetAllCourseDetailRequest")
    @ResponsePayload
    public GetAllCourseDetailResponse getAllCourse(
            @RequestPayload GetAllCourseDetailRequest request
    ) {
        Collection<Course> courses = courseRepository.courses();

        GetAllCourseDetailResponse response = new GetAllCourseDetailResponse();
        courses.forEach(course -> response.getCourseDetail().add(mapToCourseDetail(course)));
        return response;
    }

    @PayloadRoot(namespace = CourseConstant.NAMESPACE_URL, localPart = "GetCourseDetailRequest")
    @ResponsePayload
    public GetCourseDetailResponse getCourseDetail(
            @RequestPayload GetCourseDetailRequest request
    ) {
        Course course = Optional
                .ofNullable(courseRepository.course(request.getId()))
                .orElseThrow(() -> new CourseNotFoundException(request.getId()));

        GetCourseDetailResponse response = new GetCourseDetailResponse();
        response.setCourseDetail(mapToCourseDetail(course));
        return response;
    }

    @PayloadRoot(namespace = CourseConstant.NAMESPACE_URL, localPart = "DeleteCourseDetailRequest")
    @ResponsePayload
    public DeleteCourseDetailResponse deleteCourse(@RequestPayload DeleteCourseDetailRequest request) {
        DeleteCourseDetailResponse response = new DeleteCourseDetailResponse();
        response.setStatus(mapToStatus(request));
        return response;
    }

    private CourseDetail mapToCourseDetail(Course course) {
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setId(course.getId());
        courseDetail.setName(course.getName());
        courseDetail.setDescription(course.getDescription());
        return courseDetail;
    }

    private Status mapToStatus(DeleteCourseDetailRequest request) {
        return Status.fromValue(courseRepository.remove(request.getId()).name());
    }

}
