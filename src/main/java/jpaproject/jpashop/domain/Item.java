package jpaproject.jpashop.domain;

import jpaproject.jpashop.constant.ItemSellStatus;
import jpaproject.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "item")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Item extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String firstCategory; //첫번째 카테고리

    private String secondCategory;  //하위 카테고리

    private String itemName;  //상품 이름

    private int price;  //가격

    @Column(columnDefinition = "TEXT")
    private String itemInfo;  //상품 상세정보

    private String color;  //컬러

    private String fabric;  //원단 //>가구에선 월넛 ,아이보리 이런 느낌 ??

    private String model; //모델

    private String size; //사이즈

    private int stockQuantity; //재고수량

    @Column(columnDefinition = "TEXT")
    private String imgUrl;  //이미지

    private String saleStatus;  //상품 판매 상태

    private Long itemIdx; // 아이템 IDX

    private boolean rep;  //평판??

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Basket> basketList = new ArrayList<>();

    public Item(String firstCategory, String secondCategory,  String itemName, int price, String itemInfo, String color, String fabric, String model, String size, int stockQuantity, String imgUrl, String saleStatus, Long itemIdx, Boolean rep) {
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.itemName = itemName;
        this.price = price;
        this.itemInfo = itemInfo;
        this.color = color;
        this.fabric = fabric;
        this.model = model;
        this.size = size;
        this.stockQuantity = stockQuantity;
        this.imgUrl = imgUrl;
        this.saleStatus = saleStatus;
        this.itemIdx = itemIdx;
        this.rep = rep;
    }

    /*비즈니스 로직*/
    public void plusStockQuantity(int plusQuantity) {
        this.stockQuantity += plusQuantity;
    }// 재고가 증가하는 메소드

    public void minusStockQuantity(int minusQuantity) {
        int resultStock = this.stockQuantity - minusQuantity;
        if (resultStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다."); //재고가 0보다 작으면 뜸
        } else {
            this.stockQuantity = +resultStock;
        }
    }// 재고가 감소하는 메소드
}