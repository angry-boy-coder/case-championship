package kz.nurzhan.technodom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public
class SlotDTO {
    private Long slotId;

    private String slot;
    private Timestamp date;
    private String city;

    private Long lhaCount;
    private Long notLhaCount;

    private Long availableLhaCount;
    private Long availableNotLhaCount;
}
