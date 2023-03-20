package com.example.hospitalreservation.hospital.controller;

import com.example.hospitalreservation.hospital.dto.response.HospitalResponse;
import com.example.hospitalreservation.hospital.service.HospitalService;
import com.example.hospitalreservation.pagination.service.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/hospitals")
@Controller
public class HospitalController {

    private final HospitalService hospitalService;
    private final PaginationService paginationService;


    @GetMapping
    public String getHospitalList(
            @RequestParam(required = false) String searchKeyword,
            @PageableDefault(size = 7, direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map) {


        Page<HospitalResponse> hospitals = hospitalService.searchHospitalList(searchKeyword, pageable).map(HospitalResponse::from);
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), hospitals.getTotalPages());

        log.info("barNumber info : {}", barNumber);
        log.info("hospitals info : {}", hospitals.getContent());

        map.addAttribute("hospitals", hospitals);
        map.addAttribute("barNumber", barNumber);

        return "hospital/index";
    }

//    @GetMapping("/{hospital-id}")
//    public String getHospital(
//            @PathVariable("hospital-id") Long hospitalId,
//            ModelMap map) {
//
//        HospitalResponse hospitalDto = HospitalResponse.from(hospitalService.getHospital(hospitalId));
//
//        map.addAttribute("hospital", hospitalDto);
//
//        return "reservation/detail";
//    }

//    @GetMapping("/{hospital-id}")
//    public ResponseEntity getHospital(
//            @PathVariable("hospital-id") Long hospitalId) {
//        HospitalResponse hospital = HospitalResponse.from(hospitalService.getHospital(hospitalId));
//
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(hospital), HttpStatus.OK
//        );
//    }

    @GetMapping("/{hospitalId}")
    public String hospital(
            @PathVariable Long hospitalId,
            ModelMap map){

        HospitalResponse hospital = HospitalResponse.from(hospitalService.getHospital(hospitalId));

        map.addAttribute("hospitalDetail", hospital);

        return "hospital/detail";
    }

    @ResponseBody
    @GetMapping("/api/load")
    public String loadHospitalList() {
        hospitalService.getHospitalList();
        return null;
    }

}

