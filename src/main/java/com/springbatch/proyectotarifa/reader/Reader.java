package com.springbatch.proyectotarifa.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;

import com.springbatch.proyectotarifa.model.Tarifas;

public class Reader {

	DataSource dataSource;

	@Bean
	public JdbcCursorItemReader<Tarifas> itemReader(DataSource dataSource) {
		JdbcCursorItemReader<Tarifas> itemReader = new JdbcCursorItemReader<>();
		itemReader.setName("tarifasItemReader");
		itemReader.setDataSource(dataSource);
		itemReader.setSql("SELECT ID, NOMBRE, PRECIO FROM TARIFAS");
		itemReader.setRowMapper((rs, rowNum) -> {
			Tarifas tarifas = new Tarifas();
			tarifas.setId(rs.getLong("ID"));
			tarifas.setNombre(rs.getString("NOMBRE"));
			tarifas.setPrecio(rs.getDouble("PRECIO"));
			return tarifas;
		});
		return itemReader;
	}
}
