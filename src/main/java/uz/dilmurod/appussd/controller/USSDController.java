package uz.dilmurod.appussd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dilmurod.appussd.entity.SimCard;
import uz.dilmurod.appussd.entity.TariffSimcard;
import uz.dilmurod.appussd.payload.ApiResponse;
import uz.dilmurod.appussd.repository.SimcardRepository;
import uz.dilmurod.appussd.repository.TariffSimcardRepository;
import uz.dilmurod.appussd.service.USSDService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ussd")
public class USSDController {
    @Autowired
    SimcardRepository simcardRepository;
    @Autowired
    TariffSimcardRepository tariffSimcardRepository;
    @Autowired
    USSDService ussdService;

    //*Balansni tekshirish
    @PostMapping("/balance/{id}")
    public HttpEntity<?> getBalance(@PathVariable UUID id) {
        ApiResponse balance = ussdService.getBalance(id);
        return ResponseEntity.status(balance.isSuccess()?201:409).body(balance);

    }

    //MB tekshirish ,DAQ tekshirish,
    @PostMapping("/{id}")
    public HttpEntity<?> getLeftOvers(@PathVariable UUID id) {
        ApiResponse mb = ussdService.getMb(id);
        return ResponseEntity.status(mb.isSuccess()?201:409).body(mb);

    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAllCode(@PathVariable UUID id, @RequestParam String code) {
        Optional<SimCard> byId = simcardRepository.findById(id);
        ApiResponse response=new ApiResponse();
        switch (code) {
            case "*100#":
              response=  ussdService.getBalance(id);
                break;
            case "*102#":
               response= ussdService.getMb(id);
                break;
            case "*103#":
                break;

        }return ResponseEntity.ok(response);
    }}
