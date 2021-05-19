package co.edu.unicundi.proyectoSpringPrueba.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaExamenRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IConsultaExamenService;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IConsultaService;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService {
	
	@Autowired
	private IConsultaExamenRepo repo;
	
	@Autowired
	private IExamenRepository examenRepo;
	
	@Autowired
	private IConsultaService consultaRepo;

	
	@Override
	public void guardarNativo(ConsultaExamen consultaExamen) {
		repo.guardar(consultaExamen.getConsulta().getId(), consultaExamen.getExamen().getId(), consultaExamen.getInfoAdicional());
	}

	@Override
	public List<ConsultaExamen> listarPorIdConsulta(Integer idConsulta) {
		return repo.listarPorIdCosnulta(idConsulta);
	}

	@Override
	public Page<ConsultaExamen> listarPorIdConsultaPaginado(Integer id, Integer page, Integer size) {
		return repo.findByConsulta_Id(id, PageRequest.of(page, size));
	}

	@Override
	public ConsultaExamen obtenerPorId(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ConsultaExamen> listar(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(ConsultaExamen entity)
			throws RepeatedObjectException, FieldValidationException, ObjectNotFoundException {
		repo.save(entity);
	}

	@Override
	public void editar(ConsultaExamen entity)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
		consultaRepo.obtenerPorId(entity.getConsulta().getId());
		obtenerExamenPorId(entity.getExamen().getId());
		
		repo.editar(entity.getConsulta().getId(), entity.getExamen().getId(), entity.getInfoAdicional());
	}

	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		
	}
	
	@Override
	public void eliminarNativo(Integer idConsulta, Integer idExamen) throws ObjectNotFoundException {
		if (repo.validarConsultaPorId(idConsulta, idExamen) > 0) {
			repo.eliminar(idConsulta, idExamen);
		}else {
			throw new ObjectNotFoundException("Verifique que la consulta exista y que tenga el examen asociado");
		}
	}
	public Examen obtenerExamenPorId(Integer id) throws ObjectNotFoundException {
		Examen examen = examenRepo.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("No existe un examen con el id ingresado"));
		return examen;
	}

}
