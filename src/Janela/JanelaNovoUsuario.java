package Janela;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Ouvintes.OuvinteCadastro;
import Utilidades.ValidarSistema;

public class JanelaNovoUsuario extends JanelaLoginPadrao{
	

	public JanelaNovoUsuario() {
		setSize(400,325);
		setLocationRelativeTo(null);
		adicionarTitulo("Cadastro De Usuário");
		adicionarLabel();
		adicionarTextField();
		adicionarLabelSenha();
		adicionarTextFieldSenha();
		adicionarBotoes();
		
		this.setVisible(true);
		
	}
	
	public void adicionarBotoes() {
		btDireito = ValidarSistema.adicionarBotao("Cadastrar",150, 220, 100, 30);
		add(btDireito);

		OuvinteCadastro ouvinteDireito = new OuvinteCadastro(this);
		
		btDireito.addActionListener(ouvinteDireito);
		
	}

}

