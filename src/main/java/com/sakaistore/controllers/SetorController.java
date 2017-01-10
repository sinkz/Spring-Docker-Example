package com.sakaistore.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sakaistore.model.Setor;
import com.sakaistore.repositories.SetorRepository;

@RestController
@RequestMapping("/setor")
public class SetorController {

	final static Logger logger = Logger.getLogger(Setor.class);

	@Autowired
	SetorRepository setorRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Setor> addSetor(@RequestBody Setor setor) {
		setorRepository.save(setor);
		logger.debug("Added:: " + setor);
		return new ResponseEntity<Setor>(setor, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSetor(@RequestBody Setor setor) {
		Setor setorExistente = setorRepository.findOne(setor.getId());
		if (setorExistente == null) {
			logger.debug("Setor with id " + setor.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			setorRepository.save(setor);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Setor> getSetor(@PathVariable("id") Long id) {
		Setor setor = setorRepository.findOne(id);
		if (setor == null) {
			logger.debug("Setor with id " + id + " does not exists");
			return new ResponseEntity<Setor>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Setor:: " + setor);
		return new ResponseEntity<Setor>(setor, HttpStatus.OK);
	}

	@RequestMapping(value = "/setores", method = RequestMethod.GET)
	public ResponseEntity<Page<Setor>> listarSetors(Pageable pageable) {
		Page<Setor> setors = setorRepository.findAll(pageable);

		return new ResponseEntity<Page<Setor>>(setors, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSetor(@PathVariable("id") Long id) {
		Setor setor = setorRepository.findOne(id);
		if (setor == null) {
			logger.debug("Setor with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			setorRepository.delete(id);
			logger.debug("Setor with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
