package com.heroku.match.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceAlreadyExistsException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.model.Matched;
import com.heroku.match.repository.MatchedRepository;
import com.heroku.match.specification.MatchedSpecification;

@Service
public class MatchedService {
    
    @Autowired
    private MatchedRepository matchedRepository;
    
    private boolean existsById(Long id) {
        return matchedRepository.existsById(id);
    }
    
    public Matched findById(Long id) throws ResourceNotFoundException {
    	Matched il = matchedRepository.findById(id).orElse(null);
        if (il==null) {
            throw new ResourceNotFoundException("Cannot find Name with id: " + id);
        }
        else return il;
    }
    
    public List<Matched> findAll(int pageNumber, int rowPerPage) {
        List<Matched> names = new ArrayList<>();
        matchedRepository.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    public List<Matched> findAllByName(String il, int pageNumber, int rowPerPage) {
    	Matched filter = new Matched();
        filter.setName(il);
        Specification<Matched> spec = new MatchedSpecification(filter);
        
        List<Matched> names = new ArrayList<>();
        matchedRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }

    public Matched save(Matched il) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.hasText(il.getName())) {
            if (il.getMatchId() != null && existsById(il.getMatchId())) { 
                throw new ResourceAlreadyExistsException("Name with id: " + il.getMatchId() +
                        " already exists");
            }
            return matchedRepository.save(il);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save name");
            exc.addErrorMessage("Name is null or empty");
            throw exc;
        }
    }
    
    public void update(Matched il) 
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.hasText(il.getName())) {
            if (!existsById(il.getMatchId())) {
                throw new ResourceNotFoundException("Cannot find Name with id: " + il.getMatchId());
            }
            matchedRepository.save(il);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save name");
            exc.addErrorMessage("Name is null or empty");
            throw exc;
        }
    }
        
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find name with id: " + id);
        }
        else {
            matchedRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return matchedRepository.count();
    }
}
