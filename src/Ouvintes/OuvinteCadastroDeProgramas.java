package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Janela.JanelaCadastrarProgramas;
import Janela.JanelaListarAlterarProgramas;
import Janela.JanelaMenu;
import ListaAquecimento.Canal;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.Dias;
import ListaAquecimento.MetodosPersistencia;
import ListaAquecimento.ProgramaDeTv;
import ListaAquecimento.TipoPrograma;
import ListaAquecimento.Usuario;

public class OuvinteCadastroDeProgramas implements ActionListener{
	private JanelaCadastrarProgramas janelaCP;
	private ProgramaDeTv programa;
	private boolean existe = false;
	
	public OuvinteCadastroDeProgramas(JanelaCadastrarProgramas janela) {
		janelaCP = janela;
	}
	
	CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
	
	public void linkProgramaCanais(Canal canal, ProgramaDeTv programa) {
		
		for(Canal c: central.getCanais()){
			if(c.equals(canal)) {
				c.adicionarPrograma(programa);
			}
		}
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String botao = e.getActionCommand();
		if(botao.equalsIgnoreCase("Cancelar")) {
			new JanelaListarAlterarProgramas();
			janelaCP.dispose();
		}
		if(botao.equalsIgnoreCase("salvar") && existe == false) {
			String nome = janelaCP.getNome().getText();
			
			Canal canal = (Canal) janelaCP.getCbCanal().getSelectedItem();
			
			String horario = (String)janelaCP.getHora().getText();
			
			DayOfWeek[] dias = janelaCP.getDias();
			
			String dataRetorno = (String)janelaCP.getTfRetorno().getText();
			
			String status = (String)janelaCP.getBoxStatus().getSelectedItem();
			
			String genero = (String)janelaCP.getGenero().getSelectedItem();
			
			String estilo = (String)janelaCP.getEstilo().getSelectedItem();
			
			int temporada = (int)janelaCP.getNumeroTemporada().getValue();
			
			int apresentadores = (int)janelaCP.getApresentadores().getValue();
			
			TipoPrograma tipo = null;
			
			if(janelaCP.getCbTipos().getSelectedItem().equals("Programas Continuos")) {
				tipo = TipoPrograma.PROGRAMA_CONTINUO;
				programa = new ProgramaDeTv( nome, tipo,  horario,  canal,   apresentadores,  temporada,  status, dataRetorno,dias);
				
			}else if(janelaCP.getCbTipos().getSelectedItem().equals("Reality shows")) {
				tipo = TipoPrograma.REALITY_SHOW;
				programa = new ProgramaDeTv( nome, tipo, horario, canal, apresentadores, status, dataRetorno,dias);

			}else {
				tipo = TipoPrograma.SERIE_REGULAR;
				programa = new ProgramaDeTv(nome, tipo, horario, canal, genero, estilo, temporada, status, dataRetorno,dias);
			}
			
			boolean adicionar = central.adicionar(programa);
			
			if(adicionar) {
				JOptionPane.showMessageDialog(janelaCP, "Programa Cadastrado");
				linkProgramaCanais(canal, programa);
				janelaCP.dispose();
				MetodosPersistencia.salvarCentral(central, "arquivo.xml");
				new JanelaListarAlterarProgramas();
			}else {
				JOptionPane.showMessageDialog(janelaCP, "Erro!!!");
			}
		}
	}

}
