package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;

public interface IProfesorService {
	
	public List<Profesor> listar();
	
	public Profesor obtener(String cedula) throws ObjectNotFoundException;
	
	public Profesor obtenerPorId(int id) throws ObjectNotFoundException;
	
	public void guardar(Profesor profesor) throws RepeatedObjectException, FieldValidationException;
	
	public void editar(Profesor profesor)  throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException;
	
	public void eliminar(String cedula) throws ObjectNotFoundException;
}
