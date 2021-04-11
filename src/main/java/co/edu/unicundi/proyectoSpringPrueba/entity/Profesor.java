package co.edu.unicundi.proyectoSpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
@Table(name = "PROFESORES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
public class Profesor {
	

	@ApiModelProperty(value = "Id del docente" , position = 0, accessMode = AccessMode.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty(value = "Nombre del docente" , position = 1, required = true, accessMode = AccessMode.READ_WRITE)
	@NotNull(message = "El nombre es obligatorio")
	@Size(min = 3, max = 50, message = "El nombre debe tener de 3 a 50 caracteres")	
	@Column(name = "nombre")
	private String nombre;
	
	@ApiModelProperty(value = "Apellido del docente" , position = 2, required = true, accessMode = AccessMode.READ_WRITE)
	@NotNull(message = "El apellido es obligatorio")
	@Size(min = 3, max = 50, message = "El apellido debe tener de 3 a 50 caracteres")
	@Column(name = "apellido")
	private String apellido;
	
	@ApiModelProperty(value = "Documento del docente" , position = 3, required = true, accessMode = AccessMode.READ_WRITE)
	@NotNull(message = "El documento es obligatorio")
	@Size(min = 3, max = 50, message = "El documento debe tener de 5 a 10 caracteres")
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
