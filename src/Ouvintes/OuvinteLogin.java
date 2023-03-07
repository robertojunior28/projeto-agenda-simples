package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Janela.JanelaLogin;
import Janela.JanelaLoginPadrao;
import Janela.JanelaMenu;
import Janela.JanelaNovoUsuario;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.MetodosPersistencia;
import ListaAquecimento.Usuario;

public class OuvinteLogin implements ActionListener{
	
	private JanelaLogin janelaLogin;
	private String email;
	private String senha;
	
	public OuvinteLogin(JanelaLogin janela) {
		this.janelaLogin = janela;
		
	}

	public void actionPerformed(ActionEvent e){
		
		String botao = e.getActionCommand();
		//tratar p�ssiveis excess�es
		email = janelaLogin.getLogin().getText();
		senha = new String (janelaLogin.getSenha().getPassword());
		
		//--------------------------------------------------------------
		// Botão Cadastrar
		
		if(botao.equalsIgnoreCase("cadastrar")) {
			janelaLogin.dispose();
			new JanelaNovoUsuario();
		
		//--------------------------------------------------------------
		// Botão Entrar
			
		}else if(botao.equalsIgnoreCase("entrar")) {

			CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
			
			if(central.validarEntrada(email, senha)) {	
				janelaLogin.dispose();
				new JanelaMenu();
			}else {
				JOptionPane.showMessageDialog(janelaLogin, "usuario invalido");
			}
		}
		
	}


}
