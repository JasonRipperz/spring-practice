package co.edu.unicundi.proyectoSpringPrueba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;

@Repository
public interface IConsultaExamenRepo extends JpaRepository<ConsultaExamen, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen, info_adicional) values(:idConsulta, :idExamen, :infoAdicional)", nativeQuery = true)
	public void guardar(@Param("idConsulta") Integer idConsulta,@Param("idExamen") Integer idExamen, 
			@Param("infoAdicional") String infoAdicional);
	
	@Query(value = "from ConsultaExamen ce where ce.consulta.id = :idConsulta")
	List<ConsultaExamen> listarPorIdCosnulta(@Param("idConsulta")Integer idConsulta);
	
	Page<ConsultaExamen> findByConsulta_Id(Integer id, Pageable page);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE consulta_examen SET  info_adicional = :infoAdicional WHERE id_consulta = :idConsulta AND id_examen= :idExamen ", nativeQuery = true)
	public void editar(@Param("idConsulta") Integer idConsulta,@Param("idExamen") Integer idExamen, 
			@Param("infoAdicional") String infoAdicional);

}
