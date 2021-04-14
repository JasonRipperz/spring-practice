package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;

@Repository
public interface IDetalleConsultaRepository extends JpaRepository<DetalleConsulta, Integer>, PagingAndSortingRepository<DetalleConsulta, Integer>{
	 
	//POR SQL NATIVO
	@Query(value = "SELECT COUNT(*) FROM detalle_consulta WHERE id = ?1", nativeQuery = true)
	int validarDetalleConsultaPorId(int id);
}
