package com.example.hospitalreservation.hospital.service;

import com.example.hospitalreservation.hospital.dto.HospitalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface HospitalService {

    @Transactional(readOnly = true)
    HospitalDto getHospital(Long hospitalId);

    void getHospitalList();

    @Transactional(readOnly = true)
    Page<HospitalDto> searchHospitalList(String searchKeyword, Pageable pageable);
}
