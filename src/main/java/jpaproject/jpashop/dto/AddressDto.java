package jpaproject.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AddressDto {
    private Long id;

    private String placeName;

    private String recipient;

    private String street;

    private String dtStreet;

    private String zipcode;

    private String addressPhoneNumber;

}
