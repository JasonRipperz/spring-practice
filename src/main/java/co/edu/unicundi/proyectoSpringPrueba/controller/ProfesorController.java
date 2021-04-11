package co.edu.unicundi.proyectoSpringPrueba.controller;

import java.lang.reflect.Array;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@RestController
@RequestMapping("/profesores")
@Api(value = "Servicios Rest de docentes")
public class ProfesorController {
	
	@Autowired
	private IProfesorService profesorService;
	
	
	@ApiOperation(value = "Listar todos los docentes", notes = "Servicio para listar todos los docentes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se obtienen correctamente los docentes", response = List.class),
            @ApiResponse(code = 204, message = "No Content.No hay registro de docentes en sistema)", response = Array.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema",  response = ExceptionResponse.class) })
	@GetMapping("/listar")
	public ResponseEntity<List<Profesor>> listar() { 
		return new ResponseEntity<List<Profesor>> (profesorService.listar(), profesorService.listar().size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Obtener un docente por id", notes = "Servicio para obtener un docente por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se retorno correctamente el docente", response = Profesor.class),
            @ApiResponse(code = 404, message = "NOT_FOUND. No se encontro un docente asociado al documento ingresado)", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema",  response = ExceptionResponse.class) })
	@GetMapping("/obtenerPorId/{id}")
	public ResponseEntity<Profesor> obtenerPorId(@PathVariable int id) throws ObjectNotFoundException {
		return new ResponseEntity<Profesor> (profesorService.obtenerPorId(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Guardar un docente", notes = "Servicio para guardar un docente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Se guardó exitosamente el docente", response = Object.class),
            @ApiResponse(code = 409, message = "Conflict. Ya existe un usuario con el mismo documento ingresado)", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema",  response = ExceptionResponse.class) })
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Profesor profesor) throws RepeatedObjectException, FieldValidationException {
		profesorService.guardar(profesor);
		return new ResponseEntity<Object> ("Profesor creado", HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Obtener un docente", notes = "Servicio para obtener un docente por su cédula")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se retorno correctamente el docente", response = Profesor.class),
            @ApiResponse(code = 404, message = "NOT_FOUND. No se encontro un docente asociado al documento ingresado)", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema",  response = ExceptionResponse.class) })
	@GetMapping("/obtener/{cedula}")
	public ResponseEntity<Profesor> obtener(@PathVariable String cedula) throws ObjectNotFoundException {
		profesorService.obtener(cedula);
		return new ResponseEntity<Profesor> (profesorService.obtener(cedula), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Editar un docente", notes = "Servicio para editar un docente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se modificó correctamente el docente", response = Object.class),
            @ApiResponse(code = 404, message = "NOT_FOUND. No se encontro un docente asociado al id", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "CONFLICT. Ya existe un usuario con el mismo documento ingresado)", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema",  response = ExceptionResponse.class) })
	@PutMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Profesor profesor) throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
		profesorService.editar(profesor);
		return new ResponseEntity<Object> ("Profesor editado",HttpStatus.OK);
	}
	
	@ApiOperation(value = "Eliminar un docente", notes = "Servicio para eliminar un docente")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO_CONTENT. Se modificó correctamente el docente"),
            @ApiResponse(code = 404, message = "NOT_FOUND. No se encontro un docente asociado al id", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema",  response = ExceptionResponse.class) })
	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ObjectNotFoundException {
		profesorService.eliminar(id);
		return new ResponseEntity<Object> (HttpStatus.NO_CONTENT);	
	}
	
}
