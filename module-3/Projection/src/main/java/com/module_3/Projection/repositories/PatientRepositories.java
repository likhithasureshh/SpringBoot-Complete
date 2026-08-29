package com.module_3.Projection.repositories;

import com.module_3.Projection.entities.Patient;
import com.module_3.Projection.projection.CNewProjection;
import com.module_3.Projection.projection.CProjection;
import com.module_3.Projection.projection.IProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepositories extends JpaRepository<Patient,Long>
{
    @Query("select p.id as id,p.name as name,p.email as email from Patient p")
   List<IProjection> getIProjectionInfo();

    @Query("select new com.module_3.Projection.projection.CProjection(p.id,p.name,p.email) from Patient p")
    List<CProjection> getCProjection();

    @Query("select new com.module_3.Projection.projection.CNewProjection(p.bloodGroup,COUNT(p)) from Patient p GROUP BY p.bloodGroup ORDER BY COUNT(p) DESC")
    List<CNewProjection> getCNewProjection();

    @Transactional
    @Modifying
    @Query("update Patient p set p.name=:name where p.id=:id")
    int updateRow(@Param("id") Long id,@Param("name") String name);


}
