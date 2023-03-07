package ListaAquecimento;

public class Contato {
	
	private String nome;
	private String email;
	
	public Contato(String n, String e) {
		nome = n;
		email = e;
	}
	
	public String getEmail() {
		return email;
	}
	public String getNome() {
		return nome;
	}
	
	public boolean equals(Contato c) {
		return nome.equals(c.getNome()) || email.equals(c.getEmail());
	}

}
