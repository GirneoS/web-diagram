package com.ozhegov.laba4_backend.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Entity
@Table(name = "points")
@Data
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_seq")
    @SequenceGenerator(name = "point_seq", sequenceName = "id_points", allocationSize = 1)
    private long id;
    @Column
    private double x;
    @Column
    private double y;
    @Column
    private double r;
    @Column
    private String result;
    @Column(name = "execution_time")
    private long executionTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    public Point() {

    }
    public Point(double x, double y, double r, User user_id){
        this.x = x;
        this.y = y;
        this.r = r;
        this.user_id = user_id;
    }
}
