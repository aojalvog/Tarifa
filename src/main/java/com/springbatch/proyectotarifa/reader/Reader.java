package com.springbatch.proyectotarifa.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.springbatch.proyectotarifa.model.Tarifas;

/**
 * 
 * Esta clase representa un componente de Spring que proporciona funcionalidad
 * para leer objetos de tipo Tarifas desde una base de datos utilizando JDBC.
 */
@Component
public class Reader {

	/**
	 * Método que configura y devuelve un JdbcCursorItemReader para leer objetos
	 * {@link Tarifas} desde una base de datos. Utiliza un DataSource para acceder a
	 * la base de datos.
	 * 
	 * @param dataSource El DataSource que proporciona la conexión a la base de
	 *                   datos.
	 * @return Un JdbcCursorItemReader configurado para leer objetos Tarifas desde
	 *         una base de datos.
	 */

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
