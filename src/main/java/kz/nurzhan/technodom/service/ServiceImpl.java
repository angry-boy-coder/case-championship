package kz.nurzhan.technodom.service;

import kz.nurzhan.technodom.dto.OrderDTO;
import kz.nurzhan.technodom.dto.SlotDTO;
import kz.nurzhan.technodom.model.Slot;
import kz.nurzhan.technodom.model.payload.BaseResponse;
import kz.nurzhan.technodom.repository.SlotOrdersRepo;
import kz.nurzhan.technodom.repository.SlotRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements kz.nurzhan.technodom.service.Service, SlotService {

    @Autowired
    private SlotRepo slotRepo;

    @Override
    public List<SlotDTO> getAvailableSlotsByOrderDTO(OrderDTO order) {
        return slotRepo.findAll().stream()
                .filter(it -> it.getCity().equals(order.getCity()))
                .filter(it -> it.getDate().equals(order.getDate()) ||
                            it.getDate().after(order.getDate()))
                .filter(it -> it.getAvailableLhaCount() >= order.getLhaCount())
                .filter(it -> it.getAvailableNotLhaCount() >= order.getNotLhaCount())
                .sorted(new Comparator<Slot>() {
                    @Override
                    public int compare(Slot o1, Slot o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                })
                .map(this::copySlotEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<SlotDTO> getSlotsByFilter(Map<String, String> filter) {
        return slotRepo.findAll().stream()
                .filter(it -> !filter.containsKey("slotName") ^
                            it.getSlotName().equals(filter.get("slotName")))
                .filter(it -> !filter.containsKey("city") ^
                            it.getSlotName().equals(filter.get("city")))
                .filter(it -> !filter.containsKey("from") ^
                            it.getDate().after(Timestamp.valueOf(filter.get("from"))))
                .filter(it -> !filter.containsKey("to") ^
                            it.getDate().before(Timestamp.valueOf(filter.get("to"))))
                .map(this::copySlotEntityToDto)
                .collect(Collectors.toList());
    }

    public List<SlotDTO> findSlotList() {
        return slotRepo.findAll().stream().map(this::copySlotEntityToDto).collect(Collectors.toList());
    }

    public Slot getSlotById(Long slotId) throws Exception {
        return slotRepo.findById(slotId)
                .orElseThrow(() -> new Exception("slot id '" + slotId + "' does not exist !"));
    }

    public BaseResponse createOrUpdateSlot(SlotDTO slotDTO) {
        Slot slot = copySlotDtoToEntity(slotDTO);
        if (slot.getAvailableNotLhaCount() >= 0 && slot.getAvailableLhaCount() >= 0){
            slotRepo.save(slot);
            return new BaseResponse("Slot saved", HttpStatus.CREATED.value());
        }
        return new BaseResponse("Slot not saved", HttpStatus.CREATED.value());
    }

    @Override
    public BaseResponse deleteSlotById(Long slotId) throws Exception {
        if (slotRepo.existsById(slotId)) {
            slotRepo.deleteById(slotId);
        } else {
            throw new Exception("No record found for given id: " + slotId);
        }
        return new BaseResponse("Slot deleted", HttpStatus.OK.value());
    }

    private SlotDTO copySlotEntityToDto(Slot slot) {
        SlotDTO slotDTO = new SlotDTO();
        BeanUtils.copyProperties(slot, slotDTO);
        return slotDTO;
    }

    private Slot copySlotDtoToEntity(SlotDTO slotDTO) {
        Slot slot = new Slot();
        BeanUtils.copyProperties(slotDTO, slot);
        return slot;
    }
}
