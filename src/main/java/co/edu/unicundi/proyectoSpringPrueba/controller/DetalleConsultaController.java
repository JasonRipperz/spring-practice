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
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IConsultaService;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IDetalleConsultaService;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/detallesConsulta")
@Api(value = "Servicios Rest de detalles consultas")
public class DetalleConsultaController {

	@Autowired
	private IDetalleConsultaService detConsultaRepo;

	@ApiOperation(value = "Listar todas las detalles", notes = "Servicio para listar todas los detalles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se obtienen correctamente los datos", response = List.class),
			@ApiResponse(code = 204, message = "No Content.No hay registro de datos en sistema)", response = Array.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@GetMapping("/listar")
	public ResponseEntity<Page<DetalleConsulta>> listar(Pageable pageable) {
		return new ResponseEntity<Page<DetalleConsulta>>(detConsultaRepo.listar(pageable),
				detConsultaRepo.listar(pageable).getSize() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Obtener un detalle por id", notes = "Servicio para obtener por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se retorno correctamente", response = DetalleConsulta.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@GetMapping("/obtenerPorId/{id}")
	public ResponseEntity<DetalleConsulta> obtenerPorId(@PathVariable int id) throws ObjectNotFoundException {
		return new ResponseEntity<DetalleConsulta>(detConsultaRepo.obtenerPorId(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Guardar un detalle", notes = "Servicio para guardar un detalle")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created. Se guardó exitosamente un detalle de consulta", response = Object.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody DetalleConsulta consulta)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
		detConsultaRepo.guardar(consulta);
		return new ResponseEntity<Object>("Detalle creado", HttpStatus.CREATED);
	}

	@ApiOperation(value = "Editar una detalle", notes = "Servicio para editar una consulta")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se modificó correctamente el detalle", response = Object.class),
			@ApiResponse(code = 404, message = "NOT_FOUND. No se encontro un detalle asociado al id", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@PutMapping("editar")
	public ResponseEntity<?> editar(@RequestBody DetalleConsulta consulta)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
		detConsultaRepo.editar(consulta);
		return new ResponseEntity<Object>("Detalle editado", HttpStatus.OK);
	}

	@ApiOperation(value = "Eliminar un detalle", notes = "Servicio para eliminar un detalle")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "NO_CONTENT. Se modificó correctamente la consulta"),
			@ApiResponse(code = 404, message = "NOT_FOUND. No se encontro un detalle asociado al id", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Error inesperado del sistema", response = ExceptionResponse.class) })
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ObjectNotFoundException {
		detConsultaRepo.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
