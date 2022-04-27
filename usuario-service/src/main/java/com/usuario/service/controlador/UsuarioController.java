package com.usuario.service.controlador;

import java.util.List;
import java.util.Map;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}

	@CircuitBreaker(name = "carrosCB", fallbackMethod = "fallbackGetCarros")
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Carro> carros = usuarioService.getCarros(id);
		return ResponseEntity.ok(carros);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallbackGetMotos")
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Moto> motos = usuarioService.getMotos(id);
		return ResponseEntity.ok(motos);
	}

	@CircuitBreaker(name = "carrosCB", fallbackMethod = "fallbackSaveCarros")
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId,@RequestBody Carro carro){
		Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallbackSaveMotos")
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId,@RequestBody Moto moto){
		Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevaMoto);
	}

	@CircuitBreaker(name = "todosCB", fallbackMethod = "fallbackGetTodos")
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){
		Map<String,Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	}

	private  ResponseEntity<List<Carro>> fallbackGetCarros(@PathVariable("usuarioId") int id, RuntimeException ex){
		return new ResponseEntity("El usuario: " + id + " tiene carros", HttpStatus.OK);
	}

	private ResponseEntity<Carro> fallbackSaveCarros(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro, RuntimeException ex){
		return new ResponseEntity("El usuario: " + usuarioId + " no tiene carros", HttpStatus.OK);
	}

	private ResponseEntity<List<Moto>> fallbackGetMotos(@PathVariable("usuarioId") int id, RuntimeException ex){
		return new ResponseEntity("El usuario: " + id + " tiene motos", HttpStatus.OK);
	}

	private ResponseEntity<Moto> fallbackSaveMotos(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto, RuntimeException ex){
		return new ResponseEntity("El usuario: " + usuarioId + " no tiene motos", HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> fallbackGetTodos(@PathVariable("usuarioId") int usuarioId, RuntimeException ex){
		return new ResponseEntity("El usuario: " + usuarioId + " no tiene vehiculos", HttpStatus.OK);
	}


}