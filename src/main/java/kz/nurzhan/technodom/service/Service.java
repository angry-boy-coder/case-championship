package kz.nurzhan.technodom.service;

import kz.nurzhan.technodom.dto.OrderDTO;
import kz.nurzhan.technodom.dto.SlotDTO;

import java.util.List;

public interface Service {

    List<SlotDTO> getAvailableSlotsByOrderDTO(OrderDTO order);
}
