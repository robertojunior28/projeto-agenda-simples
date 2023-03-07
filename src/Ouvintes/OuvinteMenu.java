package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Janela.JanelaCadastrarProgramas;
import Janela.JanelaCadastroCanal;
import Janela.JanelaListaDeContato;
import Janela.JanelaListarAlterarProgramas;
import Janela.JanelaLogin;
import Janela.JanelaMenu;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.GeradorRelatorio;
import ListaAquecimento.Mensageiro;
import ListaAquecimento.MetodosPersistencia;
import ListaAquecimento.Usuario;
import Utilidades.ValidarSistema;

public class OuvinteMenu implements ActionListener{
	
	private JanelaMenu janela;
	
	public OuvinteMenu(JanelaMenu janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
		
		String botao = e.getActionCommand();
		
		CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
		
		if(botao.equalsIgnoreCase("Programação")) {
			
			int resposta = JOptionPane.showConfirmDialog(janela, "Deseja gerar pdf da programação da semana");
			
			if(resposta == JOptionPane.YES_OPTION) {
				
				String email = JOptionPane.showInputDialog("Informe seu email");
				
				Usuario testeUsuario = new Usuario();
				testeUsuario.setNome(email);
				
				Usuario usuario = central.recuperarUsuario(testeUsuario);
				
				if(usuario != null) {	
					GeradorRelatorio.obterProgramacaoDeUmCanal();
					Mensageiro.enviarProgramacaoDeHoje(usuario.getNome());
					JOptionPane.showMessageDialog(janela, "Programação gerada com sucesso");
				}else
					ValidarSistema.adicionarMesagem("Usuário Inválido", janela);
			}else
				ValidarSistema.adicionarMesagem("Ocorreu um erro!!", janela);
			
		}
		if(botao.equalsIgnoreCase("Canais")) {
			janela.dispose();
			new JanelaCadastroCanal();
		}
		if(botao.equalsIgnoreCase("Programas")) {
			janela.dispose();
			
			if(central.getCanais().isEmpty()) {
				//controle caso não exista canais
				JOptionPane.showMessageDialog(janela, "Erro!! Crie um canal");
				new JanelaMenu();
				
			}else {
				new JanelaListarAlterarProgramas();
			}
		}
		if(botao.equalsIgnoreCase("Contatos")) {
			janela.dispose();
			new JanelaListaDeContato();
		}
		if(botao.equalsIgnoreCase("Sair")) {
			janela.dispose();
			new JanelaLogin();
		}
	}

}
