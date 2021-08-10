package com.heroku.match.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.heroku.match.domain.IncomingLead;

public interface IncomingLeadRepository extends PagingAndSortingRepository<IncomingLead, Long>, 
        JpaSpecificationExecutor<IncomingLead> {
	
	
	@Query(value="SELECT * FROM names WHERE SIMILARITY(_name,:name) > 0.6 ORDER BY similarity (_name,:name) DESC",
			  nativeQuery = true)
	Page<IncomingLead> findNameBySimilarity(@Param("name") String name, Pageable p);
	
}
