package co.edu.unicundi.proyectoSpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_consulta")
public class DetalleConsulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "diagnostico", length = 255, nullable = false)
	private String diagnostico;
	
	@Column(name = "tratamiento", length = 255, nullable = false)
	private String tratamiento;
	
	@Column(name = "id_consulta", nullable = false, insertable = false, updatable = false)
	private Integer idConsulta;
	
	@ManyToOne
	@JoinColumn(name = "id_consulta", nullable = false, foreignKey = @ForeignKey(name = "FK_Consulta"))
	private Consulta consulta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	@JsonIgnore
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	/**
	 * @return the idConsulta
	 */
	public Integer getIdConsulta() {
		return idConsulta;
	}

	/**
	 * @param idConsulta the idConsulta to set
	 */
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}
	
	
}
