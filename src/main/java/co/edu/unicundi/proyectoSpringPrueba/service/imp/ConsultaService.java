package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class ConsultaService implements IConsultaService {

	@Autowired
	private IConsultaRepository consultaRepo;
	
	@Autowired
	private IMedicoService medicoRepo;

	public ConsultaService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Consulta> listar(Pageable pageable) {
		Page<Consulta> listaConsulta =  consultaRepo.findAll(pageable);
		for (Consulta consulta : listaConsulta) {
			consulta.setDetalleConsulta(null);
			consulta.getMedico().setDireccion(null);
		}
		return listaConsulta;
	}

	/**
	 * @throws FieldValidationException
	 * @throws ObjectNotFoundException 
	 * 
	 */
	@Override
	public void guardar(Consulta consulta) throws RepeatedObjectException, FieldValidationException, ObjectNotFoundException {
		consulta.setId(null);
		Medico medico = medicoRepo.obtenerPorId(consulta.getMedico().getId());
		
		consulta.setMedico(medico);
		
		if(consulta.getDetalleConsulta() != null) {
			consulta.getDetalleConsulta().forEach(det -> {
				det.setConsulta(consulta);
			});
		}
		consultaRepo.save(consulta);
	}

	@Override
	public void editar(Consulta consulta)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
	
		if(consulta.getId() != null) {
			Consulta consultaBd = obtenerPorId(consulta.getId());
			Medico medico = medicoRepo.obtenerPorId(consulta.getMedico().getId());
			//consultaBd.setNombreDoctor(consulta.getNombreDoctor());
			consultaBd.setFecha(consulta.getFecha());
			consultaBd.setMedico(medico);
			consultaRepo.save(consultaBd);
		
		}else {
			throw new ObjectNotFoundException("No especificó el id de consulta a editar");
		}
		
	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		if (consultaRepo.validarConsultaPorId(id) > 0) {
			consultaRepo.deleteById(id);
		}else {
			throw new ObjectNotFoundException("No existe una consulta con el id ingresado");
		}
	}

	@Override
	public Consulta obtenerPorId(Integer id) throws ObjectNotFoundException {
		Consulta profesor = consultaRepo.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("No existe una consulta con el id ingresado"));
		return profesor;
	}

	@Override
	public Page<Consulta> findByDetalleConsulta_diagnostico(String diagnostico, Pageable pageable) {
		Page<Consulta> listaConsulta =  consultaRepo.findByDetalleConsulta_diagnostico(diagnostico, pageable);
		for (Consulta consulta : listaConsulta) {
			consulta.setDetalleConsulta(null);
		}
		return listaConsulta;
	}
	
}
