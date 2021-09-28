package jpaproject.jpashop.domain;

import jpaproject.jpashop.constant.Role;
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
        @Id
        @Column(name="member_id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;

        @Column(unique = true)
        private String email;

        private String password;

        private String address;

        @Enumerated(EnumType.STRING)
        private Role role;

       /* public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
                Member member = new Member();
                member.setName(memberFormDto.getName());
                member.setEmail(memberFormDto.getEmail());
                member.setAddress(memberFormDto.getAddress());
                String password = passwordEncoder.encode(memberFormDto.getPassword());
                member.setPassword(password);
                member.setRole(Role.ADMIN);
                return member;
        }*/

}
