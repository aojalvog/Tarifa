package com.springbatch.proyectotarifa.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import com.springbatch.proyectotarifa.model.Tarifas;

@Component

public class Writer {

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
