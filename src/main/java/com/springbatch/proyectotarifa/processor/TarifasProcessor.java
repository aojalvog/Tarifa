package com.springbatch.proyectotarifa.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.proyectotarifa.model.Tarifas;

public class TarifasProcessor implements ItemProcessor<Tarifas, Tarifas> {
	@Override
	public Tarifas process(Tarifas tarifa) throws Exception {

		return tarifa;
	}

}
