package Janela;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.Contato;
import ListaAquecimento.MetodosPersistencia;
import Ouvintes.OuvinteContatos;
import Ouvintes.OuvinteMenu;
import Utilidades.ValidarSistema;

public class JanelaListaDeContato extends JanelaPadrao{
	private JTextField txtNome; 
	private JTextField txtEmail;
	private DefaultTableModel modelo;
	private JTable tabela;
	private JButton botaoNovo;
	private JButton botaoExcluir;
	private JButton botaoEnviar;

	public JanelaListaDeContato() {
		super("Contatos", "Contatos");
		adicionarTexto("Nome", 10, 95, 40, 25);
		adicionarTexto("E-mail", 195, 95, 40, 25);
		
	    txtNome = ValidarSistema.adicionarCaixaDeTexto(50, 95, 135, 25);
        add(txtNome);
       
        txtEmail = ValidarSistema.adicionarCaixaDeTexto(240, 95, 137, 25);
        add(txtEmail);
        
		adicionarTabela();
		botaoNovo = adicionarBotao("NOVO","fotos/salve-.png",160,130,100,30);
	    botaoExcluir = adicionarBotao("EXCLUIR","fotos/lixo.png", 265,130,105,30);
		botaoEnviar = adicionarBotao("ENVIAR","fotos/enviei.png", 10, 130, 100, 30);
		adicionarBotao("Voltar ao menu","", 250, 424, 130, 30);
		
		botaoExcluir.setEnabled(false);
		botaoNovo.setEnabled(true);
		botaoEnviar.setEnabled(false);

		setVisible(true);
	}
	public JButton adicionarBotao(String nomeDoBotao,String imagem, int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical) {
		JButton botao = ValidarSistema.adicionarBotaoComImagem(nomeDoBotao,imagem, distaciamentoHorizontal,  distanciamentoVertical, tamanhoHorizontal, tamanhoVertical);
		add(botao);
		botao.addActionListener(new OuvinteContatos(this));
		return botao;
	}
	
	private void adicionarTexto(String nomeDoTexto, int distaciamentoHorizontal, int distanciamentoVertical, int tamanhoHorizontal, int tamanhoVertical) {
		JLabel texto = ValidarSistema.adicionarTexto(nomeDoTexto, distaciamentoHorizontal, distanciamentoVertical, tamanhoHorizontal, tamanhoVertical);
		add(texto);
	}
	
	private void adicionarTabela() {
		JPanel painelListaContato = ValidarSistema.adicionarPainel("Lista de Contatos", 0, 170, 384, 250);
        add(painelListaContato);
        
        modelo  = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("E-mail");
   
        CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
        
        ArrayList<Contato> contatos = central.getContatos();
        
        if(contatos.size() > 0){
            
        	for(Contato p: contatos){
                Object[] linha = new Object[2];
                
                linha[0] = p.getNome();
                linha[1] = p.getEmail();
              
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
				
				int linhaSelecionada = tabela.getSelectedRow();
			
				if(linhaSelecionada != -1) {
					
					botaoExcluir.setEnabled(true);
					botaoNovo.setEnabled(false);
					botaoEnviar.setEnabled(true);
					
					txtNome.setEnabled(false);
					txtEmail.setEnabled(false);
					
					Contato c = central.getContatos().get(linhaSelecionada);
					
					JTextField nome = txtNome;
				    nome.setText(c.getNome());
				    JTextField email = txtEmail;
				    email.setText(c.getEmail());
				
				}
	            tabela.repaint();
			}
		});
        JScrollPane painelTabela = new JScrollPane(tabela);
        painelTabela.setBounds(15, 25, 357, 200);
        painelListaContato.add(painelTabela);  
    }
	public JTable getTabela() {
		return tabela;
	}
	public JTextField getTxtEmail() {
		return txtEmail;
	}
	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}
	public JTextField getTxtNome() {
		return txtNome;
	}
	public DefaultTableModel getModelo() {
		return modelo;
	}
	public JButton getBotaoExcluir() {
		return botaoExcluir;
	}

}
