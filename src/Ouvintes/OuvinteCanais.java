package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Janela.JanelaCadastroCanal;
import Janela.JanelaMenu;
import ListaAquecimento.Canal;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.MetodosPersistencia;
import Utilidades.ValidarSistema;

public class OuvinteCanais implements ActionListener{
	private JanelaCadastroCanal janela;
	private String nomeCanal;
	private String numeroDoCanal;
	private String link;
	private String tipoCanal;
	
	public OuvinteCanais(JanelaCadastroCanal janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
		String botao = e.getActionCommand();
		
		CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
		// Retorna o indice da linha que est� sendo selecionada
		int linhaSelecionada = janela.getTabela().getSelectedRow();
		
		nomeCanal = janela.getTxtNome().getText();
		numeroDoCanal = janela.getTxtNumeroCanal().getText();
		link = janela.getTxtLink().getText();
		tipoCanal = (String) janela.getBoxFormaAssistir().getSelectedItem();
		
		//L�gica para botao
		
		//-------------------------------------------------------------------------------~
		// Bot�o Alterar e Excluir
			
	      if (botao.equalsIgnoreCase("Excluir")) {
	    
			if(linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(janela, "Selecionei uma Linha");
			
			}else {
				int resposta = JOptionPane.showConfirmDialog(janela, "T�m certeza que quer excluir");
			
				if(resposta == JOptionPane.YES_OPTION) {
					
					janela.manipularInterface(botao);
					central.getCanais().remove(linhaSelecionada);
					DefaultTableModel modelo = (DefaultTableModel) janela.getModelo();
					modelo.removeRow(linhaSelecionada);
					ValidarSistema.adicionarMesagem("Canal Excluido", janela);
					janela.getTabela().repaint();
					
					janela.manipularInterface("NOVO");
				}
				ValidarSistema.apagarCampoDeTexto(janela);
			}		
			
	//-------------------------------------------------------------------------------~
	// Bot�o Salvar
			
		}else if (botao.equalsIgnoreCase("Novo") || botao.equalsIgnoreCase("Alterar")) {
			
			janela.manipularInterface(botao);
		
	        if(botao.equalsIgnoreCase("Novo")) {
	        	 
		    	try {	
		    		Canal canal = new Canal(nomeCanal, numeroDoCanal, link, tipoCanal);
					central.adicionarCanal(canal);
					JOptionPane.showMessageDialog(janela, "Canal cadastrado");
					MetodosPersistencia.salvarCentral(central, "arquivo.xml");
					
					janela.dispose();
					new JanelaCadastroCanal();
					
				} catch (Exception e1) {
					e1.getMessage();
				}
		    	
			}
	       
	//-----------------------------------------------------------------------------------
	// Bot�o alterar		
		 
			if(botao.equalsIgnoreCase("Alterar")) {
			
				if(linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(janela, "Selecionei uma Linha");
					
				}else {
					if(nomeCanal.isBlank()) {
						ValidarSistema.adicionarMesagem("Campo vazio!!", janela);
					}else {	
						
						Canal canalRepetido = central.recuperarCanal(nomeCanal);
						
						if(canalRepetido != null){
							ValidarSistema.adicionarMesagem("N�o foi possivel, canal j� cadastrado!!", janela);
						}else {
							Canal c = central.getCanais().get(linhaSelecionada);
							c.setNome(nomeCanal);
							c.setLink(link);
							c.setNumero(numeroDoCanal);
							c.setTipoCanal(tipoCanal);
							MetodosPersistencia.salvarCentral(central, "arquivo.xml");
							
							janela.dispose();
							new JanelaCadastroCanal();
						}
						
					}
					
					ValidarSistema.apagarCampoDeTexto(janela);
				}
				janela.manipularInterface("NOVO");
			}
			
		//-------------------------------------------------------------------------------
		// Bot�o Voltar Ao Menu	
		
		}else if (botao.equalsIgnoreCase("Voltar ao menu")) {
			janela.dispose();
			new JanelaMenu();
		}
	   
		//Salvando as atualiza��es no arquivo.xml
		MetodosPersistencia.salvarCentral(central, "arquivo.xml");		
	}
		
}
