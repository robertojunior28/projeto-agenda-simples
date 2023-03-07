package Utilidades;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Janela.JanelaCadastroCanal;

public class ValidarSistema {

    //-------------------------------------------------------------------------------------------------------------------------------------------------
	// Método de adicionar campo de texto
	
	public static JTextField adicionarCaixaDeTexto(int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical) {
	   	JTextField campoTexto = new JTextField();
        campoTexto.setBounds(distaciamentoHorizontal,distanciamentoVertical,tamanhoHorizontal,tamanhoVertical);     
        return campoTexto;
	} 
    //-------------------------------------------------------------------------------------------------------------------------------------------------
	// Método de adicionar texto
	
	public static JLabel adicionarTexto(String nomeDoTexto, int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical) {
		
		JLabel campoTexto = new JLabel(nomeDoTexto);
        campoTexto.setBounds(distaciamentoHorizontal,distanciamentoVertical,tamanhoHorizontal,tamanhoVertical);     
        return campoTexto;
	}
    //-------------------------------------------------------------------------------------------------------------------------------------------------
	// Método de adicionar botão
	
	public static JButton adicionarBotao(String nomeDoBotao, int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical) {
		JButton botao = new JButton(nomeDoBotao);
		botao.setBounds(distaciamentoHorizontal, distanciamentoVertical, tamanhoHorizontal, tamanhoVertical);
		return botao;
	}
	public static JButton adicionarBotaoComImagem(String nomeDoBotao, String imagem, int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical) {
		JButton botao = new JButton(new ImageIcon(imagem));
		botao.setText(nomeDoBotao);
		botao.setBounds(distaciamentoHorizontal, distanciamentoVertical, tamanhoHorizontal, tamanhoVertical);
		return botao;
		
	}
    //-------------------------------------------------------------------------------------------------------------------------------------------------
	// Método de adicionar painel
	
	public static JPanel adicionarPainel(String nomePainel,int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical) {
		JPanel painel = new JPanel();
		painel.setBounds(distaciamentoHorizontal, distanciamentoVertical, tamanhoHorizontal, tamanhoVertical);
        painel.setLayout(null);
        painel.setBorder(new TitledBorder(nomePainel));
        return painel;
	}
    //-------------------------------------------------------------------------------------------------------------------------------------------------
	// Método de adicionar comboBox
	
	public static JComboBox<String> adicionarComboBox(String[] arrayBox, int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical){
		JComboBox<String> box = new JComboBox<String>(arrayBox);
		box.setBounds(distaciamentoHorizontal,distanciamentoVertical, tamanhoHorizontal, tamanhoVertical);
		return box;
	}
	public static void adicionarMesagem(String mensagem, Object janela) {
		JOptionPane.showMessageDialog((Component) janela, mensagem);
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------
	// Método de deixar campo de texto da tela JanelaCadastroCanal sem texto;
	
	public static void apagarCampoDeTexto(JanelaCadastroCanal janela) {
	
		JTextField nome = janela.getTxtNome();
		nome.setText("");
		JTextField link =  janela.getTxtLink();
		link.setText("");
		JTextField numero = janela.getTxtNumeroCanal();
		numero.setText("");
		JComboBox<String> box = janela.getBoxFormaAssistir();
		box.setSelectedItem("");
		
	}
}

