package co.edu.unicundi.proyectoSpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "direccion")
public class Direccion {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "detalle", length = 50, nullable = false)
	private String detalle;

	@Column(name = "barrio", length = 15, nullable = false)
	private String barrio;
	
	@Column(name = "ciudad", length = 15, nullable = false)
	private String ciudad;		
	
	@Column(name = "pais", length = 15, nullable = false)
	private String pais;	
	
	@OneToOne
	@MapsId
	private Medico medico;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
