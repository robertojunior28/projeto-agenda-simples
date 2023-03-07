package Janela;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Ouvintes.OuvinteLogin;
import Utilidades.ValidarSistema;

public class JanelaLogin extends JanelaLoginPadrao{
	private JTextField login;
	private JPasswordField senha;
	
	public JanelaLogin() {
		setSize(400,325);
		setLocationRelativeTo(null);
		adicionarTitulo("Dados Do Usuário");
		adicionarLabel();
		login = adicionarTextField();
		adicionarLabelSenha();
		senha= adicionarTextFieldSenha();
		adicionarBotoes();
		
		this.setVisible(true);
		
	}
	
	public void adicionarBotoes() {
		btEsquerdo = ValidarSistema.adicionarBotao("Entrar", 150, 220, 100, 30);
		add(btEsquerdo);

		OuvinteLogin ouvinteEsquerdo = new OuvinteLogin(this);
		btEsquerdo.addActionListener(ouvinteEsquerdo);
		
	}

	public JPasswordField getSenha() {
		return senha;
	}
	public JTextField getLogin() {
		return login;
	}
}