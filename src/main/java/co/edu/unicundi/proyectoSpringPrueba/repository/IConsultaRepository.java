package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Integer>, PagingAndSortingRepository<Consulta, Integer>{
	 
	
	 public  Page<Consulta> findByDetalleConsulta_diagnostico(String diagnostico, Pageable pageable);
	
	 //POR SQL NATIVO
	 @Query(value = "SELECT COUNT(*) FROM consulta WHERE id = ?1", nativeQuery = true)
	 int validarConsultaPorId(int id);
}
