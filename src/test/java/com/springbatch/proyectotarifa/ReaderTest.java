package com.springbatch.proyectotarifa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springbatch.proyectotarifa.model.Tarifas;

@SpringBootTest
class ReaderTest {

	@Autowired
	JdbcCursorItemReader<Tarifas> cursorItemReader;

	/**
	 * Test del {@link Reader}
	 * 
	 * @throws Exception
	 */
	@Test
	void readerTest() throws Exception {

		List<Tarifas> listTarifas = new ArrayList<>();
		cursorItemReader.open(new ExecutionContext());
		while (true) {
			Tarifas tarifa = cursorItemReader.read();
			if (tarifa == null) {
				break;
			}
			listTarifas.add(tarifa);

		}

		cursorItemReader.close();
		assertThat(listTarifas).hasSize(5);
	}

}
