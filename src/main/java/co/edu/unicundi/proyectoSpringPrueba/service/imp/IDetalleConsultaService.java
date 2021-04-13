package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;

public interface IDetalleConsultaService {
	
	public List<DetalleConsulta> listar();
	
	public DetalleConsulta obtenerPorId(int id) throws ObjectNotFoundException;
	
	public void guardar(DetalleConsulta profesor) throws RepeatedObjectException, FieldValidationException;
	
	public void editar(DetalleConsulta profesor)  throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException;
	
	public void eliminar(int id) throws ObjectNotFoundException;
}
