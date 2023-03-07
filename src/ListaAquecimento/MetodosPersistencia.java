package ListaAquecimento;

import ListaAquecimento.CentralDeInformacoes;

import ListaAquecimento.Persistencia;

public class MetodosPersistencia {
	
	public static CentralDeInformacoes obterCentral(String nomeArquivo) {
		Persistencia persistencia = new Persistencia();
		CentralDeInformacoes central = persistencia.recuperarCentral(nomeArquivo);
		
		return central;
	}
	
	public static void salvarCentral(CentralDeInformacoes central, String nomeArquivo) {
		Persistencia persistencia = new Persistencia();
		persistencia.salvarCentral(central, nomeArquivo);
	}

}
