package br.com.sistemadecadastro.teste;

import java.util.List;

import br.com.sistemadecadastro.entidades.Usuario;
import br.com.sistemadecadastro.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		
		
		//testCadastrar();
		//testAlterar();
		//testExcluir();
		//testBuscarTodos();
		//testAutenticar();
		//testBuscarPorId();
	}

	private static void testCadastrar() {
		Usuario usu = new Usuario();
		usu.setNome("Mateu");
		usu.setLogin("mt");
		usu.setSenha("mt123");
		
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.cadastrar(usu);
	}
	
	private static void testAlterar() {
		
		Usuario usu = new Usuario();
		usu.setId(1);
		usu.setNome("jao");
		usu.setLogin("jo");
		usu.setSenha("jao123");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.alterar(usu);
		
	}
	


	private static void testExcluir(){
		
		Usuario usu = new Usuario();
		usu.setId(1);
		
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.excluir(usu);
		
	}
	
	private static void testBuscarTodos() {
		
		UsuarioDAO usuDao = new UsuarioDAO();
		List<Usuario> listaResultado = usuDao.buscarTodos();
		
		
		for (Usuario u: listaResultado) {
			System.out.println(u.getId()+ " " + u.getNome() + " " + u.getLogin() + " " + u.getSenha()+ "");
		}
	}
	
	public static void testAutenticar() {
		Usuario usuario = new Usuario();
		usuario.setLogin("mt");
		usuario.setSenha("mt123");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		System.out.println(usuarioDAO.autenticar(usuario).getNome());
	}
	
	public static void testBuscarPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		System.out.println(usuarioDAO.buscarPorId(3).getNome());
	}
	

}
