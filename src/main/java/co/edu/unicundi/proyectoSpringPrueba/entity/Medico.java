package co.edu.unicundi.proyectoSpringPrueba.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "medico")
public class Medico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Nombre es requerido")
	@Size(min = 3, max = 30, message = "Nombre  debe tener entre 3 y 30 caracteres")
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@NotNull(message = "Apellido es requerido")
	@Size(min = 3, max = 30, message = "Apellido debe tener entre 3 y 30 caracteres")	
	@Column(name = "apellido", length = 30, nullable = false)
	private String apellido;
	
	@Column(name = "correo", length = 60, nullable = false)
	private String correo;	
	
	@NotNull(message = "La dirección obligatoria")
	@OneToOne(mappedBy = "medico", cascade =  CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Direccion direccion;
	
	

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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
}
