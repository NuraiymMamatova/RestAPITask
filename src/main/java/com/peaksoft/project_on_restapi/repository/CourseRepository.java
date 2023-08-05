package com.peaksoft.project_on_restapi.repository;

import com.peaksoft.project_on_restapi.model.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.company.id = :companyId")
    List<Course> getAllCoursesByCompanyId(Long companyId);
    @Query("select c from Course c where upper(c.courseName) like concat('%',:pagination, '%')" +
            " or upper(c.courseName) like concat('%',:pagination, '%' ) ")
    List<Course> searchPagination(@Param("pagination") String pagination, Pageable pageable);
}