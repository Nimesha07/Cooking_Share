package com.mealsocial.mealsocial.service.impl;

import com.mealsocial.mealsocial.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {


    public ResponseDto successEvent(Object object){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode("200");
        responseDto.setErrorMessage("Success");
        responseDto.setObject(object);
        return responseDto;
    }

    public ResponseDto validationEvent(String message){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode("400");
        responseDto.setErrorMessage(message);
        responseDto.setObject(null);
        return responseDto;
    }

}
