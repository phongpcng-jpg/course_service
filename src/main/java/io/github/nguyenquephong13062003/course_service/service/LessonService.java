package io.github.nguyenquephong13062003.course_service.service;

import io.github.nguyenquephong13062003.course_service.dto.LessonRequest;
import io.github.nguyenquephong13062003.course_service.dto.LessonResponse;
import io.github.nguyenquephong13062003.course_service.entity.Course;
import io.github.nguyenquephong13062003.course_service.entity.Lesson;
import io.github.nguyenquephong13062003.course_service.exception.ResourceNotFoundException;
import io.github.nguyenquephong13062003.course_service.mapper.LessonMapper;
import io.github.nguyenquephong13062003.course_service.repository.CourseRepository;
import io.github.nguyenquephong13062003.course_service.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LessonService {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Transactional
    public LessonResponse create(Long courseId, LessonRequest request) {
        Course course = getCourseOrThrow(courseId);
        Lesson saved = lessonRepository.save(lessonMapper.toEntity(request, course));
        return lessonMapper.toResponse(saved);
    }

    public List<LessonResponse> findAllByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw ResourceNotFoundException.course(courseId);
        }
        return lessonRepository.findAllByCourseIdOrderByIdAsc(courseId).stream()
                .map(lessonMapper::toResponse)
                .toList();
    }

    public LessonResponse findById(Long courseId, Long lessonId) {
        return lessonMapper.toResponse(getLessonOrThrow(courseId, lessonId));
    }

    @Transactional
    public LessonResponse update(Long courseId, Long lessonId, LessonRequest request) {
        Lesson lesson = getLessonOrThrow(courseId, lessonId);
        lessonMapper.updateEntity(lesson, request);
        return lessonMapper.toResponse(lesson);
    }

    @Transactional
    public void delete(Long courseId, Long lessonId) {
        lessonRepository.delete(getLessonOrThrow(courseId, lessonId));
    }

    private Course getCourseOrThrow(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> ResourceNotFoundException.course(courseId));
    }

    private Lesson getLessonOrThrow(Long courseId, Long lessonId) {
        return lessonRepository.findByIdAndCourseId(lessonId, courseId)
                .orElseThrow(() -> ResourceNotFoundException.lesson(courseId, lessonId));
    }
}
