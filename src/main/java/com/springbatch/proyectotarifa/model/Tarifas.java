package com.springbatch.proyectotarifa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Tarifas {

	private Long id;
	private String nombre;
	private Double precio;
	private Double iva;

}
