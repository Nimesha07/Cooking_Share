package com.mealsocial.mealsocial.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private Object object;

    private String responseCode;

    private String errorMessage;

}
