package com.heroku.match.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.heroku.match.domain.IntakeLead;
import com.heroku.match.domain.custom.IHowMany;

public interface IntakeLeadRepository extends PagingAndSortingRepository<IntakeLead, Long>, 
        JpaSpecificationExecutor<IntakeLead> {
	
	
	@Query(value="SELECT * FROM names WHERE SIMILARITY(_name,:name) > 0.6 ORDER BY similarity (_name,:name) DESC",
			  nativeQuery = true)
	Page<IntakeLead> findNameBySimilarity(@Param("name") String name, Pageable p);
	
	Optional<IntakeLead> findById(Long id);
	
	List<IntakeLead> findByIdBetween(Long start, Long end);

	@Query(value="select min(l.id) as minid, max(l.id) as maxid, count(l.*) as howmany from intake.lead as l where l.id>=:id",
			nativeQuery = true)
	IHowMany findHowManyRecordsToProcess(@Param("id") Long id);
}
