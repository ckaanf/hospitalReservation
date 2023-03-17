package com.example.hospitalreservation.hospital.controller;

import com.example.hospitalreservation.hospital.dto.HospitalDto;
import com.example.hospitalreservation.hospital.dto.response.HospitalResponse;
import com.example.hospitalreservation.hospital.repository.HospitalRepository;
import com.example.hospitalreservation.hospital.service.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hospitals")
@Controller
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController( HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String getHospitalList(
            @RequestParam(required = false) String searchKeyword,
            @PageableDefault(size =10 , direction = Sort.Direction.DESC)Pageable pageable,
            ModelMap map
            ){

        Page<HospitalResponse> hospitals = hospitalService.searchHospitalList(searchKeyword,pageable).map(HospitalResponse::from);
        map.addAttribute("hospitalList", hospitals);

        return "hospitals/index";
    }

    @GetMapping("/{hospital-id}")
    public String getHospital(
            @PathVariable("hospital-id") Long hospitalId,
            ModelMap map) {

        HospitalResponse hospitalDto = HospitalResponse.from(hospitalService.getHospital(hospitalId));

        map.addAttribute("hospital", hospitalDto);

        return "hospitals/detail";
    }
    @GetMapping("/api/load")
    public String loadHospitalList(){
        hospitalService.getHospitalList();
        return null;
    }

}
