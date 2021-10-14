package jpaproject.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AddressDto {
    private Long id;
    private String detailStreet;

    private String street;

    private String zipcode;


}
