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
	private IProfesorRepository IProfesorRepo;
	
	public List<Profesor> profesores = new ArrayList<>();

	public ProfesorService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Profesor> listar() {
		//return IProfesorRepo.findAll();
		return profesores;
	}

	/**
	 * @throws FieldValidationException 
	 * 
	 */
	@Override
	public void guardar(Profesor profesor) throws RepeatedObjectException, FieldValidationException {
		this.validarDocente(profesor);
		boolean flag = true;
		for (Profesor profe : profesores) {
			if (profe.getCedula().equals(profesor.getCedula())) {
				flag = false;
				break;
			}
		}
		if (flag) {
			profesor.setId(profesores.size());
			profesores.add(profesor);
		} else {
			throw new RepeatedObjectException("Ya existe un profesor creado con el mismo documento");
		}
	}

	@Override
	public Profesor obtener(String cedula) throws ObjectNotFoundException {
		
		/**
		 * Profesor profesor = IprofesorRepo.findById(id).orElseThrow(
		 * () -> new ObjectNotFoundException("No existe un docente con el número de cédula ingresado"));
		 * return profesor; 
		 */
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
	public void editar(Profesor profesor) throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
		
		this.validarDocente(profesor);
		
		Profesor profesorObtenido = new Profesor();
		Profesor profesorCedula = new Profesor();
		for (Profesor profe : profesores) {
			if (profesor.getId().equals(profe.getId())) {
				profesorObtenido = profe;
				break;
			}
		}
		
		for (Profesor profe : profesores) {
			if (profesor.getCedula().equals(profe.getCedula())) {
				profesorCedula = profe;
				break;
			}
		}
		
		if (profesorObtenido.getId() == null) {
			throw new ObjectNotFoundException("No existe un docente con el ID ingresado");
		}

		if (profesorCedula.getId() == null || profesorCedula.getId() == profesorObtenido.getId()) {
			profesorObtenido.setNombre(profesor.getNombre());
			profesorObtenido.setApellido(profesor.getApellido());
			profesorObtenido.setCedula(profesor.getCedula());

		} else {
			throw new RepeatedObjectException("Ya existe un profesor creado con el mismo documento");
		}

	}

	@Override
	public void eliminar(String cedula) throws ObjectNotFoundException {
		Profesor profesorEliminar = new Profesor();
		for (Profesor profesor : profesores) {
			if (profesor.getCedula().equals(cedula)) {
				profesorEliminar = profesor;
			}
		}

		if (profesorEliminar.getId() != null) {
			profesores.remove(profesorEliminar);
		} else {
			throw new ObjectNotFoundException("No existe un docente con la cédula ingresada");
		}
	}
	
	private void validarDocente(Profesor profesor) throws FieldValidationException {
		if(profesor.getNombre().length() < 3) {
			throw new FieldValidationException("El nombre debe tener mínimo 3 caracteres");
		}
		
		if(profesor.getApellido().length() < 3) {
			throw new FieldValidationException("El apellido debe tener mínimo 3 caracteres");
		}
		
		if(profesor.getCedula().length() < 5) {
			throw new FieldValidationException("El documento debe tener mínimo 5 caracteres");
		}
	}

}
