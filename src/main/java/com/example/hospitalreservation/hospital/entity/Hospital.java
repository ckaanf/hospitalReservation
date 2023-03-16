package com.example.hospitalreservation.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String yadmNm;
    @Column
    private String addr;
    @Column
    private String telno;
    @Column
    private String dgsbjtCd;

    public Hospital(String yadmNm, String addr, String telno, String dgsbjtCd) {
        this.yadmNm = yadmNm;
        this.addr = addr;
        this.telno = telno;
        this.dgsbjtCd = dgsbjtCd;
    }

    public static Hospital of(String yadmNm, String addr, String telno, String dgsbjtCd) {
        return new Hospital(yadmNm, addr, telno, dgsbjtCd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital hospital)) return false;
        return telno == hospital.telno && Objects.equals(id, hospital.id) && Objects.equals(yadmNm, hospital.yadmNm) && Objects.equals(addr, hospital.addr) && Objects.equals(dgsbjtCd, hospital.dgsbjtCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, yadmNm, addr, telno, dgsbjtCd);
    }
}
