package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Integer>, PagingAndSortingRepository<Medico, Integer>{
	 
	 //POR SQL NATIVO
	 @Query(value = "SELECT COUNT(*) FROM medico WHERE id = ?1", nativeQuery = true)
	 int validarMedicoPorId(int id);
	 
	 public Page<Medico> findByNombreIgnoreCase(String nombre, Pageable pageable);
	 
	 public Page<Medico> findByApellidoIgnoreCase(String apellido, Pageable pageable);
	 
	 public Page<Medico> findByCorreoIgnoreCase(String correo, Pageable pageable);
	 
	 public  Page<Medico> findByDireccion_paisIgnoreCase(String pais, Pageable pageable);
	 	
	 public  Page<Medico> findByDireccion_ciudadIgnoreCase(String ciudad, Pageable pageable);
		
	 public  Page<Medico> findByDireccion_barrioIgnoreCase(String barrio, Pageable pageable);
		
	 public  Page<Medico> findByDireccion_detalleIgnoreCase(String detalle, Pageable pageable);
}
