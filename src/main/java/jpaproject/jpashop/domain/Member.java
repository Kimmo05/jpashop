package jpaproject.jpashop.domain;

import jpaproject.jpashop.constant.Role;
import jpaproject.jpashop.dto.MemberFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //멤버 객체
@Getter
@Setter
@NoArgsConstructor //파라미터가없는 기본 생성자를 생성해줌
public class Member extends BaseTime {
        @Id
        @Column(name="member_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String loginId; //로그인 아이디

        private String name; //이름


        private String email;  // 필수항목

        private String password;

        @Embedded
        private Address address;

        private String phoneNumber;

        @Enumerated(EnumType.STRING)
        private Role role;  // 멤버 등급

        private int visitCount; //방문자수
        private int orderCount; //주문수


        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
        private List<Order> orderList = new ArrayList<>();

        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
        private List<DeliveryAddress> deliveryAddressList = new ArrayList<>();

        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
        private List<Basket> basketList = new ArrayList<>();

        public Member(String name, String loginId, String password) {
                this.name = name;
                this.loginId = loginId;
                this.password = password;
        }

        @Builder
        public Member(Long id, String loginId, String password, String name, String phoneNumber, String email) {
                this.id = id;
                this.loginId = loginId;
                this.password = password;
                this.name = name;
                this.phoneNumber = phoneNumber;
                this.email = email;

        }

}
