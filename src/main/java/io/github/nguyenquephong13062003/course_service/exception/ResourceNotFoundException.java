package io.github.nguyenquephong13062003.course_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException course(Long id) {
        return new ResourceNotFoundException("Course not found with id " + id);
    }

    public static ResourceNotFoundException lesson(Long courseId, Long lessonId) {
        return new ResourceNotFoundException(
                "Lesson " + lessonId + " not found in course " + courseId);
    }
}
