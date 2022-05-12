package kz.nurzhan.technodom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "slot_orders")
@Getter
@Setter
public class SlotOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long slotId;

    @ManyToOne
    @JoinColumn(name = "slot", insertable = false, updatable = false)
    private Slot slot;

    private Long lhaCount;
    private Long notLhaCount;

    private String city;

}
