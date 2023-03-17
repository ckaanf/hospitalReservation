package com.example.hospitalreservation.reservation.entity;

import com.example.hospitalreservation.audit.AuditingFields;
import com.example.hospitalreservation.hospital.entity.Hospital;
import com.example.hospitalreservation.users.entity.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Reservation extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Setter
    private String telNo;

    @Column
    @Setter
    private String reason;

    @Column
    @Setter
    private LocalDate reservationDate;

    @JsonBackReference
    @ManyToOne(optional = false)
    private Users users;

    @JsonBackReference
    @ManyToOne(optional = false)
    private Hospital hospital;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private final List<ReasonImage> reasonImages = new ArrayList<>();

    public Reservation(String telNo, String reason, LocalDate reservationDate, Users users, Hospital hospital) {
        this.telNo = telNo;
        this.reason = reason;
        this.reservationDate = reservationDate;
        this.users = users;
        this.hospital = hospital;
    }

    public static Reservation of(String telNo, String reason, LocalDate reservationDate, Users users,Hospital hospital){
        return new Reservation(telNo, reason, reservationDate, users, hospital);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(telNo, that.telNo) && Objects.equals(reason, that.reason) && Objects.equals(reservationDate, that.reservationDate) && Objects.equals(users, that.users) && Objects.equals(reasonImages, that.reasonImages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telNo, reason, reservationDate, users, reasonImages);
    }
}
