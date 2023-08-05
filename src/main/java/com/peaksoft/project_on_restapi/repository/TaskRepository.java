package com.peaksoft.project_on_restapi.repository;

import com.peaksoft.project_on_restapi.model.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.lesson.id = :id")
    List<Task> getAllTasksById(Long id);
    @Query("select t from Task t where upper(t.taskName) like concat('%',:pagination, '%')" +
            " or upper(t.taskName) like concat('%',:pagination, '%' ) ")
    List<Task> searchPagination(@Param("pagination") String pagination, Pageable pageable);
}