package com.uce.edu.demo.repository;

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
class ClienteRepositoryImplTest {

	@Autowired
	private IClienteRepository iClienteRepository;

	@Test
	void testBuscarPorCedula() {
		Cliente cliente = this.iClienteRepository.buscarPorCedula("123");
		assertThat(cliente.getCedula()).isEqualTo("123");
	}

	@Test
	@Transactional
	@Rollback(false)
	void testInsertarCliente() {

		Cliente cliente = new Cliente();
		cliente.setApellido("Garcia");
		this.iClienteRepository.insertarCliente(cliente);
		assertNotNull(this.iClienteRepository.buscarPorId(cliente.getId()));

	}

	@Test
	void testBuscarPorApellido() {
		List<Cliente> listaCliente = this.iClienteRepository.buscarPorApellido("Washington");
		for (Cliente c : listaCliente) {
			assertThat(c.getApellido()).isEqualTo("Washington");
		}
	}

	@Test
	@Transactional
	@Rollback(false)
	void testActualizar() {
		Cliente cliente = new Cliente();
		cliente.setId(6);
		cliente.setNombre("Mario");

		this.iClienteRepository.actualizar(cliente);

		assertThat(cliente.getNombre()).isEqualTo("Mario");

	}

	@Test
	void testBuscarPorId() {
		Cliente cliente = this.iClienteRepository.buscarPorId(6);
		assertThat(cliente.getId()).isEqualTo(6);
	}

	@Test
	@Transactional
	@Rollback(false)
	void testActualizarPorCedula() {

		this.iClienteRepository.actualizarPorCedula("123", "Jose", null, null, null);
		Cliente c = this.iClienteRepository.buscarPorId(1);
		assertThat(c.getNombre()).isEqualTo("Jose");

	}

}
