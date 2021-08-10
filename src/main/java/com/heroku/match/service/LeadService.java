package com.heroku.match.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.heroku.match.domain.Lead;
import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceAlreadyExistsException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.repository.LeadRepository;
import com.heroku.match.specification.LeadSpecification;

@Service
public class LeadService {
    
    @Autowired
    private LeadRepository leadRepository;
    
    private boolean existsById(Long id) {
        return leadRepository.existsById(id);
    }
    
    /**
     * BEGIN -- CUSTOM FUNCTIONS
     */
    
    /**
     * 
     * @param il
     * @param pageNumber
     * @param rowPerPage
     * @return
     */
    public List<Lead> findBySimilarity(String il, int pageNumber, int rowPerPage) throws ResourceNotFoundException  {
        List<Lead> names = new ArrayList<>();
        
        if (il==null || il.trim().equals("")) {
            throw new ResourceNotFoundException("Name to search must be present and cannot be empty");
        }
        
        leadRepository.findNameBySimilarity(il, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    /**
     * END -- CUSTOM FUNCTIONS
     */

    public Lead findById(Long id) throws ResourceNotFoundException {
    	Lead il = leadRepository.findById(id).orElse(null);
        if (il==null) {
            throw new ResourceNotFoundException("Cannot find Name with id: " + id);
        }
        else return il;
    }
    
    public List<Lead> findAll(int pageNumber, int rowPerPage) {
        List<Lead> names = new ArrayList<>();
        leadRepository.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    public List<Lead> findAllByName(String il, int pageNumber, int rowPerPage) {
    	Lead filter = new Lead();
        filter.setName(il);
        Specification<Lead> spec = new LeadSpecification(filter);
        
        List<Lead> names = new ArrayList<>();
        leadRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }

    public Lead save(Lead il) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.hasText(il.getName())) {
            if (il.getId() != null && existsById(il.getId())) { 
                throw new ResourceAlreadyExistsException("Name with id: " + il.getId() +
                        " already exists");
            }
            return leadRepository.save(il);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save name");
            exc.addErrorMessage("Name is null or empty");
            throw exc;
        }
    }
    
    public void update(Lead il) 
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.hasText(il.getName())) {
            if (!existsById(il.getId())) {
                throw new ResourceNotFoundException("Cannot find Name with id: " + il.getId());
            }
            leadRepository.save(il);
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
            leadRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return leadRepository.count();
    }
}
