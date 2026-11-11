package io.github.nguyenquephong13062003.course_service.service;

import io.github.nguyenquephong13062003.course_service.dto.CourseRequest;
import io.github.nguyenquephong13062003.course_service.dto.CourseResponse;
import io.github.nguyenquephong13062003.course_service.entity.Course;
import io.github.nguyenquephong13062003.course_service.exception.ResourceNotFoundException;
import io.github.nguyenquephong13062003.course_service.mapper.CourseMapper;
import io.github.nguyenquephong13062003.course_service.repository.CourseRepository;
import io.github.nguyenquephong13062003.course_service.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final CourseMapper courseMapper;

    @Transactional
    public CourseResponse create(CourseRequest request) {
        Course saved = courseRepository.save(courseMapper.toEntity(request));
        return courseMapper.toResponse(saved);
    }

    public List<CourseResponse> findAll() {
        return courseRepository.findAll(Sort.by("id")).stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    public CourseResponse findById(Long id) {
        return courseMapper.toResponse(getOrThrow(id));
    }

    @Transactional
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = getOrThrow(id);
        courseMapper.updateEntity(course, request);
        return courseMapper.toResponse(course);
    }

    /** Xoá course kèm toàn bộ lesson thuộc course đó. */
    @Transactional
    public void delete(Long id) {
        Course course = getOrThrow(id);
        lessonRepository.deleteAllByCourseId(id);
        courseRepository.delete(course);
    }

    private Course getOrThrow(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.course(id));
    }
}
