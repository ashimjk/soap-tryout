package com.ashimjk.soap.producer.courses.exception;

import com.ashimjk.soap.producer.courses.constant.CourseConstant;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

// @SoapFault(faultCode = FaultCode.CLIENT)
@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{" + CourseConstant.NAMESPACE_URL + "}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(int id) {
        super("Invalid Course Id : " + id);
    }

}
