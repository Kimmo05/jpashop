package kr.co.pjshop.dto;


import com.querydsl.core.annotations.QueryProjection;
import kr.co.pjshop.constant.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
        private Long id;
        private String name;
        private String loginId;
        private String email;
        private Role role;
        private String phoneNumber;
        private LocalDateTime regTime;


        @QueryProjection
        public MemberDto(Long id, String name, String loginId,String email, Role role, String phoneNumber,LocalDateTime regTime) {
                this.id = id;
                this.name = name;
                this.loginId = loginId;
                this.email = email;
                this.role = role;
                this.phoneNumber = phoneNumber;
                this.regTime = regTime;
        }
}// Querydsl을 위한 Dto


