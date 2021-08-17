package com.heroku.match.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.heroku.match.model.Matched;

public interface MatchedRepository extends PagingAndSortingRepository<Matched, Long>, 
        JpaSpecificationExecutor<Matched> {
	
		
}
