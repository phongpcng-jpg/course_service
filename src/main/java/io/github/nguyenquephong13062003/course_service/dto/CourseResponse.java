package io.github.nguyenquephong13062003.course_service.dto;

import java.time.Instant;

public record CourseResponse(
        Long id,
        String title,
        String body,
        Instant createdAt,
        Instant updatedAt
) {
}
