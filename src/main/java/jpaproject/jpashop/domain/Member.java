package jpaproject.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //멤버 객체
@Getter
@Setter
public class Member {
        @Id @GeneratedValue // 기본키 생성을 데이터베이스에 위임 기본이 auto
        private Long id;

        private String name;

        @Embedded //address 객체를 컬럼으로 사용
        private Address address;

        //maapedby = 주인이 아님 읽기만 가능  조회만 가능
        @OneToMany(mappedBy = "member") //1대다 멤버는 여러개의 주문을 할수있다. Order 에 member와 연결되있다.
        private List<Order> orders = new ArrayList<>();

    @Embedded
    private Address address;

}
