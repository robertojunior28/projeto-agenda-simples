package Exception;

import java.awt.Component;

import javax.swing.JOptionPane;

import Janela.JanelaCadastroCanal;

public class CanalExisteException extends Exception {
	
	private JanelaCadastroCanal janela;

	public CanalExisteException() {
		JOptionPane.showMessageDialog(janela, "N�o � poss�vel cadastrar canal, canal j� cadastrado!!");
	}

}
