package br.com.sistemadecadastro.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemadecadastro.entidades.Usuario;

public class UsuarioDAO {
	
	private Connection con = Conexao.getConnection();
	
	
	public void cadastrar (Usuario usuario) {
		//Monta sql
		String sql = "INSERT INTO USUARIO (nome, login, senha) values (?,?,?)";
		
		// constroi o PreparedStatement com o sql
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Usuário CADASTRADO!");
			
		} catch (SQLException e) {
			
			System.out.println("Não foi possível CADASTRAR:" + e.getMessage());
		}
	}
	
	
	public void alterar (Usuario usuario) {
		//Monta sql
		String sql = "UPDATE USUARIO SET nome=?, login=?, senha=? WHERE id=?";
		
		// constroi o PreparedStatement com o sql
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Usuário ALTERADO com Sucesso!");
			
		} catch (SQLException e) {
			
			System.out.println("Não foi possível ALTERAR:" + e.getMessage());
		}
	}
	
	
	public void excluir (Usuario usuario) {
		//Monta sql
		String sql = "DELETE FROM USUARIO WHERE id=?";
		
		// constroi o PreparedStatement com o sql
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usuario.getId());
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Usuário EXCLUÍDO com Sucesso!");
			
		} catch (SQLException e) {
			
			System.out.println("Não foi possível EXCLUIR:" + e.getMessage());
		}
	}
	
	
	public List<Usuario> buscarTodos (){
		//Monta sql
		String sql = "SELECT * FROM USUARIO";
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		// constroi o PreparedStatement com o sql
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			
			ResultSet resultado = preparador.executeQuery();
			
			
			while(resultado.next()) {
				
				Usuario usu = new Usuario();
						
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
			
				lista.add(usu);
			}
			
			
			preparador.close();
			
			System.out.println("CONSULTA realizada com Sucesso!");
			
		} catch (SQLException e) {
			
			System.out.println("Não foi possível C:" + e.getMessage());
		}
		
		return lista;
	}
	
	
	
	public Usuario buscarPorId(Integer id) {
		String sql = "SELECT * FROM Usuario WHERE ID=?";
		
		Usuario usuario = null;
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				
				usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("Senha"));
			
			}
			
		} catch (SQLException e) {
			
			System.out.println("Não existe usuário para esse ID");
		} 
		
		return usuario;
	
	}
	
	
	public Usuario autenticar (Usuario usuario) {
		
		String sql = "SELECT * FROM Usuario WHERE login = ? and senha= ?";
		Usuario usuarioRetorno=null;
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(resultado.getInt("id"));
				usuarioRetorno.setNome(resultado.getString("nome"));
				usuarioRetorno.setLogin(resultado.getString("login"));
				usuarioRetorno.setSenha(resultado.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioRetorno;
	}
	
	public Boolean existeUsuario(Usuario usuario) {
		String sql = "SELECT * FROM Usuario WHERE login = ? and senha = ?";
	
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			return resultado.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	
	}

}
