package co.edu.unicundi.proyectoSpringPrueba.controller;

import java.lang.reflect.Array;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import co.edu.unicundi.proyectoSpringPrueba.dto.ExceptionResponse;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IConsultaService;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/consultas")
@Api(value = "Servicios Rest de consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService consultaService;

	@ApiOperation(value = "Listar todas las consultas", notes = "Servicio para listar todas los consultas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se obtienen correctamente los datos", response = List.class),
			@ApiResponse(code = 204, message = "No Content.No hay registro de datos en sistema)", response = Array.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@GetMapping("/listar")
	public ResponseEntity<Page<Consulta>> listar(Pageable pageable) {
		return new ResponseEntity<Page<Consulta>>(consultaService.listar(pageable),
				consultaService.listar(pageable).getSize() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Obtener una consulta por id", notes = "Servicio para obtener por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se retorno correctamente", response = Consulta.class),
			@ApiResponse(code = 404, message = "NOT_FOUND. No se encontro registro asociado al documento ingresado)", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@GetMapping("/obtenerPorId/{id}")
	public ResponseEntity<Consulta> obtenerPorId(@PathVariable int id) throws ObjectNotFoundException {
		return new ResponseEntity<Consulta>(consultaService.obtenerPorId(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Guardar una consulta", notes = "Servicio para guardar una consulta")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created. Se guardó exitosamente una consulta", response = Object.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Consulta consulta)
			throws RepeatedObjectException, FieldValidationException, ObjectNotFoundException {
		consultaService.guardar(consulta);
		return new ResponseEntity<Object>("Consulta creada", HttpStatus.CREATED);
	}

	@ApiOperation(value = "Editar una consulta", notes = "Servicio para editar una consulta")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se modificó correctamente la consulta", response = Object.class),
			@ApiResponse(code = 404, message = "NOT_FOUND. No se encontro una consulta asociado al id", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@PutMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Consulta consulta)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
		consultaService.editar(consulta);
		return new ResponseEntity<Object>("Consulta editada", HttpStatus.OK);
	}

	@ApiOperation(value = "Eliminar una consulta", notes = "Servicio para eliminar un consulta")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "NO_CONTENT. Se modificó correctamente la consulta"),
			@ApiResponse(code = 404, message = "NOT_FOUND. No se encontro una consulta asociada al id", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ObjectNotFoundException {
		consultaService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
