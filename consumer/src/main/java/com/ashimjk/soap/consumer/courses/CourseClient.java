package com.ashimjk.soap.consumer.courses;

import com.ashimjk.soap.consumer.wsdl.GetAllCourseDetailRequest;
import com.ashimjk.soap.consumer.wsdl.GetAllCourseDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Slf4j
public class CourseClient extends WebServiceGatewaySupport {

    public GetAllCourseDetailResponse getAllCourse() {
        log.info("Get All Course Detail");

        GetAllCourseDetailRequest request = new GetAllCourseDetailRequest();

        return (GetAllCourseDetailResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://localhost:8081/ws",
                        request,
                        new SoapActionCallback("http://ashimjk.com/courses")
                );
    }

}