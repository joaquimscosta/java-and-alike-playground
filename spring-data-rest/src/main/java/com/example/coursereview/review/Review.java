package com.example.coursereview.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.coursereview.course.Course;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer rating;
    private String comment;
    @ManyToOne
    private Course course;
}