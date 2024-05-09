package com.springbatch.proyectotarifa.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 
 * Esta clase actúa como un componente de Spring y define un bean para el
 * procesador de tarifas.
 */

@Component
public class Processor {

	/**
	 * Método que configura y devuelve un TarifasProcessor.
	 * 
	 * @return Un {@link TarifasProcessor} configurado.
	 */

	@Bean
	TarifasProcessor tarifasProcessor() {
		return new TarifasProcessor();
	}
}