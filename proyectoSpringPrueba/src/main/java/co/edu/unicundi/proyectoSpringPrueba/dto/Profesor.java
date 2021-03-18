package co.edu.unicundi.proyectoSpringPrueba.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@ApiModel("Modelo Profesor")
public class Profesor {
	
	@ApiModelProperty(value = "Id del docente" , position = 0, accessMode = AccessMode.READ_ONLY)
	private Integer id;
	
	@ApiModelProperty(value = "Nombre del docente" , position = 1, required = true, accessMode = AccessMode.READ_WRITE)
	private String nombre;
	
	@ApiModelProperty(value = "Apellido del docente" , position = 2, required = true, accessMode = AccessMode.READ_WRITE)
	private String apellido;
	
	@ApiModelProperty(value = "Documento del docente" , position = 3, required = true, accessMode = AccessMode.READ_WRITE)
	private String cedula;
	
	/**
	 * Constructor vacío 
	 */
	public Profesor() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 */
	public Profesor(Integer id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
}
