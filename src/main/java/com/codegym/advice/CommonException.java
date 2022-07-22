package com.codegym.advice;

import com.codegym.model.dto.response.Response;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CommonException extends RuntimeException{
    private Response response;
    private String message;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"responseCode\":").append("\"").append(this.getResponse().getResponseCode()).append("\"").append(",");
        stringBuilder.append("\"responseMessage\":").append("\"").append(this.getMessage() == null ? this.getResponse().getResponseMessage() : this.getMessage()).append("\"");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
