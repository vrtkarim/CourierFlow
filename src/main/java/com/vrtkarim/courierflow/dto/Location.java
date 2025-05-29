package com.vrtkarim.courierflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Location {
    private final double latitude;
    private final double longitude;
}
