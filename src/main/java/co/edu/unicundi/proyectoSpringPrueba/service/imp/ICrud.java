package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;

public interface ICrud<T, V> {

	public T obtenerPorId(V id) throws ObjectNotFoundException;
	
	public Page<T> listar(Pageable pageable);
	
	public void guardar(T entity) throws RepeatedObjectException, FieldValidationException, ObjectNotFoundException;
	
	public void editar(T entity) throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException;
	
	public void eliminar(V id) throws ObjectNotFoundException;	

}
