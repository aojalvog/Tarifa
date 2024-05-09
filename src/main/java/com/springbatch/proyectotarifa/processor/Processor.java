package com.springbatch.proyectotarifa.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Processor {

	@Bean
	TarifasProcessor tarifasProcessor() {
		return new TarifasProcessor();
	}
}