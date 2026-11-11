package io.github.nguyenquephong13062003.course_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record LessonRequest(
        @NotBlank(message = "title must not be blank")
        @Size(max = 255, message = "title must be at most 255 characters")
        String title,

        String body,

        @NotNull(message = "duration is required (minutes)")
        @Positive(message = "duration must be greater than 0")
        Integer duration,

        @Size(max = 2048, message = "url must be at most 2048 characters")
        @URL(message = "url is not valid")
        String url
) {
}
