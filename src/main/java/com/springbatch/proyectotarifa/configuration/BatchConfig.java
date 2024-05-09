package com.springbatch.proyectotarifa.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.springbatch.proyectotarifa.listener.JobCompletionNotificationListener;
import com.springbatch.proyectotarifa.model.Tarifas;
import com.springbatch.proyectotarifa.processor.TarifasProcessor;

/**
 * 
 * Esta clase representa la configuración de Spring Batch para el procesamiento
 * de trabajos.
 */

@Configuration
public class BatchConfig {

	/**
	 * Método que configura y devuelve un {@link JobCompletionNotificationListener}
	 * para manejar eventos de finalización de trabajos.
	 * 
	 * @return Un JobCompletionNotificationListener configurado.
	 */
	@Bean
	JobCompletionNotificationListener listener() {
		return new JobCompletionNotificationListener();
	}

	/**
	 * Método que configura y devuelve un Job que ejecuta un conjunto de pasos.
	 * 
	 * @param jobRepository El repositorio de trabajos que gestiona los trabajos
	 *                      ejecutados.
	 * @param step          El paso a ejecutar dentro del trabajo.
	 * @param listener      El listener para el evento de finalización del trabajo.
	 * @return Un Job configurado.
	 */

	@Bean
	Job job(JobRepository jobRepository, Step step, JobCompletionNotificationListener listener) {
		return new JobBuilder("db-to-csv", jobRepository).listener(listener).start(step).build();
	}

	/**
	 * Método que configura y devuelve un Step para el procesamiento de los datos.
	 * 
	 * @param jobRepository      El repositorio de trabajos que gestiona los pasos
	 *                           ejecutados.
	 * @param transactionManager El gestor de transacciones para el paso.
	 * @param reader             El lector de datos desde la base de datos.
	 * @param processor          El procesador de los datos leídos.
	 * @param writer             El escritor de los datos procesados.
	 * @return Un Step configurado.
	 */

	@Bean
	Step step(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
			JdbcCursorItemReader<Tarifas> reader, TarifasProcessor processor, FlatFileItemWriter<Tarifas> writer) {
		return new StepBuilder("step1", jobRepository).<Tarifas, Tarifas>chunk(10, transactionManager).reader(reader)
				.processor(processor).writer(writer).allowStartIfComplete(true).build();
	}
}
