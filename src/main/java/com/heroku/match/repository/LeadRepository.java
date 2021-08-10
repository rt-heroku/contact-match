package com.heroku.match.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.heroku.match.domain.Lead;

public interface LeadRepository extends PagingAndSortingRepository<Lead, Long>, 
        JpaSpecificationExecutor<Lead> {
	
	
	@Query(value="SELECT * FROM salesforce.lead WHERE SIMILARITY(name,:name) > 0.6 ORDER BY similarity (name,:name) DESC",
			  nativeQuery = true)
	Page<Lead> findNameBySimilarity(@Param("name") String name, Pageable p);
	
}
