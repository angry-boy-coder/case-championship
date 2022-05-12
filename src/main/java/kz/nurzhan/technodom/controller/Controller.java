package kz.nurzhan.technodom.controller;

import kz.nurzhan.technodom.dto.OrderDTO;
import kz.nurzhan.technodom.dto.SlotDTO;
import kz.nurzhan.technodom.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("")
public class Controller {

    @Autowired
    private Service service;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/find/available/slot/{id}")
    public ResponseEntity<List<SlotDTO>> getAvailableSlotsByOrderId(
            @PathVariable Long id
    ) {
        OrderDTO order = new OrderDTO();
        try {
            String url = "link" + id;
            ResponseEntity<OrderDTO> response = restTemplate.getForEntity(url, OrderDTO.class);
            order = response.getBody();
        } catch (Exception e) {
            System.out.println(e);
        }
        List<SlotDTO> listOfSlots = service.getAvailableSlotsByOrderDTO(order);
        return new ResponseEntity<>(listOfSlots, HttpStatus.OK);
    }

    @PostMapping(value = "/find/available/slot")
    public ResponseEntity<List<SlotDTO>> getAvailableSlotsByOrderId(@Valid @RequestBody OrderDTO order) {
        List<SlotDTO> listOfSlots = service.getAvailableSlotsByOrderDTO(order);
        return new ResponseEntity<>(listOfSlots, HttpStatus.OK);
    }


}
