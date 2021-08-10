package com.heroku.match.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.heroku.match.domain.Contact;
import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceAlreadyExistsException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.repository.ContactRepository;
import com.heroku.match.specification.ContactSpecification;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;
    
    private boolean existsById(Long id) {
        return contactRepository.existsById(id);
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
    public List<Contact> findBySimilarity(String il, int pageNumber, int rowPerPage) throws ResourceNotFoundException  {
        List<Contact> names = new ArrayList<>();
        
        if (il==null || il.trim().equals("")) {
            throw new ResourceNotFoundException("Name to search must be present and cannot be empty");
        }
        
        contactRepository.findNameBySimilarity(il, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    /**
     * END -- CUSTOM FUNCTIONS
     */

    public Contact findById(Long id) throws ResourceNotFoundException {
    	Contact il = contactRepository.findById(id).orElse(null);
        if (il==null) {
            throw new ResourceNotFoundException("Cannot find Name with id: " + id);
        }
        else return il;
    }
    
    public List<Contact> findAll(int pageNumber, int rowPerPage) {
        List<Contact> names = new ArrayList<>();
        contactRepository.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }
    
    public List<Contact> findAllByName(String il, int pageNumber, int rowPerPage) {
    	Contact filter = new Contact();
        filter.setName(il);
        Specification<Contact> spec = new ContactSpecification(filter);
        
        List<Contact> names = new ArrayList<>();
        contactRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(names::add);
        return names;
    }

    public Contact save(Contact il) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.hasText(il.getName())) {
            if (il.getId() != null && existsById(il.getId())) { 
                throw new ResourceAlreadyExistsException("Name with id: " + il.getId() +
                        " already exists");
            }
            return contactRepository.save(il);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save name");
            exc.addErrorMessage("Name is null or empty");
            throw exc;
        }
    }
    
    public void update(Contact il) 
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.hasText(il.getName())) {
            if (!existsById(il.getId())) {
                throw new ResourceNotFoundException("Cannot find Name with id: " + il.getId());
            }
            contactRepository.save(il);
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
            contactRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return contactRepository.count();
    }
}
