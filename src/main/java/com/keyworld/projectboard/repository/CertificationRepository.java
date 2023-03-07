package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    List<Certification> findAllByOrderByIdDesc();

}
