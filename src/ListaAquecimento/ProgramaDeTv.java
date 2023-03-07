package ListaAquecimento;
import java.time.DayOfWeek;
import java.util.ArrayList;


public class ProgramaDeTv {
	private long id;
    private String nome;
    private TipoPrograma tipo;
    private DayOfWeek[] dias;
    private Canal canal;
    private String horario;
    private String dataDeRetorno;
    private String status;
    //Atributos complementares.
    //séries regulares
    private String genero;
    private String estilo;
    private int temporada;
//    programas continuos e Reality shows
    private int apresentadores;
    
    //preciso encontrar uma forma de adicionar o Day of week em lista
    // mudei dia para Day of week e deu erro no mensageiro.
    //realizei mudanças no código mensageiro no for de Dias.
    
    public ProgramaDeTv(String nome, TipoPrograma tipo, Canal canal,DayOfWeek[] dias ){
        id = System.currentTimeMillis();
        this.nome = nome;
        this.tipo = tipo;
        this.canal = canal;
        this.dias = dias;
    }
    
    //Série regular
    public ProgramaDeTv(String nome, TipoPrograma tipo, String horario, Canal canal, String genero, String estilo, int temporada, String status, String dataDeRetorno, DayOfWeek[] dias){
        id = System.currentTimeMillis();
        this.nome = nome;
        this.tipo = tipo;
        this.horario = horario;
        this.canal = canal;
        this.genero = genero;
        this.estilo = estilo;
        this.temporada = temporada;
        this.status = status;
        this.dataDeRetorno = dataDeRetorno;
        this.dias = dias;
        
    }
//    programas continuos
    public ProgramaDeTv(String nome, TipoPrograma tipo, String horario, Canal canal,  int apresentadores, int temporada, String status, String dataDeRetorno,DayOfWeek[] dias){
        id = System.currentTimeMillis();
        this.nome = nome;
        this.tipo = tipo;
        this.canal = canal;
        this.horario = horario;
        this.apresentadores = apresentadores;
        this.temporada = temporada;
        this.status = status;
        this.dataDeRetorno = dataDeRetorno;
        this.dias = dias;
        
    }
//     Reality show
    public ProgramaDeTv(String nome, TipoPrograma tipo, String horario, Canal canal,  int apresentadores, String status, String dataDeRetorno, DayOfWeek[] dias){
		id = System.currentTimeMillis();
		this.nome = nome;
		this.tipo = tipo;
		this.canal = canal;
		this.horario = horario;
		this.apresentadores = apresentadores;
		this.status = status;
		this.dataDeRetorno = dataDeRetorno;
		this.dias = dias;
      
  }
    public String toString(){
        return "Programa: " + nome + " Dia Semana: " + dias + "Canal:" + canal;
    }
    public boolean equals(ProgramaDeTv programa){
        return id == programa.id;
    }
  
    public String retornarDias() {
		String r="";
		for(int i = 0; i < 7; i++) {
			if(dias[i] == null)
				continue;
			if(dias[i] == DayOfWeek.MONDAY) 
				r+="seg-";
			if(dias[i] == DayOfWeek.TUESDAY)
				r+="ter-";
			if(dias[i] == DayOfWeek.WEDNESDAY)
				r+="qua-";
			if(dias[i] == DayOfWeek.THURSDAY)
				r+="qui-";
			if(dias[i] == DayOfWeek.FRIDAY)
				r+="sex-";
			if(dias[i] == DayOfWeek.SATURDAY)
				r+="sab-";
			if(dias[i] == DayOfWeek.SUNDAY)
				r+="dom-";
		}
		
		return r;
	}

    public TipoPrograma getTipo() {
        return tipo;
    }
	public long getId() {
        return id;
    }

    public Canal getCanal() {
        return canal;
    }

    public String getNome() {
        return nome;
    }

	public String getGenero() {
		return genero;
	}

	public String getEstilo() {
		return estilo;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTipo(TipoPrograma tipo) {
		this.tipo = tipo;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public int getApresentadores() {
		return apresentadores;
	}

	public void setApresentadores(int apresentadores) {
		this.apresentadores = apresentadores;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getDataDeRetorno() {
		return dataDeRetorno;
	}

	public void setDataDeRetorno(String dataDeRetorno) {
		this.dataDeRetorno = dataDeRetorno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public DayOfWeek[] getDias() {
		return dias;
	}
	public void setDias(DayOfWeek[] dias) {
		this.dias = dias;
	}
	
}
