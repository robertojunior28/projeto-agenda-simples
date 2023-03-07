package ListaAquecimento;


public class Usuario {
	private String nome;
	private String senha;

	public boolean equals(Usuario usuario) {
		if(nome.equals(usuario.nome) || senha.equals(usuario.senha)) {
			return true;
		}
		return false;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
