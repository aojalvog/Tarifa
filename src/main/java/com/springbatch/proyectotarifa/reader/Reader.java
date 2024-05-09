package com.springbatch.proyectotarifa.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.springbatch.proyectotarifa.model.Tarifas;

@Component
public class Reader {

	@Bean
	JdbcCursorItemReader<Tarifas> itemReader(DataSource dataSource) {
		JdbcCursorItemReader<Tarifas> itemReader = new JdbcCursorItemReader<>();
		itemReader.setName("tarifasItemReader");
		itemReader.setDataSource(dataSource);
		itemReader.setSql("SELECT * FROM TARIFAS");
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
