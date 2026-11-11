package io.github.nguyenquephong13062003.course_service.dto;

import java.time.Instant;

public record LessonResponse(
        Long id,
        Long courseId,
        String title,
        String body,
        Integer duration,
        String url,
        Instant createdAt,
        Instant updatedAt
) {
}
