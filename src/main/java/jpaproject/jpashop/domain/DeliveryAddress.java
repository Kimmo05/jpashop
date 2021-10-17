package jpaproject.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_address_id")
    private Long id;

    private String placeName; // 장소 이름

    private String recipient; // 수령인

    private String zipcode; //우편번호

    private String street;

    private String dtStreet; // 상세주소

    private String addressPhoneNumber;  //받는사람 전화번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //연관관계 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getDeliveryAddressList().add(this);
    }
}
