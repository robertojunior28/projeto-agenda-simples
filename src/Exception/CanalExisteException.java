package Exception;

import java.awt.Component;

import javax.swing.JOptionPane;

import Janela.JanelaCadastroCanal;

public class CanalExisteException extends Exception {
	
	private JanelaCadastroCanal janela;

	public CanalExisteException() {
		JOptionPane.showMessageDialog(janela, "Não é possível cadastrar canal, canal já cadastrado!!");
	}

}
