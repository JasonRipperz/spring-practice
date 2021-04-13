package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;

public interface IConsultaService {
	
	public List<Consulta> listar();
	
	public Consulta obtenerPorId(int id) throws ObjectNotFoundException;
	
	public void guardar(Consulta profesor) throws RepeatedObjectException, FieldValidationException;
	
	public void editar(Consulta profesor)  throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException;
	
	public void eliminar(int id) throws ObjectNotFoundException;
}
