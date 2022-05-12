package kz.nurzhan.technodom.service;

import kz.nurzhan.technodom.dto.SlotDTO;
import kz.nurzhan.technodom.model.Slot;
import kz.nurzhan.technodom.model.payload.BaseResponse;

import java.util.List;
import java.util.Map;

// Slots CRUD service
public interface SlotService {
    BaseResponse createOrUpdateSlot(SlotDTO slot);
    Slot getSlotById(Long slotId) throws Exception;
    List<SlotDTO> getSlotsByFilter(Map<String, String> filter);
    BaseResponse deleteSlotById(Long slotId) throws Exception;
}
