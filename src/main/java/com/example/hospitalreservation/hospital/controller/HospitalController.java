package com.example.hospitalreservation.hospital.controller;

import com.example.hospitalreservation.hospital.repository.HospitalRepository;
import com.example.hospitalreservation.hospital.service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HospitalController {

    private final HospitalRepository hospitalRepository;
    private final HospitalService hospitalService;

    public HospitalController(HospitalRepository hospitalRepository, HospitalService hospitalService) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/api/load")
    public String getHospital(){
        hospitalService.getHospitalList();
        return null;
    }

}
