package com.dsecurity.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class ResponseMessage {
    private String status;
    private String message;
    private Object data;


}
