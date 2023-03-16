package com.example.hospitalreservation.hospital.dto;

import com.example.hospitalreservation.hospital.entity.Hospital;

public record HospitalDto(
        Long id,
        String yadmNm,
        String addr,
        String telno,
        String dgsbjtCd
) {
    public static HospitalDto of (Long id, String yadmNm, String addr,String telno,String dgsbjtCd){
        return new HospitalDto(id, yadmNm, addr, telno, dgsbjtCd);
    }

    public static HospitalDto from(Hospital hospital){
        return new HospitalDto(
                hospital.getId(),
                hospital.getYadmNm(),
                hospital.getAddr(),
                hospital.getTelno(),
                hospital.getDgsbjtCd()
        );
    }
    public Hospital toEntity(){
        return Hospital.of(
                yadmNm,
                addr,
                telno,
                dgsbjtCd
        );
    }
}
