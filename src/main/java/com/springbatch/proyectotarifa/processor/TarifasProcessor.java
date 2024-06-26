package com.springbatch.proyectotarifa.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.proyectotarifa.model.Tarifas;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Esta clase implementa la interfaz ItemProcessor de Spring Batch para procesar
 * objetos de tipo {@link Tarifas} En este caso, el procesador simplemente
 * devuelve la misma tarifa sin aplicar ninguna transformación.
 */
@Slf4j
public class TarifasProcessor implements ItemProcessor<Tarifas, Tarifas> {
	/**
	 * Método que procesa un objeto {@link Tarifas}. En este caso, el procesador
	 * simplemente devuelve la misma tarifa sin aplicar ninguna transformación.
	 * 
	 * @param tarifa La tarifa a procesar.
	 * @return La misma tarifa sin cambios.
	 * @throws Exception Si ocurre algún error durante el procesamiento.
	 */

	@Override
	public Tarifas process(Tarifas tarifa) throws Exception {
		log.info("Entrando en el process");
		return tarifa;
	}

}
