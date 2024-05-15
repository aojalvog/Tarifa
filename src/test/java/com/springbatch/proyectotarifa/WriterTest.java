package com.springbatch.proyectotarifa;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.PathResource;

import com.springbatch.proyectotarifa.model.Tarifas;

@SpringBootTest
class WriterTest {

	@Autowired
	FlatFileItemWriter<Tarifas> flatFileItemWriter;

	/**
	 * Test {@link Tarifas} comprueba si el archivo se ha creado
	 * 
	 * @throws IOException
	 */
	@Test
	void writerTest() throws IOException {

		PathResource fichero = new PathResource(
				"C:\\Users\\6003036\\Desktop\\Caliope\\ficheros\\AutomatizacionCaliope\\ficherosSalida\\outputTarifas.csv");

		fichero.getFile().delete();

		flatFileItemWriter.open(new ExecutionContext());
		try {
			flatFileItemWriter.write(new Chunk<Tarifas>(new Tarifas(1L, "name1", 9.99)));

		} catch (Exception e) {
			e.printStackTrace();
		}

		assertThat(fichero.getFile()).exists();
	}

}
