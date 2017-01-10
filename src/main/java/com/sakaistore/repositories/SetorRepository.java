package com.sakaistore.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sakaistore.model.Setor;
import com.sakaistore.model.Usuario;

public interface SetorRepository extends PagingAndSortingRepository<Setor, Long> {

	List<Usuario> findBySetor(@Param("setor") String setor);

}
