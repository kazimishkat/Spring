package com.mishkat.Pharmacy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DistrictResponseDTO {

    private Integer districtId;
    private String districtName;
    private Integer divisionId;
    private String divisionName;
    private Integer countryId;
    private String countryName;
    private String countryCode;
}
