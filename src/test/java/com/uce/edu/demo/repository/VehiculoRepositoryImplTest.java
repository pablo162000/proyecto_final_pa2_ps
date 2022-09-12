package com.uce.edu.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Vehiculo;

@SpringBootTest
class VehiculoRepositoryImplTest {

	@Autowired
	private IVehiculoRepository iVehiculoRepository;

	@Test
	void testBuscar() {

		List<Vehiculo> vehi = this.iVehiculoRepository.buscar("Fiesta", "Ford");
		for (Vehiculo v : vehi) {
			assertThat(v.getModelo()).isEqualTo("Fiesta");
			assertThat(v.getMarca()).isEqualTo("Ford");

		}

	}

	@Test
	void testBuscarPorPlaca() {
		List<Vehiculo> listaVehiculo = this.iVehiculoRepository.buscarMarca("Ford");
		for (Vehiculo v : listaVehiculo) {
			assertThat(v.getMarca()).isEqualTo("Ford");
		}

	}

	@Test
	void testBuscarVehiculosVip() {

		List<Vehiculo> listaVehiculo = this.iVehiculoRepository.buscarVehiculosVip("2022-09");
		assertNotNull(listaVehiculo);
	}

	@Test
	@Transactional
	@Rollback(false)
	void testActualizar() {

		Vehiculo v = new Vehiculo();
		v.setId(1);
		v.setMarca("Taurus");
		this.iVehiculoRepository.actualizar(v);
		assertThat(v.getMarca()).isEqualTo("Taurus");
	}

	@Test
	void testBuscarMarca() {
		List<Vehiculo> lista = this.iVehiculoRepository.buscarMarca("Ford");
		for (Vehiculo v : lista) {
			assertThat(v.getMarca()).isEqualTo("Ford");
		}

	}

	@Test
	void testBuscarPorId() {
		Vehiculo vehiculo = this.iVehiculoRepository.buscarPorId(1);
		assertThat(vehiculo.getId()).isEqualTo(1);
	}

	@Test
	@Transactional
	@Rollback(false)
	void testInsertarVehiculo() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca("KIL-123");
		this.iVehiculoRepository.insertarVehiculo(vehiculo);
		assertNotNull(this.iVehiculoRepository.buscarPorId(vehiculo.getId()));
	}

}
