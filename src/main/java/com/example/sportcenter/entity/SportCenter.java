package com.example.sportcenter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_center")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer centerId;

    @OneToMany(targetEntity = Court.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sport_center_center_id")
    private List<Court> courts;
}
