package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;

@Repository
public interface IDetalleConsultaRepository extends JpaRepository<DetalleConsulta, Integer>{
	 
}
