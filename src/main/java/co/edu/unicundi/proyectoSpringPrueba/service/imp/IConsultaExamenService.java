package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;

public interface IConsultaExamenService extends ICrud<ConsultaExamen, Integer> {

	public void guardarNativo(ConsultaExamen consultaExamen);
	
	List<ConsultaExamen> listarPorIdConsulta(Integer idConsulta);
	
	Page<ConsultaExamen> listarPorIdConsultaPaginado(Integer id, Integer page, Integer size);	
	
}
