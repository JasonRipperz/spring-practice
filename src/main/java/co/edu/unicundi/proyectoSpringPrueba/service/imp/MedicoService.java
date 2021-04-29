package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepository;
import co.edu.unicundi.proyectoSpringPrueba.repository.IMedicoRepository;
import co.edu.unicundi.proyectoSpringPrueba.repository.IProfesorRepository;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;

@Service
public class MedicoService implements IMedicoService {

	@Autowired
	private IMedicoRepository medicoRepo;

	public MedicoService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Medico> listar(Pageable pageable) {
		Page<Medico> lista = medicoRepo.findAll(pageable);

		return lista;
	}

	@Override
	public void guardar(Medico medico) throws RepeatedObjectException, FieldValidationException {
		medico.setId(null);
		medico.getDireccion().setMedico(medico);
		medicoRepo.save(medico);
	}

	@Override
	public void editar(Medico medico)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {

		if (medico.getId() != null) {
			Medico medicoBd = obtenerPorId(medico.getId());

			medicoBd.setNombre(medico.getNombre());
			medicoBd.setApellido(medico.getApellido());
			medicoBd.setCorreo(medico.getCorreo());
			medicoBd.getDireccion().setBarrio(medico.getDireccion().getBarrio());
			medicoBd.getDireccion().setCiudad(medico.getDireccion().getCiudad());
			medicoBd.getDireccion().setPais(medico.getDireccion().getPais());
			medicoBd.getDireccion().setDetalle(medico.getDireccion().getDetalle());

			medicoRepo.save(medicoBd);

		} else {
			throw new ObjectNotFoundException("No especificó el id de médico a editar");
		}

	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		if (medicoRepo.validarMedicoPorId(id) > 0) {
			medicoRepo.deleteById(id);
		} else {
			throw new ObjectNotFoundException("No existe un médico con el id ingresado");
		}
	}

	@Override
	public Medico obtenerPorId(Integer id) throws ObjectNotFoundException {
		Medico medico = medicoRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("No existe un médico con el id ingresado"));
		return medico;
	}

	@Override
	public Page<Medico> findByNombreIgnoreCase(String nombre, Pageable pageable) {
		Page<Medico> lista = medicoRepo.findByNombreIgnoreCase(nombre, pageable);
		return lista;
	}

	@Override
	public Page<Medico> findByApellidoIgnoreCase(String apellido, Pageable pageable) {
		Page<Medico> lista = medicoRepo.findByApellidoIgnoreCase(apellido, pageable);
		return lista;
	}

	@Override
	public Page<Medico> findByCorreoIgnoreCase(String correo, Pageable pageable) {
		Page<Medico> lista = medicoRepo.findByCorreoIgnoreCase(correo, pageable);
		return lista;
	}

}
