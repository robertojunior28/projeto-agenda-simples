package Janela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ListaAquecimento.Canal;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.MetodosPersistencia;

import ListaAquecimento.ProgramaDeTv;
import Utilidades.ValidarSistema;
public class JanelaListarAlterarProgramas extends JanelaPadrao{
	
	private JButton btNovo;
	private JButton btFavorito;
	private JButton btAlterar;
	private JButton btExcluir;
	private JButton btMenu;
	private JTextField pesquisa;
    private JTable tabela;
    private JButton botaoPesquisa;
    private JRadioButton favorito;
    private JRadioButton canal;
    private JRadioButton nome;
    private JRadioButton genero;
    private ArrayList<ProgramaDeTv> listaFavoritos;
    private DefaultTableModel modelo = new DefaultTableModel();
    
    public void tirarFavorito() {
    	btFavorito.setEnabled(false);
    }
    public void tirarAlterar() {
    	btAlterar.setEnabled(false);
    }
    public void tirarExcluir() {
    	btExcluir.setEnabled(false);
    }
    public boolean getFavorito() {
    	return favorito.isSelected();
    }
    public boolean getCanal() {
    	return canal.isSelected();
    }
    public boolean getNome() {
    	return nome.isSelected();
    }
    public boolean getGenero() {
    	return genero.isSelected();
    }
    public String getPesquisa() {
    	if(pesquisa != null)
    		return pesquisa.getText();
    	return null;
    }
    public void desabilitarPesquisa() {
    	botaoPesquisa.setEnabled(false);
    }
    public void abilitarPesquisa() {
    	botaoPesquisa.setEnabled(true);
    }
    public JButton getBtNovo() {
		return btNovo;
	}
 

    
    
	public JanelaListarAlterarProgramas() {
		super("Lista de Programa", "Programas");
		setSize(500,600);
		setLocationRelativeTo(null);
		configurarBotoes();
		adicionarTabela();
		campusPesquisa();
		desabilitarPesquisa();
		setVisible(true);
		repaint();
	}
	
	public void apagarLinhasDaTabela() {
		int qtdLinhas = modelo.getRowCount();
		//apaga todas as linhas
		for(int i = 0; i < qtdLinhas; i++)
			modelo.removeRow(0);
	}
	
	public void adicionarLinha(ProgramaDeTv p) {
		Object[] linha = new Object[11];
		linha[0] = p.getNome();
		linha[1] = p.getCanal();
		linha[2] = p.getTipo();
		linha[3] = p.retornarDias();
		linha[4] = p.getStatus();
		linha[5] = p.getHorario();
		linha[6] = p.getDataDeRetorno();
		linha[7] = p.getGenero();
		linha[8] = p.getEstilo();
		linha[9] = p.getTemporada();
		linha[10] = p.getApresentadores();
		modelo.addRow(linha);	
	}

	public void adicionarTabela() {
		
		JPanel panelListaPrograma = ValidarSistema.adicionarPainel("Lista de Programa", 30,190, 434, 250);
        add(panelListaPrograma);
        
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Canal");
		modelo.addColumn("Tipo");
		modelo.addColumn("Dias");
		modelo.addColumn("Status");
		modelo.addColumn("Horario");
		modelo.addColumn("Retorno");
		modelo.addColumn("Genero");
		modelo.addColumn("Estilo");
		modelo.addColumn("Temporadas");
		modelo.addColumn("Apresentadores");
		CentralDeInformacoes informacao = MetodosPersistencia.obterCentral("arquivo.xml");
		ArrayList<ProgramaDeTv> todosOsProgramas = informacao.getTodosProgramas();
		
		for(ProgramaDeTv p: todosOsProgramas) {
			adicionarLinha(p);
		}
		
		tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(145);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(185);
		painelTabela.setBounds(15, 20, 406, 220);
		panelListaPrograma.add(painelTabela);
		tabela.repaint();
	}
	
	public void campusPesquisa() {
		
        JLabel titulo = new JLabel("Pesquisa");
        titulo.setBounds(35, 67, 100, 15);
        add(titulo);
        
        pesquisa = ValidarSistema.adicionarCaixaDeTexto(40, 85, 175, 25);
        add(pesquisa);
     
        genero =  new JRadioButton("Genero");
        genero.setBounds(330, 75, 80, 20);
        add(genero);
        genero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(genero.isSelected()) 
					abilitarPesquisa();
			}
		});
        
        nome =  new JRadioButton("titulo");
        nome.setBounds(270, 75, 60, 20);
        add(nome);
        nome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nome.isSelected())
					abilitarPesquisa();
				else
					desabilitarPesquisa();
			}
		});
        
        canal =  new JRadioButton("Canal");
        canal.setBounds(270, 95, 60, 20);
        add(canal);
        canal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canal.isSelected())
					abilitarPesquisa();
				else
					desabilitarPesquisa();
			}
		});
        
        favorito =  new JRadioButton("Favorito");
        favorito.setBounds(330, 95, 80, 20);
        add(favorito); 
        favorito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(favorito.isSelected())
					abilitarPesquisa();
				else
					desabilitarPesquisa();
			}
		});
        
        botaoPesquisa = new JButton(new ImageIcon("fotos/lupa (1).png"));
        botaoPesquisa.setBounds(230, 84, 35, 25);
        add(botaoPesquisa);
        botaoPesquisa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String pesquisa1 = pesquisa.getText();
        		apagarLinhasDaTabela();
        		tirarAlterar();
        		tirarExcluir();
        		tirarFavorito();
        		boolean n = getNome();
        		boolean c = getCanal();
        		boolean g = getGenero();
        		boolean f = getFavorito();
        		
        		CentralDeInformacoes informacao = MetodosPersistencia.obterCentral("arquivo.xml");
        		boolean voltar = pesquisa1.length() == 0 || pesquisa1.isBlank();
        		for(ProgramaDeTv p: informacao.getTodosProgramas()) {
        			boolean nomet = p.getNome().contains(pesquisa1);
        			boolean canalt = p.getCanal().getNome().contains(pesquisa1);
        			boolean statust = p.getStatus().contains(pesquisa1);
        			
        			
        			
        			if (p == null)
        				continue;
        			if(n && nomet) {
        				
        				adicionarLinha(p);
        			}
        			if(c && canalt) {
        				adicionarLinha(p);	
        			}
        			if(g) {
        				adicionarLinha(p);
        			}
        			if(statust) {
        				adicionarLinha(p);
        			}
        		}
        		repaint();
        		
        		if(voltar) {
        			new JanelaListarAlterarProgramas();
        			dispose();
        		}
        	}
		});
	}
	
	private void configurarBotoes() {
	
		btNovo = new JButton("NOVO");
		btNovo.setBounds(30, 147, 90, 30);
		add(btNovo);
		
		btAlterar = new JButton("ALTERAR");
		btAlterar.setBounds(135, 147, 90, 30);
		add(btAlterar);
		
		btExcluir = new JButton("EXCLUIR");
		btExcluir.setBounds(370, 147, 90, 30);
		add(btExcluir);
		
		btMenu = new JButton("Voltar ao menu");
		btMenu.setBounds(350, 510, 120, 30);
		add(btMenu);
		
		btFavorito = new JButton("FAVORITAR");
		btFavorito.setBounds(240, 147, 110, 30);
		add(btFavorito);
		
		CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
		
		btFavorito.addActionListener(new ActionListener() {
		
			private void listaFavoritosAdicionar(ProgramaDeTv programa) {
				ArrayList<Canal> listaCanais = central.getCanais();
				for(Canal c: listaCanais) {
					if(c.getNome().equals(programa.getCanal().getNome())) {
						c.adicionarProgramaFavorito(programa);
					}
				}
				
			}
			public void actionPerformed(ActionEvent e) {
				listaFavoritos = central.getProgramasFavoritos();
				boolean existe = false;
				
				ProgramaDeTv programa = central.getTodosProgramas().get(tabela.getSelectedRow());
				
				if(listaFavoritos.size() == 0) {
					int opcao = JOptionPane.showConfirmDialog(null, "Marcar Programa Como Favorito", " ", JOptionPane.YES_NO_OPTION);
					if(opcao == 0) {
						JOptionPane.showMessageDialog(null, "Programa adicionado a lista de favoritos");
						listaFavoritos.add(programa);
						listaFavoritosAdicionar(programa);
					}
				}else {
					int posicao = 0;
					//verifica se programa ja existe na lista.
					for(int i = 0; i < listaFavoritos.size(); i++) {
						if(listaFavoritos.get(i).equals(programa)) {
							existe = true;
							posicao = i;
							break;
						}
					}
					if(existe) {
						int opcao = JOptionPane.showConfirmDialog(null, "Programa marcado como favorito, deseja remove-lo da lista de favoritos?", " ", JOptionPane.YES_NO_OPTION);
						if(opcao == 0) {
							JOptionPane.showMessageDialog(null, "Programa removido da lista.");

							ArrayList<ProgramaDeTv> Excluir = listaFavoritos.get(posicao).getCanal().getProgramasFavoritos();
							
							//Excluindo via Programa
							for(int i = 0; i < Excluir.size(); i++) {
								if(Excluir.get(i).equals(listaFavoritos.get(posicao))) {
									Excluir.remove(listaFavoritos.get(posicao));
								}
							}
							//Excluindo dentro da classe canal
							listaFavoritos.remove(posicao);
						}
					}else {
						int opcao = JOptionPane.showConfirmDialog(null, "Marcar Programa Como Favorito", " ", JOptionPane.YES_NO_OPTION);
						if(opcao == 0) {
							JOptionPane.showMessageDialog(null, "Programa adicionado a lista de favoritos");
							listaFavoritos.add(programa);
							listaFavoritosAdicionar(programa);
						}
					}
				}
				//else
				MetodosPersistencia.salvarCentral(central, "arquivo.xml");
			}
			//action
		});
	
		btNovo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new JanelaCadastrarProgramas();
				dispose();
			}
		});
		btAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tabela.getSelectedRow();
				
				ProgramaDeTv programa = central.getTodosProgramas().get(linhaSelecionada);
				
				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha");
				}else {
//					JOptionPane.showMessageDialog(null, "Editar tarefa");
					dispose();
					CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
					
					new EditarPrograma(programa);
				}
			}
		});
		
		btExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tabela.getSelectedRow();
				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha");
				}else {
					int opcao = JOptionPane.showConfirmDialog(null, "Excluir Programa?","Excluir", JOptionPane.YES_NO_OPTION);
					if(opcao == 0) {
						//remove da tabela
						DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
						modelo.removeRow(linhaSelecionada);
						tabela.repaint();
						
						//remove programa do  arquivo
						CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
						central.excluirByIndex(linhaSelecionada);
						MetodosPersistencia.salvarCentral(central, "arquivo.xml");
					}

				}
				
			}
		});
		
		btMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new JanelaMenu();
				dispose();
			}
		});
	}

}
