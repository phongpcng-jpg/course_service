package io.github.nguyenquephong13062003.course_service.mapper;

import io.github.nguyenquephong13062003.course_service.dto.CourseRequest;
import io.github.nguyenquephong13062003.course_service.dto.CourseResponse;
import io.github.nguyenquephong13062003.course_service.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request) {
        return Course.builder()
                .title(request.title())
                .body(request.body())
                .build();
    }

    public void updateEntity(Course course, CourseRequest request) {
        course.setTitle(request.title());
        course.setBody(request.body());
    }

    public CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getBody(),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
}
