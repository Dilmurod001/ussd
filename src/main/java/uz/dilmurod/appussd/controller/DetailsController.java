package uz.dilmurod.appussd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.dilmurod.appussd.payload.ApiResponse;
import uz.dilmurod.appussd.payload.DetailsDTO;
import uz.dilmurod.appussd.payload.ExcelRequestDynamic;
import uz.dilmurod.appussd.service.DetailsService;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/details")
public class DetailsController {
    @Autowired
    DetailsService detailsService;

    @PostMapping("/{id}")
    public HttpEntity<?> addDetails(@PathVariable UUID id, @RequestBody DetailsDTO detailsDTO) {
        ApiResponse response = detailsService.save(id, detailsDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDetails(@PathVariable UUID id, @RequestParam String from, @RequestParam String to) throws ParseException {
        ApiResponse response = detailsService.getDetails(id, from, to);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/downloadExcel")
//    public HttpEntity<?> getExcel() {
//        return detailsService.getExcel();
//    }
}
