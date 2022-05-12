package kz.nurzhan.technodom.controller;

import kz.nurzhan.technodom.dto.SlotDTO;
import kz.nurzhan.technodom.model.payload.BaseResponse;
import kz.nurzhan.technodom.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("slot")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @GetMapping(value = "/find")
    public ResponseEntity<List<SlotDTO>> getAllProducts(
            Map<String, String> filter
    ) {
        List<SlotDTO> list = slotService.getSlotsByFilter(filter);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = { "/add", "/update" })
    public ResponseEntity<BaseResponse> createOrUpdateProduct(@Valid @RequestBody SlotDTO slotDTO) {
        BaseResponse response = slotService.createOrUpdateSlot(slotDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<BaseResponse> deleteProductById(@PathVariable("id") Long id) throws Exception {
        BaseResponse response = slotService.deleteSlotById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
