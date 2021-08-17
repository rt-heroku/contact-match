package com.heroku.match.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.heroku.match.model.Matched;

public class MatchedSpecification implements Specification<Matched> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5193868876527965372L;
	private Matched filter;

    public MatchedSpecification(Matched filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Matched> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if (filter.getName() != null) {
            p.getExpressions().add(cb.like(cb.lower(root.get("_name")), "%" + filter.getName().toLowerCase() + "%"));
        }

        
        /*
        if (filter.getName()!= null && filter.getPhone()!= null) {
            p.getExpressions().add(cb.and(
                    cb.equal(root.get("name"), filter.getName()),
                    cb.equal(root.get("age"), filter.getPhone())
            ));
        }
        */

        return p;
    }
}
