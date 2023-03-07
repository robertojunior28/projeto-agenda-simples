package Janela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import ListaAquecimento.Canal;
import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.Dias;
import ListaAquecimento.MetodosPersistencia;
import ListaAquecimento.ProgramaDeTv;
import Ouvintes.OuvinteCadastroDeProgramas;
import Utilidades.ValidarSistema;

public class JanelaCadastrarProgramas extends JanelaPadrao{
	//Ellen
	private JComboBox<String> cbTipo;
	private JComboBox<Canal> cbCanal;
	private JTextField nome;
	private JFormattedTextField hora;
	private JSpinner apresentadores;
	private JSpinner numeroTemporada;
	private DayOfWeek[] dias;
	private int qtdDias;
	//private OuvinteCadastroDeProgramas dias;
	private String dataDeRetorno;
	private String status;
	private JComboBox<String> genero;
	private JComboBox<String> estilo;
	private JButton botaoCadastro;
	private JComboBox<String> boxStatus;
	private JFormattedTextField tfRetorno;
	private JButton botaoSalvar;
	
	private String[] opcoesStatus = {"Hiato","Em exibição", "Finalizado", "Cancelado"};
	
	
	CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
	
	OuvinteCadastroDeProgramas ouvinteExterno = new OuvinteCadastroDeProgramas(this);
	OuvinteMudancas ouvinteInterno = new OuvinteMudancas();
	

//	gets e set
	
	public JTextField getNome() {
		return nome;
	}
	public JSpinner getApresentadores() {
		return apresentadores;
	}
	public void setApresentadores(JSpinner apresentadores) {
		this.apresentadores = apresentadores;
	}
	public JFormattedTextField getTfRetorno() {
		return tfRetorno;
	}
	public void setTfRetorno(JFormattedTextField tfRetorno) {
		this.tfRetorno = tfRetorno;
	}
	public JComboBox<String> getCbTipos() {
		return cbTipo;
	}
	public JFormattedTextField getHora() {
		return hora;
	}
	public String getDataDeRetorno() {
		return dataDeRetorno;
	}
	public String getStatus() {
		return status;
	}
	public JComboBox<Canal> getCbCanal() {
		return cbCanal;
	}
	public JComboBox<String> getBoxStatus() {
		return boxStatus;
	}
	public JComboBox<String> getGenero() {
		return genero;
	}
	public JComboBox<String> getEstilo() {
		return estilo;
	}
	public JSpinner getNumeroTemporada() {
		return numeroTemporada;
	}
	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	
	
	public JanelaCadastrarProgramas() {
	
		super("Programas", "Cadastrar Programa");
		setSize(400,600);
		nomeDoPrograma();
		canalOndePassa();
		tipoDoPrograma();
		dias = new DayOfWeek[7];
	
//		interativos
		adicionarStatus();
		adicionarBotao();
		adicionarGenero();
		adicionarEstilo();
		dia();
		hora();
		temporadas();
		
//		labels
		jLabel("Dia(s) de exibição", 20,200, 120, 25);
		jLabel("Status do programa", 20, 260, 120, 25);
		jLabel("Horário", 190, 260, 100, 25);
		jLabel("Nome do programa", 20, 85, 120, 30);
		jLabel("Canal", 20, 140, 50, 30);
		jLabel("Tipo", 200, 140, 50, 30);
		jLabel("Número de apresentadores", 20, 430, 160, 30);
		jLabel("Data de Retorno", 265, 260, 150, 25);
		jLabel("Gênero", 20, 320, 100, 20);
		jLabel("Estilo", 200, 320, 100, 20);
		jLabel("Número de temporadas", 20, 390, 150, 20);
		
		
		qtdApresentadores();	
		dataDeRetorno();
		
		setVisible(true);
	}
	
	
//  ouvinte para mudanï¿½as internas
	
	private class OuvinteMudancas implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(boxStatus.getSelectedItem().equals("Hiato")) {
				tfRetorno.setEnabled(true);
			}else {
				tfRetorno.setEnabled(false);
			}
			if(cbTipo.getSelectedItem().equals("Série Regular") == false) {
				apresentadores.setEnabled(true);
				cbCanal.setEnabled(true);
				genero.setEnabled(false);
				estilo.setEnabled(false);
			}else {
				apresentadores.setEnabled(false);
				genero.setEnabled(true);
				estilo.setEnabled(true);
				cbCanal.setEnabled(false);
			}
		}
	}
	
	
	private void nomeDoPrograma() {
		nome = ValidarSistema.adicionarCaixaDeTexto(20, 110, 340, 25);
		add(nome);
	}
	private void canalOndePassa() {
		cbCanal = new JComboBox<Canal>();
		for(int i = 0; i < central.getCanais().size(); i++) {
			cbCanal.addItem(central.getCanais().get(i));
		}
		cbCanal.setBounds(20, 165, 140, 25);
		add(cbCanal);
	}
	
	private void tipoDoPrograma() {
		String[] opcoes = {"Programas Continuos", "Reality shows", "Série Regular"};
		cbTipo = new JComboBox<String>(opcoes);
		cbTipo.setBounds(200, 165, 160, 25);
		add(cbTipo);
		
		cbTipo.addActionListener(ouvinteInterno);
	}
	
	private void jLabel(String nome, int a, int b, int c, int d) {
		JLabel status = ValidarSistema.adicionarTexto(nome, a, b, c, d);
		add(status);
		
	}
	
	private void adicionarStatus() {
		boxStatus = ValidarSistema.adicionarComboBox(opcoesStatus, 20, 285, 150, 25);
		//this.status = (String) box.getSelectedItem();
		add(boxStatus);
		boxStatus.addActionListener(ouvinteInterno);
	}
	
	private void adicionarGenero() {
		String[] generos = {"comédia"," drama"," ficção", "romance","terror","ação e aventura"};
		
		genero = ValidarSistema.adicionarComboBox(generos, 20, 345, 150, 25);
		//this.status = (String) genero.getSelectedItem();
		add(genero);
		genero.addActionListener(ouvinteInterno);
	}
	
	private void adicionarEstilo() {
		String[] estilos = {"live action", "animada"};
		estilo = ValidarSistema.adicionarComboBox(estilos, 200, 345, 150, 25);
		//this.status = (String) estilo.getSelectedItem();
		add(estilo);
		estilo.addActionListener(ouvinteInterno);
	}
	
	
	private void qtdApresentadores() {
		apresentadores = new JSpinner();
		apresentadores.setBounds(200, 430, 40, 25);
		//apresentadores = apresentadores.getComponentCount();
		add(apresentadores);
	}
	
	public void temporadas() {
		numeroTemporada = new JSpinner();
		numeroTemporada.setBounds(200, 390, 40, 25);
		//this.quantidadeDeApresentadores = numeroTemporada.getComponentCount();
		add(numeroTemporada);
	}
	
	private void hora() {
		try {
			MaskFormatter mascaraDeHora = new MaskFormatter("##:##");
			JFormattedTextField tfHora = new JFormattedTextField(mascaraDeHora);
			tfHora.setBounds(190, 285, 40, 25);
			tfHora.setHorizontalAlignment(JLabel.CENTER);
			hora = tfHora;
			add(tfHora);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void dataDeRetorno() {
		try {
			MaskFormatter mascaraDeData = new MaskFormatter("##/##/####");
			tfRetorno = new JFormattedTextField(mascaraDeData);
			tfRetorno.setBounds(265, 285, 80, 25);
			tfRetorno.setHorizontalAlignment(JLabel.CENTER);
			dataDeRetorno = tfRetorno.getText();
			add(tfRetorno);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	private void adicionarBotao() {
		botaoSalvar = ValidarSistema.adicionarBotao("Salvar", 80, 500, 100, 30);
		add(botaoSalvar);
			
		OuvinteCadastroDeProgramas ouvinteCP = new OuvinteCadastroDeProgramas(this);
			
		botaoSalvar.addActionListener(ouvinteCP);
		
		botaoCadastro = ValidarSistema.adicionarBotao("Cancelar", 200, 500, 100, 30);
		add(botaoCadastro);
		botaoCadastro.addActionListener(ouvinteCP);
	}	
	
	private class OuvinteCheckBox implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			String clique = e.getActionCommand();
			
			if(clique.equalsIgnoreCase("dom")) {
				dias[qtdDias++] = DayOfWeek.SUNDAY;
			}
			if(clique.equalsIgnoreCase("seg")) {
				dias[qtdDias++] = DayOfWeek.MONDAY;
			}
			if(clique.equalsIgnoreCase("ter")) {
				dias[qtdDias++] = DayOfWeek.TUESDAY;
			}
			if(clique.equalsIgnoreCase("Qua")) {
				dias[qtdDias++] = DayOfWeek.WEDNESDAY;
			}
			if(clique.equalsIgnoreCase("Qui")) {
				dias[qtdDias++] = DayOfWeek.THURSDAY;
			}
			if(clique.equalsIgnoreCase("Sex")) {
				dias[qtdDias++] = DayOfWeek.FRIDAY;
			}
			if(clique.equalsIgnoreCase("Sab")) {
				dias[qtdDias++] = DayOfWeek.SATURDAY;
			}
			
		}
		
	}
	
	OuvinteCheckBox ouvinteCB = new OuvinteCheckBox();
			
	private void dia() {
		JCheckBox dom = new JCheckBox("Dom");
		dom.setBounds(17, 225, 51, 25);
		add(dom);
		dom.addActionListener(ouvinteCB);
		JCheckBox seg = new JCheckBox("Seg");
		seg.setBounds(67, 225, 51, 25);
		add(seg);
		seg.addActionListener(ouvinteCB);
		JCheckBox ter = new JCheckBox("Ter");
		ter.setBounds(117, 225, 51, 25);
		add(ter);
		ter.addActionListener(ouvinteCB);
		JCheckBox qua = new JCheckBox("Qua");
		qua.setBounds(167, 225, 51, 25);
		add(qua);
		qua.addActionListener(ouvinteCB);
		JCheckBox qui = new JCheckBox("Qui");
		qui.setBounds(217, 225, 51, 25);
		add(qui);
		qui.addActionListener(ouvinteCB);
		JCheckBox sex = new JCheckBox("Sex");
		sex.setBounds(267, 225, 51, 25);
		add(sex);
		sex.addActionListener(ouvinteCB);
		JCheckBox sab = new JCheckBox("Sab");
		sab.setBounds(317, 225, 51, 25);
		add(sab);
		sab.addActionListener(ouvinteCB);
		
	}
	public DayOfWeek[] getDias() {
		return dias;
	}

}