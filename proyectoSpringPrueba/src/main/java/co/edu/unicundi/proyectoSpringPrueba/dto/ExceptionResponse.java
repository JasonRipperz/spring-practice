package co.edu.unicundi.proyectoSpringPrueba.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * 
 * @author Jason Rizo
 *
 */
@ApiModel("Exception Wrapper")
public class ExceptionResponse {
	
	@ApiModelProperty(value = "Fecha y hora cuando se presenta la excepción" , accessMode = AccessMode.READ_ONLY)
	private String timestamp;
	
	@ApiModelProperty(value = "Número del código del error HTTP" , accessMode = AccessMode.READ_ONLY)
	private String status;
	
	@ApiModelProperty(value = "Nombre del error HTTP" , accessMode = AccessMode.READ_ONLY)
	private String error;
	
	@ApiModelProperty(value = "Mensaje de error" , accessMode = AccessMode.READ_ONLY)
	private String message;
	
	@ApiModelProperty(value = "Ruta del servicio REST desde donde se disparo el error" , accessMode = AccessMode.READ_ONLY)
	private String path;

	public ExceptionResponse(String status, String error, String message, String path) {
		super();
		this.timestamp = LocalDateTime.now().toString();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
