package com.enigma.exchangecardapp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;

@Entity
@Table(name = "m_card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@SQLDelete(sql = "UPDATE m_card SET status = false WHERE id=?")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "number_card", unique = true)
    private String numberCard;
    @Column(name = "expired_date")
    private Date expiredDate;
    @Column(name = "balance")
    private Double balance;

    @Column(name = "status")
    private Boolean status;
    @Column(name = "user_id")
    private String user_id;
}
