package jpaproject.jpashop.domain;

import jpaproject.jpashop.constant.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //멤버 객체
@Getter
@Setter
public class Member {
        @Id
        @Column(name="member_id")
        @GeneratedValue(strategy = GenerationType.AUTO)
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

        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
        private List<Order> orderList = new ArrayList<>();   //order 와 연관관계



}
