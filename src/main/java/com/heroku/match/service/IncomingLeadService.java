package com.heroku.match.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.heroku.match.domain.IncomingLead;
import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceAlreadyExistsException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.repository.IncomingLeadRepository;
import com.heroku.match.specification.IncomingLeadSpecification;

@Service
public class IncomingLeadService {
    
    @Autowired
    private IncomingLeadRepository incomingLeadRepository;
    
    private boolean existsById(Long id) {
        return incomingLeadRepository.existsById(id);
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
    public List<IncomingLead> findBySimilarity(String il, int pageNumber, int rowPerPage) throws ResourceNotFoundException  {
        List<IncomingLead> names = new ArrayList<>();
        
        if (il==null || il.trim().equals("")) {
            throw new ResourceNotFoundException("Name to search must be present and cannot be empty");
        }
        
        incomingLeadRepository.findNameBySimilarity(il, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    /**
     * END -- CUSTOM FUNCTIONS
     */

    public IncomingLead findById(Long id) throws ResourceNotFoundException {
    	IncomingLead il = incomingLeadRepository.findById(id).orElse(null);
        if (il==null) {
            throw new ResourceNotFoundException("Cannot find Name with id: " + id);
        }
        else return il;
    }
    
    public List<IncomingLead> findAll(int pageNumber, int rowPerPage) {
        List<IncomingLead> names = new ArrayList<>();
        incomingLeadRepository.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    public List<IncomingLead> findAllByName(String il, int pageNumber, int rowPerPage) {
    	IncomingLead filter = new IncomingLead();
        filter.setName(il);
        Specification<IncomingLead> spec = new IncomingLeadSpecification(filter);
        
        List<IncomingLead> names = new ArrayList<>();
        incomingLeadRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }

    public IncomingLead save(IncomingLead il) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.hasText(il.getName())) {
            if (il.getId() != null && existsById(il.getId())) { 
                throw new ResourceAlreadyExistsException("Name with id: " + il.getId() +
                        " already exists");
            }
            return incomingLeadRepository.save(il);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save name");
            exc.addErrorMessage("Name is null or empty");
            throw exc;
        }
    }
    
    public void update(IncomingLead il) 
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.hasText(il.getName())) {
            if (!existsById(il.getId())) {
                throw new ResourceNotFoundException("Cannot find Name with id: " + il.getId());
            }
            incomingLeadRepository.save(il);
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
            incomingLeadRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return incomingLeadRepository.count();
    }
}
