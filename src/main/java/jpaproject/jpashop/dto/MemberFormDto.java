package jpaproject.jpashop.dto;


import jpaproject.jpashop.domain.Address;
import jpaproject.jpashop.domain.Member;
import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFormDto {
    //회원가입 폼
    private Long id;
    @NotEmpty(message = "아이디는 필수 입니다")
    private String loginId;
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;


    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @Length(max=11)
    private String phoneNumber;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }

    @Builder
    public MemberFormDto(Long id,String loginId, String name, String email,
                         String password, String street, String detailStreet,
                         String zipcode, String phoneNumber) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
////회원가입 DTO

}
