package jpaproject.jpashop.dto;


import com.querydsl.core.annotations.QueryProjection;
import jpaproject.jpashop.constant.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

        private Long id;
        private String name;
        private String LoginId;
        private String phoneNumber;
        private LocalDate createdAt;
        private Role role;

        @QueryProjection
        public MemberDto(Long id, String name, String loginId, String phoneNumber, LocalDate createdAt, Role role) {
                this.id = id;
                this.name = name;
                this.LoginId = loginId;
                this.phoneNumber = phoneNumber;
                this.createdAt = createdAt;
                this.role = role;
        }
}
