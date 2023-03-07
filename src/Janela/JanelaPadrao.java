package Janela;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Utilidades.ValidarSistema;

public class JanelaPadrao extends JFrame{
	
	public JanelaPadrao(String nome, String titulo) {	
	    setTitle(nome);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel etiqueta = ValidarSistema.adicionarTexto(titulo, 0,7,490,60);
        etiqueta.setFont(new Font("Arial", Font.BOLD,40));
        etiqueta.setBackground(Color.GREEN);
        etiqueta.setOpaque(true);
        add(etiqueta);
	}
}
