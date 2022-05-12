package kz.nurzhan.technodom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;

    private Long lhaCount;
    private Long notLhaCount;

    private Timestamp date;
    private String city;
}
