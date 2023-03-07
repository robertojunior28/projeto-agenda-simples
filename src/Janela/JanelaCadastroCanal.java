package Janela;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ListaAquecimento.Canal;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.MetodosPersistencia;
import ListaAquecimento.Usuario;
import Ouvintes.OuvinteCanais;
import Ouvintes.OuvinteContatos;
import Utilidades.ValidarSistema;

public class JanelaCadastroCanal extends JanelaPadrao{
	   // Ellen
	    private JTextField txtNome;
	    private JTextField txtNumeroCanal;
	    private JTextField txtLink;
	    private JButton botaoExcluir;
	    private JButton botaoAlterar;
	    private JButton botaoSalvar;
	    private JButton botaoVoltarAoMenu;
	    private JTable tabela;
	    private JComboBox<String> boxFormaAssistir;
	    private DefaultTableModel modelo;
	   
	    public JanelaCadastroCanal() {
	        super("Canais", "Cadastro de Canal");
	        campusAdicionarCanal();
	        campusAcoes();
	        campusListaCanais();
	        botaoVoltarAoMenu();
	        manipularInterface("NOVO");
	       
	        setVisible(true);
	    }
	    public void manipularInterface(String modo) {
	    	
	    	switch (modo) {
	    	
			case "NOVO":
				botaoAlterar.setEnabled(false);
	    		botaoExcluir.setEnabled(false);
	    		txtLink.setEnabled(true);
	    		txtNome.setEnabled(true);
	    		txtNumeroCanal.setEnabled(true);
	    		boxFormaAssistir.setEnabled(true);
	    		botaoSalvar.setEnabled(true);
	    		break;
			
			case "SELECAO":
				botaoAlterar.setEnabled(true);
	    		botaoExcluir.setEnabled(true);
	    		txtLink.setEnabled(true);
	    		txtNome.setEnabled(true);
	    		txtNumeroCanal.setEnabled(true);
	    		boxFormaAssistir.setEnabled(true);
	    		botaoSalvar.setEnabled(true);
	    	}
	    }
		public JButton adicionarBotao(String nomeDoBotao,String imagem, int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical) {
			JButton botao = ValidarSistema.adicionarBotaoComImagem(nomeDoBotao,imagem, distaciamentoHorizontal,  distanciamentoVertical, tamanhoHorizontal, tamanhoVertical);
			add(botao);
			botao.addActionListener(new OuvinteCanais(this));
			return botao;
		}
			    
		private class ouvinteInterno implements ActionListener{

	 			public void actionPerformed(ActionEvent e) {
	 				
	 				if(boxFormaAssistir.getSelectedItem().equals("Canal Aberto")) {
	 					txtLink.setEnabled(false);
	 					txtNumeroCanal.setEnabled(true);
	 				}else if(boxFormaAssistir.getSelectedItem().equals("Broadcasting Aberta Na Internet")) {
	 					 txtNumeroCanal.setEnabled(false);
	 					 txtLink.setEnabled(true);
	 					
	 				}else {
	 					 txtNumeroCanal.setEnabled(false);
	 					 txtLink.setEnabled(false);
	 				}
	 				
	 			}
	 	    	
	 	    }
		
	    public void campusAdicionarCanal(){
	       // painel de adicionar canal
	        JPanel panelAdicionarCanal = ValidarSistema.adicionarPainel("Adicionar Canal", 0, 70, 384, 100);
	        super.getContentPane().add(panelAdicionarCanal);
	    
	        JLabel lblNome = ValidarSistema.adicionarTexto("Nome do canal",10, 22, 95, 15);
	        panelAdicionarCanal.add(lblNome);

	        JLabel lblFormaAssistir = ValidarSistema.adicionarTexto("Forma de assistir", 10, 43, 120, 23);
	        panelAdicionarCanal.add(lblFormaAssistir);

	        JLabel lblNumeroCanal = ValidarSistema.adicionarTexto("Numero do canal", 10, 73, 120, 15);
	        panelAdicionarCanal.add(lblNumeroCanal);

	        JLabel lblLink = ValidarSistema.adicionarTexto("Link", 160, 74, 120, 15);
	        panelAdicionarCanal.add(lblLink);

	        txtNome = ValidarSistema.adicionarCaixaDeTexto(100, 17, 273, 23);
	        panelAdicionarCanal.add(txtNome);

	        txtNumeroCanal = ValidarSistema.adicionarCaixaDeTexto(117, 73, 40, 20);
	        panelAdicionarCanal.add(txtNumeroCanal);
	       
	        txtNumeroCanal.addKeyListener(new KeyListener() {
				
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					
					if(Character.isLetter(c))
						e.consume();
				}
				public void keyReleased(KeyEvent e) {}
				
				public void keyPressed(KeyEvent e) {}
			});

	        txtLink = ValidarSistema.adicionarCaixaDeTexto(187, 73, 187, 20);
	        panelAdicionarCanal.add(txtLink);
	        
	        boxFormaAssistir = ValidarSistema.adicionarComboBox(new String[] {"Canal Aberto","Broadcasting Aberta Na Internet","Pacote Assinatura","Assinatura Individual Televisao","Assinatura Individual Broadcasting"}, 116, 46, 200, 22);
			boxFormaAssistir.addActionListener(new ouvinteInterno());
			panelAdicionarCanal.add(boxFormaAssistir);
			
	    }
	    public void campusAcoes(){
	    	
	        botaoAlterar = adicionarBotao("ALTERAR","fotos/caixa-de-retorno.png", 137, 182, 110, 30);
	        botaoSalvar = adicionarBotao("NOVO","fotos/registro.png", 20, 182, 110, 30);
	        botaoExcluir = adicionarBotao("EXCLUIR","fotos/lixo.png", 254, 182, 110, 30);
	    }
	    public void campusListaCanais(){
	     
	    	JPanel panelListaCanais = ValidarSistema.adicionarPainel("Lista de Canais", 0, 220, 384, 195);
	        super.getContentPane().add(panelListaCanais);
	        //colunas da lista 
	        modelo  = new DefaultTableModel();
	        modelo.addColumn("Nome");
            modelo.addColumn("Qtd de Programas");
            modelo.addColumn("Favoritos");
       
            CentralDeInformacoes listaCanal = MetodosPersistencia.obterCentral("arquivo.xml");
            
            ArrayList<Canal> canais = listaCanal.getCanais();
            
            if(canais.size() > 0){
                
            	for(Canal p : canais){
                    Object[] linha = new Object[3];
                    
                    linha[0] = p.getNome();
                    linha[1] = p.getProgramasCadastrados().size();
                    linha[2] = p.getProgramasFavoritos().size();
                    modelo.addRow(linha);
                }
            }    
	        
            tabela = new JTable(modelo);
            tabela.addMouseListener(new MouseListener() {
			
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				
				public void mouseClicked(MouseEvent e) {
					
					manipularInterface("SELECAO");
					
					int linhaSelecionada = tabela.getSelectedRow();
					
					CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
				
					if(linhaSelecionada != -1) {
						
						Canal c = central.getCanais().get(linhaSelecionada);
						
						JTextField nome = txtNome;
					    nome.setText(c.getNome());
					    JTextField link = txtLink;
					    link.setText(c.getLink());
					    JTextField numero = txtNumeroCanal;
					    numero.setText(c.getNumero());
					    JComboBox<String> formaAssistir = boxFormaAssistir;
					    formaAssistir.setSelectedItem(c.getTipoCanal());
					
					}
		            tabela.repaint();
				}
			});
	        JScrollPane painelTabela = new JScrollPane(tabela);
	        painelTabela.setBounds(15, 25, 357, 153);
	        panelListaCanais.add(painelTabela);  
	    }
	    public void botaoVoltarAoMenu() {
	    	botaoVoltarAoMenu = ValidarSistema.adicionarBotao("Voltar ao menu", 253, 422,120, 30);
	    	add(botaoVoltarAoMenu);
	    	
	    	botaoVoltarAoMenu.addActionListener(new OuvinteCanais(this));
	    }
	    public JTextField getTxtNome() {
			return txtNome;
		}
	    public void setTxtLink(JTextField txtLink) {
			this.txtLink = txtLink;
		}
	    public void setTxtNome(JTextField txtNome) {
			this.txtNome = txtNome;
		}
	    public void setTxtNumeroCanal(JTextField txtNumeroCanal) {
			this.txtNumeroCanal = txtNumeroCanal;
		}
	    public void setBoxFormaAssistir(JComboBox<String> boxFormaAssistir) {
			this.boxFormaAssistir = boxFormaAssistir;
		}
	    public JTextField getTxtLink() {
			return txtLink;
		}
	    public JTextField getTxtNumeroCanal() {
			return txtNumeroCanal;
		}
	    public JTable getTabela() {
			return tabela;
		}
	    public JComboBox<String> getBoxFormaAssistir() {
			return boxFormaAssistir;
		}
	    public DefaultTableModel getModelo() {
			return modelo;
		}
	    
}
