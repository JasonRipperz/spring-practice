package co.edu.unicundi.proyectoSpringPrueba.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "consulta")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre_doctor", length = 30, nullable = false)
	private String nombreDoctor;
	
	@NotNull(message = "La fecha es requerida")
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
	
	@OneToMany(mappedBy = "consulta", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<DetalleConsulta> detalleConsulta;
	
	@NotNull(message = "El médico es obligatorio")
	@ManyToOne
	@JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "FK_Medico"))
	private Medico medico;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreDoctor() {
		return nombreDoctor;
	}

	public void setNombreDoctor(String nombreDoctor) {
		this.nombreDoctor = nombreDoctor;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public List<DetalleConsulta> getDetalleConsulta() {
		return detalleConsulta;
	}

	public void setDetalleConsulta(List<DetalleConsulta> detalleConsulta) {
		this.detalleConsulta = detalleConsulta;
	}

	/**
	 * @return the medico
	 */
	public Medico getMedico() {
		return medico;
	}

	/**
	 * @param medico the medico to set
	 */
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	
}
