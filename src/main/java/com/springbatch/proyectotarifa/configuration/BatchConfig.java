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

@Configuration
public class BatchConfig {

	@Bean
	JobCompletionNotificationListener listener() {
		return new JobCompletionNotificationListener();
	}

	@Bean
	Job job(JobRepository jobRepository, Step step, JobCompletionNotificationListener listener) {
		return new JobBuilder("db-to-csv", jobRepository).listener(listener).start(step).build();
	}

	@Bean
	Step step(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
			JdbcCursorItemReader<Tarifas> reader, TarifasProcessor processor, FlatFileItemWriter<Tarifas> writer) {
		return new StepBuilder("step1", jobRepository).<Tarifas, Tarifas>chunk(10, transactionManager).reader(reader)
				.processor(processor).writer(writer).allowStartIfComplete(true).build();
	}
}
