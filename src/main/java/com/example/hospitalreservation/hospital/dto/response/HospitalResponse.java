package com.example.hospitalreservation.hospital.dto.response;

import com.example.hospitalreservation.hospital.dto.HospitalDto;

public record HospitalResponse(
        Long id,
        String yadmNm,
        String addr,
        String telno,
        String dgsbjtCd
) {
    public static HospitalDto of(Long id, String yadmNm, String addr, String telno, String dgsbjtCd) {
        return new HospitalDto(id, yadmNm, addr, telno, dgsbjtCd);
    }

    public static HospitalResponse from(HospitalDto dto){
        return new HospitalResponse(
                dto.id(),
                dto.yadmNm(),
                dto.addr(),
                dto.telno(),
                dto.dgsbjtCd()
        );
    }
}
