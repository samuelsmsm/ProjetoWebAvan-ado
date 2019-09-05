package br.com.sistemadecadastro.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	
	public static Connection getConnection() {
		
		Connection con= null ;
		
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemaweb1","postgres","123456");
			System.out.println("Conectado ao banco com sucesso");
		} catch (SQLException e) {
			
			System.out.println("Não foi possível conectar ao banco:" + e.getMessage());
		}
		
		return con;
		
	} 
	
	
}
