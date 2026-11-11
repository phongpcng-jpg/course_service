package io.github.nguyenquephong13062003.course_service.repository;

import io.github.nguyenquephong13062003.course_service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
