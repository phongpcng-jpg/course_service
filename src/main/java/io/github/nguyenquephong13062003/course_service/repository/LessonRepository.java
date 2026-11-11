package io.github.nguyenquephong13062003.course_service.repository;

import io.github.nguyenquephong13062003.course_service.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByCourseIdOrderByIdAsc(Long courseId);

    Optional<Lesson> findByIdAndCourseId(Long id, Long courseId);

    @Modifying
    @Query("delete from Lesson l where l.course.id = :courseId")
    void deleteAllByCourseId(@Param("courseId") Long courseId);
}
