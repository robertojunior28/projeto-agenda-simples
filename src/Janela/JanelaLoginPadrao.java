package Janela;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Ouvintes.OuvinteLogin;
import Utilidades.ValidarSistema;

public abstract class JanelaLoginPadrao extends JanelaPadrao{
	
	protected JButton btEsquerdo;
	protected JButton btDireito;
	private JTextField login;
	private JPasswordField senha;
	
	//gets e set para diferenciar

	public JTextField getLogin() {
		return login;
	}
	
	public void setLogin(JTextField login) {
		this.login = login;
	}
	
	public JPasswordField getSenha() {
		return senha;
	}
	
	public void setSenha(JPasswordField senha) {
		this.senha = senha;
	}

	public JButton getBtEsquerdo() {
		return btEsquerdo;
	}
	
	public void setBtEsquerdo(JButton btEsquerdo) {
		this.btEsquerdo = btEsquerdo;
	}
	
	public JButton getBtDireito() {
		return btDireito;
	}
	
	public void setBtDireito(JButton btDireito) {
		this.btDireito = btDireito;
	}
	
	//m�todos da classe
	
	public JButton getBtEntrar() {
		return btEsquerdo;
	}


	public void setBtEntrar(JButton btEntrar) {
		this.btEsquerdo = btEntrar;
	}

	public JButton getBtNovoUsuario() {
		return btDireito;
	}

	public void setBtNovoUsuario(JButton btNovoUsuario) {
		this.btDireito = btNovoUsuario;
	}

	public JanelaLoginPadrao() {
		super("Login", "Login");

		
	}
	
	public void adicionarTitulo(String nome) {
		JLabel titulo = ValidarSistema.adicionarTexto(nome, 0, 80, 400, 40);
		titulo.setFont(new Font("Arial", Font.BOLD, 15));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		
	}
	public void adicionarLabel() {
		JLabel lbTitulo = ValidarSistema.adicionarTexto("E-maill", 20, 130, 40, 30);
		lbTitulo.setToolTipText("Digite seu email");
		add(lbTitulo);
	}
	
	public JTextField adicionarTextField() {
		login = ValidarSistema.adicionarCaixaDeTexto(65, 130, 300, 30);
		OuvinteDoEmail ouvinte = new OuvinteDoEmail();
		login.addKeyListener(ouvinte);
		add(login);
		return login;
		
	}
	
	public void adicionarLabelSenha() {
		JLabel senha = new JLabel("Senha");
		senha.setBounds(20, 170, 40, 30);
		senha.setToolTipText("Digite a senha");
		add(senha);
	}

	public JPasswordField adicionarTextFieldSenha() {
		senha = new JPasswordField();
		senha.setBounds(65, 170, 300, 30);
		add(senha);
		return senha;
		
		
	}
		
	public abstract void adicionarBotoes();
	
	private class OuvinteDoEmail implements KeyListener{

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if(!Character.isLetter(c) && !Character.isDigit(c) && c != '@' && c != '.') {
				e.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}	
	}
}