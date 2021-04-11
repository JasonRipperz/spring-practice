package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

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

	public List<Profesor> profesores = new ArrayList<>();

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
	public void guardar(Profesor profesor) throws RepeatedObjectException, FieldValidationException {

		profesorRepo.save(profesor);
	//	throw new RepeatedObjectException("Ya existe un profesor creado con el mismo documento");
		
	}

	@Override
	public Profesor obtener(String cedula) throws ObjectNotFoundException {

		Profesor profesorObtenido = new Profesor();
		for (Profesor profesor : profesores) {
			if (profesor.getCedula().equals(cedula)) {
				profesorObtenido = profesor;
				break;
			}
		}
		if (profesorObtenido.getId() != null) {
			return profesorObtenido;
		} else {
			throw new ObjectNotFoundException("No existe un docente con el número de cédula ingresado");
		}
	}

	@Override
	public void editar(Profesor profesor)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {

		Profesor profesorBd = obtenerPorId(profesor.getId());
		
		profesorBd.setNombre(profesor.getNombre());
		profesorBd.setApellido(profesor.getApellido());
		profesorBd.setCedula(profesor.getCedula());
		
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

}
