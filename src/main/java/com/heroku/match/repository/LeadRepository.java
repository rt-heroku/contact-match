package com.heroku.match.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.heroku.match.domain.Lead;
import com.heroku.match.domain.custom.ILeadSimilarity;

public interface LeadRepository extends PagingAndSortingRepository<Lead, Long>, 
        JpaSpecificationExecutor<Lead> {
	
	
	@Query(value="SELECT * FROM test.lead WHERE SIMILARITY(company,:company) > 0.6 ORDER BY similarity (company,:name) DESC",
			  nativeQuery = true)
	Page<Lead> findNameBySimilarity(@Param("company") String company, Pageable p);
	
	@Query(value="SELECT * FROM test.lead WHERE SIMILARITY(company,:company) > 0.6 ORDER BY similarity (company,:company) DESC",
			  nativeQuery = true)
	List<Lead> findNamesBySimilarity(@Param("company") String company);
	
	@Query(value="SELECT similarity(l.company,:company) as percentage FROM test.lead as l WHERE l.id=:id",
			  nativeQuery = true)
	ILeadSimilarity findPercentage(@Param("id") Long id, @Param("company") String company);

	List<Lead> findByPostalcodeAndStateAndCity(String postalcode, String city, String State);
	
	List<Lead> findByExternalId(String externalId);
	
	List<Lead> findByDoordashId(String doordashId);
	
}
