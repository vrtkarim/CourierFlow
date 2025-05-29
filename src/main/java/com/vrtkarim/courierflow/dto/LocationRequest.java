package com.vrtkarim.courierflow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class LocationRequest {
    Location source;
    List<Location> destinations;
}
