package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Integer>{

	 //POR NOMBRAMIENTO
	 Profesor findByCedula(String cedula);
	 
	 //POR SQL NATIVO
	 @Query(value = "SELECT * FROM PROFESORES WHERE cedula = ?1", nativeQuery = true)
	 Profesor findByCedulaSql(String cedula);
	 
	 //POR JQL
	 @Query("select p from Profesor p where p.cedula = ?1")
	 Profesor findByCedulaJpql(String cedula);
	 
	 
}
