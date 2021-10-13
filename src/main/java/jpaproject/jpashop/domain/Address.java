package jpaproject.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
@Getter
@Setter
public class Address {

    private String street;
    private String detailAddress;
    private String zipcode;

    public Address(String street, String detailAddress, String zipcode) {
        this.street = street;
        this.detailAddress = detailAddress;
        this.zipcode = zipcode;
    }
}
