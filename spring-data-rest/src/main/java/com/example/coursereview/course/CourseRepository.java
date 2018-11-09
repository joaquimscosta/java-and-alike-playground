package com.example.coursereview.course;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CourseRepository
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    Page<Course> findByTitleContaining(@Param("title") String title, Pageable page);
}