package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Janela.JanelaListaDeContato;
import Janela.JanelaMenu;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.Contato;
import ListaAquecimento.Mensageiro;
import ListaAquecimento.MetodosPersistencia;
import Utilidades.ValidarSistema;

public class OuvinteContatos implements ActionListener {
	
	private JanelaListaDeContato janela;
	private String txtNome;
	private String txtEmail;
	
	
	
	public OuvinteContatos(JanelaListaDeContato janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
	   
		String botao = e.getActionCommand();
		
		CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
		// Retorna o indice da linha que está sendo selecionada
		int linhaSelecionada = janela.getTabela().getSelectedRow();
		
		txtNome = janela.getTxtNome().getText();
		txtEmail = janela.getTxtEmail().getText();
		
		if(botao.equalsIgnoreCase("Novo")) {
	        	 
		    	try {	
		    		Contato contato = new Contato(txtNome, txtEmail);
					central.adicionarContato(contato);
					JOptionPane.showMessageDialog(janela, "Contato cadastrado");
					MetodosPersistencia.salvarCentral(central, "arquivo.xml");
					
					janela.dispose();
					new JanelaListaDeContato();
					
				
					
				} catch (Exception e1) {
					e1.getMessage();
				}
			
		}else if(botao.equalsIgnoreCase("Excluir")) {
			
			if(linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(janela, "Selecionei uma Linha");
			
			}else {
				int resposta = JOptionPane.showConfirmDialog(janela, "Têm certeza que quer excluir");
			
				if(resposta == JOptionPane.YES_OPTION) {
					
					central.getContatos().remove(linhaSelecionada);
					DefaultTableModel modelo = (DefaultTableModel) janela.getModelo();
					modelo.removeRow(linhaSelecionada);
					ValidarSistema.adicionarMesagem("Canal Excluido", janela);
					janela.getTabela().repaint();
				}
				
			}		
			
		}else if(botao.equalsIgnoreCase("Enviar")) {
			
			janela.getBotaoExcluir().setEnabled(false);
			int resposta = JOptionPane.showConfirmDialog(janela, "Enviar programação para este contato");
			
			Contato c = central.getContatos().get(linhaSelecionada);
			
			if(resposta == JOptionPane.YES_OPTION) {
				
				Mensageiro.enviarProgramacaoDeHoje(c.getEmail());
				ValidarSistema.adicionarMesagem("Programação enviada com sucesso", janela);
				
				
			}
			
		}else if(botao.equalsIgnoreCase("Voltar ao menu")) {
			janela.dispose();
			new JanelaMenu();
		}
		
		//MetodosPersistencia.salvarCentral(central, "arquivo.xml");	
	}

}
