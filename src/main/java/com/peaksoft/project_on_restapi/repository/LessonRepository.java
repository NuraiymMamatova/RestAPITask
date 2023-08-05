package com.peaksoft.project_on_restapi.repository;

import com.peaksoft.project_on_restapi.model.entity.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select l from Lesson l where l.course.id = :courseId")
    List<Lesson> getLessonsByCourseId(Long courseId);
    @Query("select l from Lesson l where upper(l.lessonName) like concat('%',:pagination, '%')" +
            " or upper(l.lessonName) like concat('%',:pagination, '%' ) ")
    List<Lesson> searchPagination(@Param("pagination") String pagination, Pageable pageable);
}