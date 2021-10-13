package jpaproject.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpaproject.jpashop.constant.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "deliverys")
@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch =FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}