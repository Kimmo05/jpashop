package jpaproject.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Address {
    private String street;
    private String detailStreet;
    private String zipcode;

    public Address(String detailStreet, String street, String zipcode) {
        this.detailStreet = detailStreet;
        this.street = street;
        this.zipcode = zipcode;
    }
}
