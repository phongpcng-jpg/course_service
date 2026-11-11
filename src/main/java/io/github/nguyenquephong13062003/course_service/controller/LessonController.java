package io.github.nguyenquephong13062003.course_service.controller;

import io.github.nguyenquephong13062003.course_service.dto.LessonRequest;
import io.github.nguyenquephong13062003.course_service.dto.LessonResponse;
import io.github.nguyenquephong13062003.course_service.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/courses/{courseId}/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponse> create(@PathVariable Long courseId,
                                                 @Valid @RequestBody LessonRequest request) {
        LessonResponse created = lessonService.create(courseId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{lessonId}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public List<LessonResponse> findAll(@PathVariable Long courseId) {
        return lessonService.findAllByCourse(courseId);
    }

    @GetMapping("/{lessonId}")
    public LessonResponse findById(@PathVariable Long courseId, @PathVariable Long lessonId) {
        return lessonService.findById(courseId, lessonId);
    }

    @PutMapping("/{lessonId}")
    public LessonResponse update(@PathVariable Long courseId,
                                 @PathVariable Long lessonId,
                                 @Valid @RequestBody LessonRequest request) {
        return lessonService.update(courseId, lessonId, request);
    }

    @DeleteMapping("/{lessonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long courseId, @PathVariable Long lessonId) {
        lessonService.delete(courseId, lessonId);
    }
}
