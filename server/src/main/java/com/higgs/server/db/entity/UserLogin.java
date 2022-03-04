package com.higgs.server.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "USER_LOGIN")
public class UserLogin implements UserDetails {
    @Serial
    private static final long serialVersionUID = 236L;

    @PrePersist
    public void onInsert() {
        this.setCreated(Date.from(OffsetDateTime.now().toInstant()));
    }

    @PostLoad
    public void onLoad() {
        this.setLastLogin(Date.from(OffsetDateTime.now().toInstant()));
    }

    @Id
    @NotNull
    @Column(name = "USER_LOGIN_SEQ")
    @SequenceGenerator(name = "SQ_USER_LOGIN")
    @GeneratedValue(generator = "SQ_USER_LOGIN", strategy = GenerationType.IDENTITY)
    private Long userLoginSeq;

    @NotNull
    private String username;

    @NotNull
    @JsonIgnore
    @ToString.Exclude
    private String password;

    @Column(name = "IS_LOCKED")
    private boolean isLocked;

    @Column(name = "IS_ENABLED")
    private boolean isEnabled;

    @Column(name = "IS_EXPIRED")
    private boolean isExpired;

    @Column(name = "IS_CREDENTIALS_EXPIRED")
    private boolean isCredentialsExpired;

    @Column(name = "AUTHS")
    private String auths;

    @Column(name = "LAST_LOGIN")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastLogin;

    @NotNull
    @Column(name = "CREATED")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.getAuths().split(",")).map(stringAuth -> (GrantedAuthority) () -> stringAuth).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isCredentialsExpired();
    }
}
