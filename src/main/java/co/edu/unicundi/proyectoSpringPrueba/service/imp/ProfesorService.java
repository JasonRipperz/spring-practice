package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IProfesorRepository;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;

@Service
public class ProfesorService implements IProfesorService {

	@Autowired
	private IProfesorRepository profesorRepo;

	public ProfesorService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Profesor> listar() {
		return profesorRepo.findAll();
	}

	/**
	 * @throws FieldValidationException
	 * 
	 */
	@Override
	public void guardar(@Valid Profesor profesor) throws RepeatedObjectException, FieldValidationException {
		if(buscarPorCedula(profesor.getCedula()) == null) {
			profesorRepo.save(profesor);
		}else {
			throw new RepeatedObjectException("Ya existe un profesor creado con el mismo documento");
		}
		
	}

	@Override
	public Profesor obtener(String cedula) throws ObjectNotFoundException {
		
		Profesor profesor =  profesorRepo.findByCedula(cedula);
		//Profesor profesor =  profesorRepo.findByCedulaJpql(cedula);
		//Profesor profesor =  profesorRepo.findByCedulaSql(cedula);
		
		if (profesor != null) {
			return profesor;
		}else {
			throw new ObjectNotFoundException("No existe un docente con el número de cédula ingresado");
		}
	
	}

	@Override
	public void editar(Profesor profesor)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {

		Profesor profesorBd = obtenerPorId(profesor.getId());
		
		if(profesor.getCedula().equals(profesorBd.getCedula())) {
			profesorBd.setNombre(profesor.getNombre());
			profesorBd.setApellido(profesor.getApellido());
			profesorBd.setCedula(profesor.getCedula());
		}
		
		profesorRepo.save(profesorBd);
		//throw new RepeatedObjectException("Ya existe un profesor creado con el mismo documento");
		

	}

	@Override
	public void eliminar(int id) throws ObjectNotFoundException {
		obtenerPorId(id);
		profesorRepo.deleteById(id);
	}

	@Override
	public Profesor obtenerPorId(int id) throws ObjectNotFoundException {
		Profesor profesor = profesorRepo.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("No existe un docente con el id ingresado"));
		return profesor;
	}
	
	private Profesor buscarPorCedula(String cedula) {
		return profesorRepo.findByCedula(cedula);
	}

}
