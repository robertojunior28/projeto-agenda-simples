package Exception;

import javax.swing.JOptionPane;

import Janela.JanelaNovoUsuario;

public class UsuarioJaCadastradoExpection extends Exception {
	private JanelaNovoUsuario janela;
	
	public UsuarioJaCadastradoExpection() {
		JOptionPane.showMessageDialog(janela, "Não foi possível cadastrar, usuário já cadastrado!!");
	}

}
