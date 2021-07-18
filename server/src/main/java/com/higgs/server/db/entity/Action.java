package com.higgs.server.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

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
@Table(name = "ACTION")
public class Action {
    @Id
    @NotNull
    @Column(name = "ACTION_SEQ")
    @SequenceGenerator(name = "SQ_ACTION")
    @GeneratedValue(generator = "SQ_ACTION", strategy = GenerationType.IDENTITY)
    private Long actionSeq;

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "OWNER_NODE_SEQ")
    private Node ownerNode;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "HANDLER")
    private String handler;

    @OneToMany
    @JsonManagedReference
    @JoinColumn(name = "ACTION_SEQ")
    private Collection<ActionParameter> parameters;
}
