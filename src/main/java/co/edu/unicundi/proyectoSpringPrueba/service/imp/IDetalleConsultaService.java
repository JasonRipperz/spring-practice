package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;

public interface IDetalleConsultaService {
	
	public List<DetalleConsulta> listar(Pageable pageable);
	
	public DetalleConsulta obtenerPorId(int id) throws ObjectNotFoundException;
	
	public void guardar(DetalleConsulta detalle) throws RepeatedObjectException, ObjectNotFoundException;
	
	public void editar(DetalleConsulta detalle)  throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException;
	
	public void eliminar(int id) throws ObjectNotFoundException;
	
	public Page<DetalleConsulta> listarPorNombreDoctor(String nombreDoctor, Pageable pageable);
}
