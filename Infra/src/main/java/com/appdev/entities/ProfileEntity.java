package com.appdev.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "profiles")
public class ProfileEntity extends BaseEntity {
    @Column(length = 20, nullable = false)
    private String role;

    @OneToMany(mappedBy = "profile")
    private List<UserEntity> users;
}
