package com.heroku.match.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.heroku.match.domain.IntakeLead;
import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceAlreadyExistsException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.repository.IntakeLeadRepository;
import com.heroku.match.specification.IntakeLeadSpecification;

@Service
public class IntakeLeadService {
    
    @Autowired
    private IntakeLeadRepository nameRepository;
    
    private boolean existsById(Long id) {
        return nameRepository.existsById(id);
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
    public List<IntakeLead> findBySimilarity(String il, int pageNumber, int rowPerPage) throws ResourceNotFoundException  {
        List<IntakeLead> names = new ArrayList<>();
        
        if (il==null || il.trim().equals("")) {
            throw new ResourceNotFoundException("Name to search must be present and cannot be empty");
        }
        
        nameRepository.findNameBySimilarity(il, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    /**
     * END -- CUSTOM FUNCTIONS
     */

    public IntakeLead findById(Long id) throws ResourceNotFoundException {
    	IntakeLead il = nameRepository.findById(id).orElse(null);
        if (il==null) {
            throw new ResourceNotFoundException("Cannot find Name with id: " + id);
        }
        else return il;
    }
    
    public List<IntakeLead> findAll(int pageNumber, int rowPerPage) {
        List<IntakeLead> names = new ArrayList<>();
        nameRepository.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    public List<IntakeLead> findAllByName(String il, int pageNumber, int rowPerPage) {
    	IntakeLead filter = new IntakeLead();
        filter.setName(il);
        Specification<IntakeLead> spec = new IntakeLeadSpecification(filter);
        
        List<IntakeLead> names = new ArrayList<>();
        nameRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }

    public IntakeLead save(IntakeLead il) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.hasText(il.getName())) {
            if (il.getId() != null && existsById(il.getId())) { 
                throw new ResourceAlreadyExistsException("Name with id: " + il.getId() +
                        " already exists");
            }
            return nameRepository.save(il);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save name");
            exc.addErrorMessage("Name is null or empty");
            throw exc;
        }
    }
    
    public void update(IntakeLead il) 
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.hasText(il.getName())) {
            if (!existsById(il.getId())) {
                throw new ResourceNotFoundException("Cannot find Name with id: " + il.getId());
            }
            nameRepository.save(il);
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
            nameRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return nameRepository.count();
    }
}
