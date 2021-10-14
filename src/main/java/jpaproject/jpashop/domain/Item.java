package jpaproject.jpashop.domain;

import jpaproject.jpashop.constant.ItemSellStatus;
import jpaproject.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "item")
@Entity
@Getter @Setter
public class Item extends BaseTime{

    @Id
    @Column(name= "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 상품코드

    @Column(nullable = false,length = 50)
    private String itemNm; // 상품명

    @Column(name = "price", nullable = false)
    private int price ; //가격

    @Column(nullable = false)
    private int stockQuantity; // 재고수량

    @Column(columnDefinition = "TEXT")
    private String itemInfo; //아이템정보

    //카테고리
    private String firstCategory;

    private String secondCategory;

    private String color;

    private String size;


    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    @Column(columnDefinition = "TEXT")
    private String imgUrl;



    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    /*비즈니스 로직*/
    public void plusStockQuantity(int plusQuantity) {
        this.stockQuantity += plusQuantity;
    }// 재고가 증가하는 메소드

    public void minusStockQuantity(int minusQuantity) throws NotEnoughStockException {
        int resultStock = this.stockQuantity - minusQuantity;
        if (resultStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        } else {
            this.stockQuantity = +resultStock;
        }
    }// 재고가 감소하는 메소드
}