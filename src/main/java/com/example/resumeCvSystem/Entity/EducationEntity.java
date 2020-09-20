package com.example.resumeCvSystem.Entity;

import com.example.resumeCvSystem.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "education")
public class EducationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long year;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
