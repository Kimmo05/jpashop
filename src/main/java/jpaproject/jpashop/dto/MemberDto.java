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
        private String loginId;
        private Role role;
        private String phoneNumber;
        private int visitCount;
        private int orderCount;
        private LocalDate createdAt;


        @QueryProjection
        public MemberDto(Long id, String name, String loginId, Role role, String phoneNumber, int visitCount, int orderCount, LocalDate createdAt) {
                this.id = id;
                this.name = name;
                this.loginId = loginId;
                this.role = role;
                this.phoneNumber = phoneNumber;
                this.visitCount = visitCount;
                this.orderCount = orderCount;
                this.createdAt = createdAt;
        }
}// Querydsl을 위한 Dto


