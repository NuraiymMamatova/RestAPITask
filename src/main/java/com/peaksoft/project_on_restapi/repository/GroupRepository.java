package com.peaksoft.project_on_restapi.repository;

import com.peaksoft.project_on_restapi.model.entity.Group;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select g from Group g where upper(g.groupName) like concat('%',:pagination, '%')" +
            " or upper(g.groupName) like concat('%',:pagination, '%' ) ")
    List<Group> searchPagination(@Param("pagination") String pagination, Pageable pageable);
}