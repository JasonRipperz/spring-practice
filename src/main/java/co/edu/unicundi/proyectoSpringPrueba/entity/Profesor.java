package co.edu.unicundi.proyectoSpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
@Table(name = "PROFESORES")
public class Profesor {
	

	@ApiModelProperty(value = "Id del docente" , position = 0, accessMode = AccessMode.READ_ONLY)
	@Id
	private Integer id;
	
	@ApiModelProperty(value = "Nombre del docente" , position = 1, required = true, accessMode = AccessMode.READ_WRITE)
	@Column(name = "nombre")
	private String nombre;
	
	@ApiModelProperty(value = "Apellido del docente" , position = 2, required = true, accessMode = AccessMode.READ_WRITE)
	@Column(name = "apellido")
	private String apellido;
	
	@ApiModelProperty(value = "Documento del docente" , position = 3, required = true, accessMode = AccessMode.READ_WRITE)
	@Column(name = "cedula")
	private String cedula;
	
	public Profesor() {
	
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	

}
