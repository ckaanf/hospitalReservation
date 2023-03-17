package com.example.hospitalreservation.reservation.entity;

import com.example.hospitalreservation.audit.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ReasonImage extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String imgUrl;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public ReasonImage(String imgUrl, Reservation reservation){
        this.imgUrl = imgUrl;
        this.reservation = reservation;
    }

    public static ReasonImage of(String imgUrl, Reservation reservation){
        return new ReasonImage(imgUrl, reservation);
    }
}
