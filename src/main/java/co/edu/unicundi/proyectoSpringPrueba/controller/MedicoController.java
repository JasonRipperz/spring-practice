package co.edu.unicundi.proyectoSpringPrueba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.proyectoSpringPrueba.dto.ExceptionResponse;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.FieldValidationException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ObjectNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.RepeatedObjectException;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.IMedicoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService medicoService;

	@GetMapping("/listar")
	public ResponseEntity<Page<Medico>> listar(Pageable pageable) {
		return new ResponseEntity<Page<Medico>>(medicoService.listar(pageable),
				medicoService.listar(pageable).getSize() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@GetMapping("/obtenerPorId/{id}")
	public ResponseEntity<Medico> obtenerPorId(@PathVariable int id) throws ObjectNotFoundException {
		return new ResponseEntity<Medico>(medicoService.obtenerPorId(id), HttpStatus.OK);
	}

	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Medico medico)
			throws RepeatedObjectException, FieldValidationException, ObjectNotFoundException {
		medicoService.guardar(medico);
		return new ResponseEntity<Object>("Médico creado", HttpStatus.CREATED);
	}

	@PutMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Medico medico)
			throws RepeatedObjectException, ObjectNotFoundException, FieldValidationException {
		medicoService.editar(medico);
		return new ResponseEntity<Object>("Médico editado", HttpStatus.OK);
	}

	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ObjectNotFoundException {
		medicoService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/listarPorNombre")
	public ResponseEntity<Page<Medico>> listarPorNombre(String nombre, Pageable pageable) {
		return new ResponseEntity<Page<Medico>>(medicoService.findByNombreIgnoreCase(nombre, pageable),
				medicoService.findByNombreIgnoreCase(nombre, pageable).getSize() > 0 ? HttpStatus.OK
						: HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/listarPorApellido")
	public ResponseEntity<Page<Medico>> listarPorApellido(String apellido, Pageable pageable) {
		return new ResponseEntity<Page<Medico>>(medicoService.findByApellidoIgnoreCase(apellido, pageable),
				medicoService.findByApellidoIgnoreCase(apellido, pageable).getSize() > 0 ? HttpStatus.OK
						: HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/listarPorCorreo")
	public ResponseEntity<Page<Medico>> listarPorCorreo(String correo, Pageable pageable) {
		return new ResponseEntity<Page<Medico>>(medicoService.findByCorreoIgnoreCase(correo, pageable),
				medicoService.findByCorreoIgnoreCase(correo, pageable).getSize() > 0 ? HttpStatus.OK
						: HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/listarPorDireccionPais")
	public ResponseEntity<Page<Medico>> listarPorDireccionPais(String pais, Pageable pageable) {
		return new ResponseEntity<Page<Medico>>(medicoService.findByDireccion_pais(pais, pageable),
				medicoService.findByDireccion_pais(pais, pageable).getSize() > 0 ? HttpStatus.OK
						: HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/listarPorDireccionCiudad")
	public ResponseEntity<Page<Medico>> listarPorDireccionCiudad(String ciudad, Pageable pageable) {
		return new ResponseEntity<Page<Medico>>(medicoService.findByDireccion_ciudad(ciudad, pageable),
				medicoService.findByDireccion_ciudad(ciudad, pageable).getSize() > 0 ? HttpStatus.OK
						: HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/listarPorDireccionBarrio")
	public ResponseEntity<Page<Medico>> listarPorDireccionBarrio(String barrio, Pageable pageable) {
		return new ResponseEntity<Page<Medico>>(medicoService.findByDireccion_barrio(barrio, pageable),
				medicoService.findByDireccion_barrio(barrio, pageable).getSize() > 0 ? HttpStatus.OK
						: HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/listarPorDireccionDetalle")
	public ResponseEntity<Page<Medico>> listarPorDireccionDetalle(String detalle, Pageable pageable) {
		return new ResponseEntity<Page<Medico>>(medicoService.findByDireccion_detalle(detalle, pageable),
				medicoService.findByDireccion_detalle(detalle, pageable).getSize() > 0 ? HttpStatus.OK
						: HttpStatus.NO_CONTENT);
	}
}
