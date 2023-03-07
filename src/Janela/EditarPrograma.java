package Janela;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ListaAquecimento.Canal;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.MetodosPersistencia;
import ListaAquecimento.ProgramaDeTv;
import ListaAquecimento.TipoPrograma;

public class EditarPrograma extends JanelaCadastrarProgramas {
	
	private ProgramaDeTv programa;
	private JanelaCadastrarProgramas janela;

	public EditarPrograma(ProgramaDeTv programa) {
		
		//programa criando outro arquivo
		
		this.programa = programa;
		
		getNome().setText(programa.getNome());
		
		getCbCanal().getModel().setSelectedItem(programa.getCanal());
		
		//Recebendo em enum concertar isso
		getCbTipos().getModel().setSelectedItem(programa.getTipo());
		
//		não consegui fazer para horário
		
		getBoxStatus().getModel().setSelectedItem(programa.getStatus());
		
		getGenero().getModel().setSelectedItem(programa.getGenero());
		
		
		getEstilo().getModel().setSelectedItem(programa.getEstilo());
		
//		getNumeroTemporada().getModel().setValue(programa.getTemporada());
//		
//		getApresentadores().getModel().setValue(programa.getApresentadores());
		
		getBotaoSalvar().addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				String botao = e.getActionCommand();
				
				if(botao.equalsIgnoreCase("salvar")) {
					
					String nome = getNome().getText();
					
					Canal canal = (Canal) getCbCanal().getSelectedItem();
					
					String horario = (String)getHora().getText();
					
					DayOfWeek[] dias = getDias();
					
					String dataRetorno = (String) getTfRetorno().getText();
					
					String status = (String) getBoxStatus().getSelectedItem();
					
					String genero = (String) getGenero().getSelectedItem();
					
					String estilo = (String) getEstilo().getSelectedItem();
					
					int temporada = (int) getNumeroTemporada().getValue();
					
					int apresentadores = (int) getApresentadores().getValue();
					
					TipoPrograma tipo = null;
					
					if(getCbTipos().getSelectedItem().equals("Programas Continuos")) {
						tipo = TipoPrograma.PROGRAMA_CONTINUO;
	
						programa.setNome(nome);
						programa.setTipo(tipo);
						programa.setHorario(horario);
						programa.setCanal(canal);
						programa.setApresentadores(apresentadores);
						programa.setTemporada(temporada);
						programa.setStatus(status);
						programa.setDataDeRetorno(dataRetorno);
						programa.setDias(dias);
						
					}else if(getCbTipos().getSelectedItem().equals("Reality shows")) {
						tipo = TipoPrograma.REALITY_SHOW;
					
						programa.setNome(nome);
						programa.setTipo(tipo);
						programa.setHorario(horario);
						programa.setCanal(canal);
						programa.setApresentadores(apresentadores);
						programa.setStatus(status);
						programa.setDataDeRetorno(dataRetorno);
						programa.setDias(dias);
						

					}else {
						tipo = TipoPrograma.SERIE_REGULAR;
				
						programa.setNome(nome);
						programa.setTipo(tipo);
						programa.setHorario(horario);
						programa.setCanal(canal);
						programa.setGenero(genero);
						programa.setEstilo(estilo);
						programa.setTemporada(temporada);
						programa.setStatus(status);
						programa.setDataDeRetorno(dataRetorno);
						programa.setDias(dias);
					}
					
					
					CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
					
					boolean adicionar = central.adicionar(programa);
					
					if(adicionar) {
						JOptionPane.showMessageDialog(janela, "Programa Cadastrado");
						dispose();
						MetodosPersistencia.salvarCentral(central, "arquivo.xml");
						new JanelaListarAlterarProgramas();
					}else {
						JOptionPane.showMessageDialog(janela, "Erro!!!");
					}
				}
				
			}
		});
	
	}
	

	
//	ArrayList<DayOfWeek> dia
	
//	Canal canal 
//	
//	String horario 
//	
//	String dataRetorno
//	
//	String status 
//	
//	String genero 
//	
//	String estilo 
//	
//	int temporada 
//	
//	int apresentadores 
}
