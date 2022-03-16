package com.saimon.tuBank.exceptions;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
    private Date time;
    private String message;
    private String details;
}