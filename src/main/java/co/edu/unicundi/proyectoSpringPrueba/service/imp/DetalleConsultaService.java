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
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepository;
import co.edu.unicundi.proyectoSpringPrueba.repository.IDetalleConsultaRepository;
import co.edu.unicundi.proyectoSpringPrueba.repository.IProfesorRepository;
import co.edu.unicundi.proyectoSpringPrueba.util.PageRequest;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;

@Service
public class DetalleConsultaService implements IDetalleConsultaService {

	@Autowired
	private IDetalleConsultaRepository detConsultaRepo;
	
	@Autowired 
	private IConsultaService consultaRepo;

	public DetalleConsultaService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DetalleConsulta> listar(Pageable pageable) {
		List<DetalleConsulta> listaConsulta =  detConsultaRepo.findAll(pageable).getContent();
		return listaConsulta;
	}

	/**
	 * @throws FieldValidationException
	 * @throws ObjectNotFoundException 
	 * 
	 */
	@Override
	public void guardar(DetalleConsulta detalleConsulta) throws RepeatedObjectException, ObjectNotFoundException {
		//detalleConsulta.setId(null);
		
		detalleConsulta.setConsulta(consultaRepo.obtenerPorId(detalleConsulta.getIdConsulta()));
		detConsultaRepo.save(detalleConsulta);
	}

	@Override
	public void editar(DetalleConsulta detalle)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
	
		if(detalle.getId() != null) {
			DetalleConsulta detalleBd = obtenerPorId(detalle.getId());
			
			detalleBd.setDiagnostico(detalle.getDiagnostico());
			detalleBd.setTratamiento(detalle.getTratamiento());
			
			detConsultaRepo.save(detalleBd);
		
		}else {
			throw new ObjectNotFoundException("No especific?? el id del detalle a editar");
		}
		
	}

	@Override
	public void eliminar(int id) throws ObjectNotFoundException {
		if (detConsultaRepo.validarDetalleConsultaPorId(id) > 0) {
			detConsultaRepo.deleteById(id);
		}else {
			throw new ObjectNotFoundException("No existe un detalle de consulta con el id ingresado");
		}
	}

	@Override
	public DetalleConsulta obtenerPorId(int id) throws ObjectNotFoundException {
		DetalleConsulta profesor = detConsultaRepo.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("No existe un detalle de consulta con el id ingresado"));
		return profesor;
	}
	
	@Override
	public Page<DetalleConsulta> listarPorNombreDoctor(String nombreDoctor, Pageable pageable) {
		Page<DetalleConsulta> listaConsulta = detConsultaRepo.findByConsulta_nombreDoctor(nombreDoctor, pageable);
		return listaConsulta;
	}
	
}
