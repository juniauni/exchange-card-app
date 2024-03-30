package com.enigma.exchangecardapp.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trx_exchange")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "old_card_id")
    @JsonBackReference
    private Card oldCard;

    @ManyToOne
    @JoinColumn(name = "new_card_id")
    @JsonBackReference
    private Card newCard;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "approval_status")
    private Boolean approvalStatus;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
