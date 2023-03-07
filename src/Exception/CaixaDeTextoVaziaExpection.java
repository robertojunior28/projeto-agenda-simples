package Exception;

import javax.swing.JOptionPane;

import Janela.JanelaCadastroCanal;

public class CaixaDeTextoVaziaExpection extends Exception{
	
	private JanelaCadastroCanal janela;
	
	public CaixaDeTextoVaziaExpection() {
		JOptionPane.showMessageDialog(janela, "Campo Vazio!!");
	}

}
