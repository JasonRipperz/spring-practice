package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;

public interface IMedicoService extends ICrud<Medico, Integer>  {
	
	 public Page<Medico> findByNombreIgnoreCase(String nombre, Pageable pageable);
	 
	 public Page<Medico> findByApellidoIgnoreCase(String apellido, Pageable pageable);
	 
	 public Page<Medico> findByCorreoIgnoreCase(String correo, Pageable pageable);
	 
	 public  Page<Medico> findByDireccion_pais(String pais, Pageable pageable);
	 
	 public  Page<Medico> findByDireccion_ciudad(String ciudad, Pageable pageable);
		
	 public  Page<Medico> findByDireccion_barrio(String barrio, Pageable pageable);
		
	 public  Page<Medico> findByDireccion_detalle(String detalle, Pageable pageable);
	 
}
