package jpaproject.jpashop.domain;

import jpaproject.jpashop.constant.Role;
import jpaproject.jpashop.dto.MemberFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //멤버 객체
@Getter
@Setter
public class Member extends BaseTime {
        @Id
        @Column(name="member_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String loginId;

        private String name;

        @Column(unique = true)
        private String email;

        private String password;

        @Embedded
        private Address address;

        private String phoneNumber;

        @Enumerated(EnumType.STRING)
        private Role role;

        private int visitCount; //방문자수

        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
        private List<Order> orderList = new ArrayList<>();   //order 와 연관관계


        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
        private List<DeliveryAddress> deliveryAddressList = new ArrayList<>();
        //배송주소와 연관

        @Builder
        public Member( String loginId, String name, String email,
                      String password, Address address, String phoneNumber
                 ) {
                this.loginId = loginId;
                this.name = name;
                this.email = email;
                this.password = password;
                this.address = address;
                this.phoneNumber = phoneNumber;
        }
}
