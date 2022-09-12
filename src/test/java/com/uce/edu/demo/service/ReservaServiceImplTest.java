package com.uce.edu.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Reserva;

@SpringBootTest
class ReservaServiceImplTest {

	@Autowired
	private IReservaService reservaService;

	@Test
	@Transactional
	void testBuscar() {
		Reserva reserva = this.reservaService.buscar("123ZXY24");
		assertEquals("673", reserva.getNumeroTarjeta(), "Los datos no son iguales");
	}

	@Test
	@Transactional
	@Rollback(true)
	void testRetirarVehiculo() {
		Reserva reserva = this.reservaService.buscar("123ABC31");
		this.reservaService.retirarVehiculo(reserva.getNumero());

		assertEquals("E", this.reservaService.buscar("123ABC31").getEstado());
	}
	
	@Test
	@Transactional
	@Rollback(false)
	void testReservarVehiculo() {
		String numeroReserva = this.reservaService.reservarVehiculo("ABC-123", "123", LocalDateTime.of(2022, 10, 6,1,0), LocalDateTime.of(2022, 10, 7,1,0), "4213");

		assertNotNull(numeroReserva);
	}
	
	@Test
	void testCalcularValorTotal() {
		BigDecimal valorTotal =
		this.reservaService.calcularValorTotal(new BigDecimal(64), LocalDateTime.now(), LocalDateTime.of(2022, 10, 01, 01, 01));
		
		assertNotNull(valorTotal);

	}
	
	@Test
	void testCalcularNumeroDias() {
		Long dias = this.reservaService.calcularNumeroDias(LocalDateTime.of(2022, 10, 4,1,0), LocalDateTime.of(2022, 10, 5,1,0));
		assertEquals(1, dias);
	}
	
	@Test
	void testTransformarFecha() {
		String fecha = this.reservaService.transformarFecha(LocalDateTime.of(2022, 10, 4,1,0));
		assertEquals("2022-10-04 01:00", fecha);
	}

}
