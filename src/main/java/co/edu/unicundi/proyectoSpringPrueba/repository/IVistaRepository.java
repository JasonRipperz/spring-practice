package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.entity.Vista;

@Repository
public interface IVistaRepository extends JpaRepository<Vista, Integer>, PagingAndSortingRepository<Vista, Integer>{
	 
}
