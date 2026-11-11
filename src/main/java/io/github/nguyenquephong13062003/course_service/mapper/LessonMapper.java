package io.github.nguyenquephong13062003.course_service.mapper;

import io.github.nguyenquephong13062003.course_service.dto.LessonRequest;
import io.github.nguyenquephong13062003.course_service.dto.LessonResponse;
import io.github.nguyenquephong13062003.course_service.entity.Course;
import io.github.nguyenquephong13062003.course_service.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public Lesson toEntity(LessonRequest request, Course course) {
        return Lesson.builder()
                .course(course)
                .title(request.title())
                .body(request.body())
                .duration(request.duration())
                .url(request.url())
                .build();
    }

    public void updateEntity(Lesson lesson, LessonRequest request) {
        lesson.setTitle(request.title());
        lesson.setBody(request.body());
        lesson.setDuration(request.duration());
        lesson.setUrl(request.url());
    }

    public LessonResponse toResponse(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getCourse().getId(),
                lesson.getTitle(),
                lesson.getBody(),
                lesson.getDuration(),
                lesson.getUrl(),
                lesson.getCreatedAt(),
                lesson.getUpdatedAt()
        );
    }
}
