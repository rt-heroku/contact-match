package com.heroku.match.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.heroku.match.domain.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long>, 
        JpaSpecificationExecutor<Account> {
	
	
	@Query(value="SELECT * FROM salesforce.account WHERE SIMILARITY(name,:name) > 0.6 ORDER BY similarity (name,:name) DESC",
			  nativeQuery = true)
	Page<Account> findNameBySimilarity(@Param("name") String name, Pageable p);
	
}
