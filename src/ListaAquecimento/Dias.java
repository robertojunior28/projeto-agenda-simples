package ListaAquecimento;

import java.util.ArrayList;

public class Dias {
	
	private ArrayList<String> Dias = new ArrayList<String>();
	
	//métodos da classe dias
	public void adicionarDias(String dias) {
		this.Dias.add(dias);
	}
	
	public String listaDias() {
		String retorno = " ";
		for(int i = 0; i < Dias.size(); i++) {
			retorno += Dias.get(i) + "-";
		}
		
		return retorno;
	}
	
	//gets e sets
	public ArrayList<String> getDias() {
		return Dias;
	}

	public void setDias(ArrayList<String> dias) {
		Dias = dias;
	}
}
