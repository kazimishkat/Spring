package com.mishkat.Pharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DivisionDTO {
    private Integer divisionId;
    private String divisionName;
    private Integer countryId;
    private String countryName;
}
