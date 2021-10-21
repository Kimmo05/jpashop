package jpaproject.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private Long id; //장바구니 id

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;  //멤버랑 다대일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ColumnDefault("1")
    private int basketCount;  //장바구니 카운트

    public void setItem(Item item) {
        this.item = item;
        item.getBasketList().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getBasketList().add(this);
    }
}
