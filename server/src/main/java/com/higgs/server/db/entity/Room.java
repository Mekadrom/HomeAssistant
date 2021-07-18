package com.higgs.server.db.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @NotNull
    @Column(name = "ROOM_SEQ")
    @SequenceGenerator(name = "SQ_ROOM")
    @GeneratedValue(generator = "SQ_ROOM", strategy = GenerationType.IDENTITY)
    private Long roomSeq;

    @NotNull
    @Column(name = "NAME", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_SEQ")
    private Account account;
}
