package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Janela.JanelaLogin;
import Janela.JanelaNovoUsuario;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.MetodosPersistencia;
import ListaAquecimento.Usuario;

public class OuvinteCadastro implements ActionListener{
	
	private JanelaNovoUsuario janelaNovoUsuario;
	private String email;
	private String senha;
	
	public OuvinteCadastro(JanelaNovoUsuario janela) {
		this.janelaNovoUsuario = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e)  {
		String botao = e.getActionCommand();
		
		email = janelaNovoUsuario.getLogin().getText();
		senha = new String (janelaNovoUsuario.getSenha().getPassword());
		//--------------------------------------------------------------
		// Botão Cancelar
		
		if(botao.equalsIgnoreCase("cancelar")) {
			this.janelaNovoUsuario.dispose();
			new JanelaLogin();
		
		//--------------------------------------------------------------
		// Botão Cadastrar
			
		}else if(botao.equalsIgnoreCase("Cadastrar")) {
			try {
				CadastrarUsuario();
			} catch (Exception e1) {
				e1.getMessage();
			}
		}	
		
	}
	
	public void CadastrarUsuario() throws Exception{
		
		CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
		
		Usuario usuario = new Usuario();
		
		usuario.setNome(email);
		usuario.setSenha(senha);
		
		central.adicionarUsuario(usuario);
		MetodosPersistencia.salvarCentral(central, "arquivo.xml");
		
		JOptionPane.showMessageDialog(janelaNovoUsuario, "usuario cadastrado");

		janelaNovoUsuario.dispose();
		new JanelaLogin();
	}

}