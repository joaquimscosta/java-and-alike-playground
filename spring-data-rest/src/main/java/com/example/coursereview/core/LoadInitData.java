package com.example.coursereview.core;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.coursereview.course.Course;
import com.example.coursereview.review.Review;
import com.example.coursereview.course.CourseRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class LoadInitData implements ApplicationListener<ContextRefreshedEvent>{

    private CourseRepository courseRepository;

    public LoadInitData(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        courseRepository.saveAll(coursesAbout("Java"));
        courseRepository.saveAll(coursesAbout("C++"));
        courseRepository.saveAll(coursesAbout("Kotlin"));
        courseRepository.saveAll(coursesAbout("Sprig Boot"));
    }

    private List<Course> coursesAbout(final String subject){
        return Arrays.asList("%s for 101", "%s  0 to 100", "Ninja %s").stream().map(title->{
            Course course = new Course();
            course.setTitle(String.format(title, subject));
            course.addReview(reviewFactory());
            return course;
        }).collect(Collectors.toList());
    }

    private Review reviewFactory(){
        Review review = new Review();
        review.setRating(3);
        review.setComment("Blah blah");
        return review;
    }
}