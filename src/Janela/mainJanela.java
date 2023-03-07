package Janela;

import ListaAquecimento.CentralDeInformacoes;
import ListaAquecimento.MetodosPersistencia;

public class mainJanela {
	
    public static void main(String[] args) {	
	
    	CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
		
		if(central.getUsuarioLista().isEmpty()) {
			new JanelaNovoUsuario();
		}else {
			new JanelaLogin();
		}
	}
}
