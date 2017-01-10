package com.sakaistore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Setor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true, nullable = false)
	private String setor;
	@OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);;

	public Setor(Long id, String setor, Set<Usuario> usuarios) {
		super();
		this.id = id;
		this.setor = setor;
		this.usuarios = usuarios;
	}

	public Setor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		String result = String.format("Setor[id=%d, setor='%s']%n", id, setor);
		if (usuarios != null) {
			for (Usuario usuario : usuarios) {
				result += String.format("Usuario[id=%d, name='%s']%n", usuario.getId(), usuario.getNome());
			}
		}
		System.out.println("Resultado: " + result);
		return result;
	}

}
