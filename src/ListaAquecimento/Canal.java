package ListaAquecimento;

import java.util.ArrayList;

public class Canal {
    private String nome;
    private String tipoCanal;
    private String numero;
    private String link;
    private ArrayList<ProgramaDeTv> programasCadastrados = new ArrayList<ProgramaDeTv>();
    private ArrayList<ProgramaDeTv> programasFavoritos = new ArrayList<ProgramaDeTv>();
    
    public Canal(String n, String numero, String l, String tipoCanal){
        nome = n;
        this.numero = numero;
        link = l;
        this.tipoCanal = tipoCanal;
    }
    public Canal(){}
    
    
    public String toString(){
        return nome;
    }
    public boolean equals(Canal canal){
        return nome.equals(canal.nome);
    }

    //gets and sets
    public String getNome() {
        return nome;
    }
	public String getTipoCanal() {
		return tipoCanal;
	}
	public void setTipoCanal(String tipoCanal) {
		this.tipoCanal = tipoCanal;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void adicionarPrograma(ProgramaDeTv programa) {
		this.programasCadastrados.add(programa);
	}
	public ArrayList<ProgramaDeTv> getProgramasCadastrados() {
		return programasCadastrados;
	}
	public void setProgramasCadastrados(ArrayList<ProgramaDeTv> programasCadastrados) {
		this.programasCadastrados = programasCadastrados;
	}
	public void adicionarProgramaFavorito(ProgramaDeTv programa) {
		this.programasFavoritos.add(programa);
	}
	
	public ArrayList<ProgramaDeTv> getProgramasFavoritos() {
		return programasFavoritos;
	}
	
	public void setProgramasFavoritos(ArrayList<ProgramaDeTv> programasFavoritos) {
		this.programasFavoritos = programasFavoritos;
	}
	public String getLink() {
		return link;
	}
	public String getNumero() {
		return numero;
	}
	
    
    
}
