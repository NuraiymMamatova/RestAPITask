package com.peaksoft.project_on_restapi.repository;

import com.peaksoft.project_on_restapi.model.entity.Instructor;
import com.peaksoft.project_on_restapi.model.entity.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select i from Instructor i where i.email = :email")
    Instructor findByEmail(String email);

    @Query("select i from Instructor i where i.course.id = :courseId")
    List<Instructor> getAllInstructorByCourseId(Long courseId);

    @Query("select i from Instructor i where upper(i.firstName) like concat('%',:pagination, '%')" +
            " or upper(i.firstName) like concat('%',:pagination, '%' ) ")
    List<Instructor> searchPagination(@Param("pagination") String pagination, Pageable pageable);
}