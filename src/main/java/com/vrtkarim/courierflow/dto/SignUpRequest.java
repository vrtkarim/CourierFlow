package com.vrtkarim.courierflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class SignUpRequest {
    private String vehiculeType;
    private byte[] image;
    private String name;
    private String email;
    private String password;
}
