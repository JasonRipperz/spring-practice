package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Vista;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepository;
import co.edu.unicundi.proyectoSpringPrueba.repository.IDetalleConsultaRepository;
import co.edu.unicundi.proyectoSpringPrueba.repository.IProfesorRepository;
import co.edu.unicundi.proyectoSpringPrueba.repository.IVistaRepository;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;

@Service
public class VistaService implements IVistaService {

	@Autowired
	private IVistaRepository repo;
	

	@Override
	public Page<Vista> listar(Pageable pageable) {
		Page<Vista> lista =  repo.findAll(pageable);
		return lista;
	}


	@Override
	public Vista obtenerPorId(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void guardar(Vista entity)
			throws RepeatedObjectException, FieldValidationException, ObjectNotFoundException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void editar(Vista entity) throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminar(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
