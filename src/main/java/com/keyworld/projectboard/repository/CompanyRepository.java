package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByOrderByIdDesc();

}