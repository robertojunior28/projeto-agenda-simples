package Janela;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.MetodosPersistencia;
import ListaAquecimento.ProgramaDeTv;
import ListaAquecimento.Usuario;
import Ouvintes.OuvinteCadastro;
import Ouvintes.OuvinteMenu;
import Utilidades.ValidarSistema;

public class JanelaMenu extends JanelaPadrao{
	
	JanelaLogin janela;
	
	public JanelaMenu() {
		//comandos da jaela padrão.
		super("Menu", "Menu");
		this.setVisible(true);
		botao("Programação","fotos/planejamento.png", 115);
		botao("Canais", "fotos/televisao.png",160);
		botao("Programas","fotos/claquete.png", 205);
		botao("Contatos","fotos/contatos.png", 250);
		botao("Sair", "fotos/sair.png",295);
		
		JLabel texto = ValidarSistema.adicionarTexto("Seja bem vindo(a), a Agenda de TV.",15,80,250,20);
		texto.setFont(new Font("Arial",Font.ITALIC, 15));
		add(texto);
		
	    JMenuBar barraDeMenu = new JMenuBar();
		setJMenuBar(barraDeMenu);
		
		JMenu menuConfiguracao = new JMenu("Editar");
		barraDeMenu.add(menuConfiguracao);
	
		JMenuItem alterarEmail = new JMenuItem("Alterar Email");
		alterarEmail.setIcon(new ImageIcon("fotos/correspondencia.png"));
		menuConfiguracao.add(alterarEmail);
		alterarEmail.addActionListener(new OuvinteMenuBar());
		
		JMenuItem alterarSenha = new JMenuItem("Alterar Senha");
		alterarSenha.setIcon(new ImageIcon("fotos/do-utilizador.png"));
		menuConfiguracao.add(alterarSenha);
		alterarSenha.addActionListener(new OuvinteMenuBar());
			
		JMenuItem excluirConta = new JMenuItem("Excluir Conta");
		excluirConta.setIcon(new ImageIcon("fotos/deletar-usuario (1).png"));
		menuConfiguracao.add(excluirConta);
		excluirConta.addActionListener(new OuvinteMenuBar());
		
		setVisible(true);
	}
	
	public class OuvinteMenuBar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			 
			String opcao = e.getActionCommand();
			 
		     CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
		     
		     String senha = JOptionPane.showInputDialog("Informe sua senha");
		     
		     if(senha != null) {
		        Usuario u = new Usuario();
				u.setSenha(new String (senha));
			
				Usuario usuario = central.recuperarUsuario(u);
						 
				switch (opcao) { 
					case "Alterar Senha": 
					case "Alterar Email":	
						if(usuario != null) {
							String novaAlteracao = null;
							
							Usuario usuarioTeste = new Usuario();
						
							if(opcao.equals("Alterar Senha")) {
								novaAlteracao = JOptionPane.showInputDialog("Informe a nova senha");
							}else {
								novaAlteracao = JOptionPane.showInputDialog("Informe o novo email");
								usuarioTeste.setNome(novaAlteracao);
							}
							
							Usuario usuarioDoEmailCadastrado = central.recuperarUsuario(usuarioTeste);
							
							if(novaAlteracao != null) {
					
								if(opcao.equals("Alterar Senha")){
									usuario.setSenha(novaAlteracao);
									ValidarSistema.adicionarMesagem("Senha alterada com sucesso", janela);
								}else if (opcao.equals("Alterar Email") && usuarioDoEmailCadastrado == null){
									usuario.setNome(novaAlteracao);
									ValidarSistema.adicionarMesagem("Email alterada com sucesso", janela);
								}else
									ValidarSistema.adicionarMesagem("ERRO", this);
							}else {
								ValidarSistema.adicionarMesagem("Ocorreu um erro", janela);
							}	
						}else {
							ValidarSistema.adicionarMesagem("Usuário não encontrado", janela);
						}
						break;
					case "Excluir Conta":
						
						int resposta = JOptionPane.showConfirmDialog(janela, "Têm certeza que quer excluir está conta");
					
						if(resposta == JOptionPane.YES_OPTION) {
							if(usuario != null) {
								
							central.getUsuarioLista().remove(usuario);
							for(int i = 0; i < central.getProgramasFavoritos().size(); i++) {
								central.getProgramasFavoritos().remove(i);
							}
							ValidarSistema.adicionarMesagem("Conta excluida com sucesso", janela);
							dispose();
							new JanelaNovoUsuario();
							}else {
							ValidarSistema.adicionarMesagem("Senha incorreta", janela);
							}
						}
		    
				 } 
				 	MetodosPersistencia.salvarCentral(central, "arquivo.xml");
			  }
			    
		}
			
	}
	
	//dentro da opção de 
	
	public void botao(String nomeDoBotao,String imagem,int distanciamento) {
		JButton botao = ValidarSistema.adicionarBotaoComImagem(nomeDoBotao,imagem, 40, distanciamento, 300, 30);
		add(botao);
		botao.addActionListener(new OuvinteMenu(this));
		
	}

}
