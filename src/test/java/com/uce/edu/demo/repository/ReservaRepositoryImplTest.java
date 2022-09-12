package com.uce.edu.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Reserva;

class ReservaRepositoryImplTest {

	@Autowired
	private IReservaRepository reservaRepository;

	@Test
	@Transactional
	@Rollback(true)
	void testInsertar() {
		Reserva r = new Reserva();
		r.setEstado("G");
		r.setFechaFin(LocalDateTime.of(2022, 12, 14, 14, 12));
		r.setFechaInicio(LocalDateTime.of(2022, 12, 10, 14, 12));
		r.setNumero("178ASW2");
		r.setNumeroTarjeta("128473");
		r.setSubtotal(new BigDecimal(140));
		r.setValorIva(new BigDecimal(10));
		r.setValorTotal(new BigDecimal(150));

		this.reservaRepository.insertar(r);

		assertNotNull(r, "La reserva está en null");
		
		
	}

	@Test
	@Transactional
	void testLeer() {
		Reserva r = this.reservaRepository.leer("123PPV22");
		assertEquals("12", r.getNumeroTarjeta(), "Los datos no son iguales");
	}

	@Test
	@Transactional
	@Rollback(true)
	void testActualizar() {
		Reserva r = this.reservaRepository.leer("123PPV22");
		r.setEstado("G");
		this.reservaRepository.actualizar(r);
		assertEquals("G", r.getEstado());
	}

	@Test
	@Transactional
	void testBuscarPorRangoFechas() {
		List<Reserva> reservas = this.reservaRepository.buscarPorRangoFechas(LocalDateTime.of(2022, 8, 10, 14, 12),
				LocalDateTime.of(2022, 12, 14, 14, 12));
		for (Reserva reserva : reservas) {
			assertThat(reserva.getFechaInicio().compareTo(LocalDateTime.of(2022, 8, 10, 14, 12)) >= 0 && reserva.getFechaFin().compareTo(LocalDateTime.of(2022, 12, 14, 14, 12)) <=0);
		}
	}

	@Test
	@Transactional
	void testBuscarClientesVip() {
		List<Reserva> reservas = this.reservaRepository.buscarClientesVip();
		for (Reserva reserva : reservas) {
			assertNotNull(reserva.getCliente().getCedula());
		}
	}
	
	

}
