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
    private String dtStreet;
    private String zipcode;

    public Address(String dtStreet, String street, String zipcode) {
        this.dtStreet = dtStreet;
        this.street = street;
        this.zipcode = zipcode;
    }
}
