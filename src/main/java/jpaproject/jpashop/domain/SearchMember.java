package jpaproject.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMember {
    private String searchCondition;
    private String searchKeyword;
}
