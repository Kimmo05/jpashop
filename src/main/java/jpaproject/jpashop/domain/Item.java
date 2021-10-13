package jpaproject.jpashop.domain;

import jpaproject.jpashop.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "items")
@Entity
@Getter @Setter
public class Item extends BaseTime{

    @Id
    @Column(name= "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품코드

    @Column(nullable = false,length = 50)
    private String itemNm; // 상품명

    @Column(name = "price", nullable = false)
    private int price ; //가격

    @Column(nullable = false)
    private int stockQuantity; // 재고수량
    //카테고리
    private String categoryId;
    @Column(columnDefinition = "TEXT")
    private String itemInfo; //아이템정보


    private String color;
    private String size;

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    @Column(columnDefinition = "TEXT")
    private String imgUrl;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

}