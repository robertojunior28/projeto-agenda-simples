package Exception;

import javax.swing.JOptionPane;

import Janela.JanelaNovoUsuario;

public class UsuarioJaCadastradoExpection extends Exception {
	private JanelaNovoUsuario janela;
	
	public UsuarioJaCadastradoExpection() {
		JOptionPane.showMessageDialog(janela, "N�o foi poss�vel cadastrar, usu�rio j� cadastrado!!");
	}

}
