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

import com.sakaistore.model.Usuario;
import com.sakaistore.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	final static Logger logger = Logger.getLogger(Usuario.class);

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
		logger.debug("Added:: " + usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.findOne(usuario.getId());
		if (usuarioExistente == null) {
			logger.debug("Usuario with id " + usuario.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			usuarioRepository.save(usuario);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id) {
		Usuario usuario = usuarioRepository.findOne(id);
		if (usuario == null) {
			logger.debug("Usuario with id " + id + " does not exists");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Usuario:: " + usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable pageable) {
		Page<Usuario> usuarios = usuarioRepository.findAll(pageable);

		return new ResponseEntity<Page<Usuario>>(usuarios, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
		Usuario usuario = usuarioRepository.findOne(id);
		if (usuario == null) {
			logger.debug("Usuario with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			usuarioRepository.delete(id);
			logger.debug("Usuario with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
