package ListaAquecimento;
import java.util.ArrayList;

// Ellen
import Exception.CaixaDeTextoVaziaExpection;
import Exception.CanalExisteException;
import Exception.UsuarioJaCadastradoExpection;

public class CentralDeInformacoes {
	  private ArrayList<ProgramaDeTv> todosProgramas;
	  private ArrayList<Canal> canais;
	  private ArrayList<Usuario> usuarioLista;
	  private ArrayList<Contato> contatos;
	  private ArrayList<ProgramaDeTv> programasFavoritos;

	    public CentralDeInformacoes(){
	        todosProgramas =  new ArrayList<>();
	        canais = new ArrayList<>();
	        usuarioLista = new ArrayList<>();
	        contatos = new ArrayList<Contato>();
	        programasFavoritos = new ArrayList<ProgramaDeTv>();
	    }
	    
	    //----------------------------------------------------------------------------------
	    // Usuário
	    
	     public Usuario recuperarUsuario(Usuario usuario) {
	    	for(Usuario verificando: usuarioLista ) {
	    		if(verificando.equals(usuario)) {
	    			return verificando;
	    		}
	    	}
	    	return null;
	    }
	   
	    public boolean adicionarUsuario(Usuario usuario) throws UsuarioJaCadastradoExpection{
	    	Usuario verificando = recuperarUsuario(usuario);
	    	
	    	if(verificando != null) {
	    		throw new UsuarioJaCadastradoExpection();
	    	}else {
	    		usuarioLista.add(usuario);
	    		return true;
	    	}
	    }
	    //metodo de validação de login de um usuário.
	    //logica muito feia
	    public boolean validarEntrada(String login, String senha) {
	    	
	    	for(int i = 0; i < usuarioLista.size(); i++) {
	    		if(usuarioLista.get(i).getNome().equals(login) && usuarioLista.get(i).getSenha().equals(senha)) {
	    			return true;
	    		}
	    	}
	    	
	    	return false;
	    }
	    
	    // --------------------------------------------------------------------------------
	    //Lista de ProgramaDeTv
	    
	    public boolean adicionar(ProgramaDeTv programa){
	        
	        ProgramaDeTv resposta = recuperarProgramaTvId(programa.getId());

	        if(resposta != null)
	            return false;
	        else{
	            todosProgramas.add(programa);
	            return true;
	        }
	    }
	    public ProgramaDeTv recuperarProgramaTvId(long id){
	        for (ProgramaDeTv todosPrograma : todosProgramas) {
	            if (todosPrograma.getId() == id)
	                return todosPrograma;
	        }
	        return null;
	    }
	    public void recuperarProgramaPorTipo(TipoPrograma tipo){
	        for (ProgramaDeTv todosPrograma : todosProgramas) {
	            if (todosPrograma.getTipo().equals(tipo))
	                System.out.println(todosPrograma);
	        }
	    }
	    public void excluirByIndex(int index) {
	    	todosProgramas.remove(index);
	    }
	 // --------------------------------------------------------------------------------
	 // Lista de Canal
	    
	    public boolean adicionarCanal(Canal c) throws Exception{
	    	
	        Canal resposta = recuperarCanal(c.getNome());
	        
	        if(resposta != null) {
	        	throw new CanalExisteException();
	       
	        }else if(c.getNome().isEmpty()){
	        	throw new CaixaDeTextoVaziaExpection();
	        }else{
	            canais.add(c);
	            return true;
	        }
	    }
	    
	    public Canal recuperarCanal(String nome){
	        Canal c = new Canal(nome,null,null,null);

	        for(Canal emissora: canais){
	            if(emissora.equals(c))
	                return emissora;
	        }
	        return null;
	    }
	    public Canal recuperarCanalPeloIndice(int indice){
	    	return canais.get(indice);
	    }
	  //---------------------------------------------------------------------------------
	  // Lista de Contato
	   
	    public boolean adicionarContato(Contato c) throws Exception{
	    		
	        Canal resposta = recuperarCanal(c.getNome());
	        
	        if(resposta != null) {
	        	throw new CanalExisteException();
	       
	        }else if(c.getNome().isEmpty()){
	        	throw new CaixaDeTextoVaziaExpection();
	        }else{
	            contatos.add(c);
	            return true;
	        }
	    }
	    
	    public Contato recuperarContato(String nome){
	    
	    	Contato c = new Contato(nome,null);

	        for(Contato contato: contatos){
	            if(contato.equals(c))
	                return contato;
	        }
	        return null;
	    }

	 // ---------------------------------------------------------------------------------
	 // Gets e Sets
	    
	    
	    public ArrayList<ProgramaDeTv> getTodosProgramas() {
	        return todosProgramas;
	    }
		public ArrayList<ProgramaDeTv> getProgramasFavoritos() {
			return programasFavoritos;
		}

		public void setProgramasFavoritos(ArrayList<ProgramaDeTv> programasFavoritos) {
			this.programasFavoritos = programasFavoritos;
		} 
	   
		public ArrayList<Usuario> getUsuarioLista() {
			return usuarioLista;
		}

		public void setUsuarioLista(ArrayList<Usuario> usuarioLista) {
			this.usuarioLista = usuarioLista;
		}
		public ArrayList<Contato> getContatos() {
			return contatos;
		}

		public ArrayList<Canal> getCanais() {
			return canais;
		}
	    
}
