package com.heroku.match.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.heroku.match.domain.IncomingLead;

public class IncomingLeadSpecification implements Specification<IncomingLead> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5193868876527965372L;
	private IncomingLead filter;

    public IncomingLeadSpecification(IncomingLead filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<IncomingLead> root, CriteriaQuery<?> cq,
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
