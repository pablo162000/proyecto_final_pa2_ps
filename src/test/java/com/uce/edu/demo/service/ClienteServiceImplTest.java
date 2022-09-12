package com.uce.edu.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Cliente;

@SpringBootTest
class ClienteServiceImplTest {
	
	@Autowired
	private IClienteService iClienteService;

	@Test
	@Transactional
	@Rollback(false)
	void testEliminar() {
		this.iClienteService.eliminar(8);
		assertNull(this.iClienteService.buscarPorId(8));

	}
	
	@Test
	void testBuscarPorCedula() {
		
		Cliente cliente = this.iClienteService.buscarPorCedula("123236");
	
		assertEquals(cliente.getApellido(), "Washington");
	}

	@Test
	@Transactional
	@Rollback(false)
	void testInsertarCliente() {
		Cliente cliente = new Cliente();
		cliente.setApellido("");
		cliente.setApellido(null);
		this.iClienteService.insertarCliente(cliente);
		assertNotNull(this.iClienteService.buscarPorId(cliente.getId()));

	}

	@Test
	@Transactional
	@Rollback(false)
	void testActualizar() {

		this.iClienteService.actualizarPorCedula("123", "Jose", null, null, null);
		Cliente c = this.iClienteService.buscarPorId(1);
	    assertThat(c.getNombre()).isEqualTo("Jose");
		
	}

	@Test
	void testBuscarPorId() {
		Cliente cliente = this.iClienteService.buscarPorId(6);
		assertThat(cliente.getId()).isEqualTo(6);
	}

	@Test
	void testBuscarPorApellido() {
		List<Cliente> listaCliente = this.iClienteService.buscarPorApellido("Washington");
		for (Cliente c : listaCliente) {
			assertThat(c.getApellido()).isEqualTo("Washington");
		}
	}
}
