package com.higgs.server.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Entity
@Table(name = "NODE")
public class Node {
    @Id
    @NotNull
    @Column(name = "NODE_SEQ")
    @SequenceGenerator(name = "SQ_NODE")
    @GeneratedValue(generator = "SQ_NODE", strategy = GenerationType.IDENTITY)
    private Long nodeSeq;

    @Column(name = "NAME", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "ROOM_SEQ")
    private Room room;

    @OneToMany
    @JsonManagedReference
    @JoinColumn(name = "OWNER_NODE_SEQ")
    private Collection<Action> publicActions;
}
