package kz.nurzhan.technodom.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "slot")
@TypeDef(
        name = "SlotOrders",
        typeClass = SlotOrders.class
)
@Getter
@Setter()
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp date;
    private String slotName;
    private String city;

    private Long lhaCount;
    private Long notLhaCount;

    @OneToMany
    @JoinColumn(name = "slotId")
    private List<SlotOrders> slotOrders;

    public Long getAvailableLhaCount() {
        long allLhaCount = slotOrders.stream().mapToLong(SlotOrders::getLhaCount).sum();
        return lhaCount - allLhaCount;
    }

    public Long getAvailableNotLhaCount() {
        long allNotLhaCount = slotOrders.stream().mapToLong(SlotOrders::getNotLhaCount).sum();
        return notLhaCount - allNotLhaCount;
    }
}
