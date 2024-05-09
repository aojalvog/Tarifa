package com.springbatch.proyectotarifa.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import com.springbatch.proyectotarifa.model.Tarifas;

/**
 * 
 * Esta clase representa un componente de Spring que proporciona funcionalidad
 * para escribir objetos de tipo Tarifas en un archivo plano. Utiliza un
 * FlatFileItemWriter para escribir los objetos Tarifas en un archivo CSV.
 */
@Component

public class Writer {
	/**
	 * Método que configura y devuelve un FlatFileItemWriter para escribir objetos
	 * Tarifas en un archivo plano CSV. Utiliza un extractor de campos
	 * BeanWrapperFieldExtractor para extraer los campos de los objetos Tarifas.
	 * Utiliza un agregador de líneas DelimitedLineAggregator para agregar los
	 * campos en una línea del archivo plano.
	 * 
	 * @return Un FlatFileItemWriter configurado para escribir objetos Tarifas en un
	 *         archivo plano CSV.
	 */

	@Bean
	FlatFileItemWriter<Tarifas> itemWriter() {
		BeanWrapperFieldExtractor<Tarifas> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] { "id", "nombre", "precio" });
		fieldExtractor.afterPropertiesSet();

		DelimitedLineAggregator<Tarifas> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(";");
		lineAggregator.setFieldExtractor(fieldExtractor);

		return new FlatFileItemWriterBuilder<Tarifas>().name("outputTarifas.csv")
				.resource(new PathResource("outputTarifas.csv")).lineAggregator(lineAggregator).build();
	}

}
