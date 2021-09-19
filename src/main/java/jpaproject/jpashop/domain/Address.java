package jpaproject.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //이 객체를 컬럼으로 사용하고싶을때 사용
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
    protected Address() {
    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
