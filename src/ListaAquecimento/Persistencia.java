package ListaAquecimento;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Persistencia {
	
	   XStream xStream = new XStream(new DomDriver("UTF-8"));
	    
	   		/**
	   		 * metodo salvar dados nos arquivos
	     * pegando o programa a ser salvo e o nome do arquivo 
	     * onde será salvo
	   		 * @param programa recebe um programa
	   		 * @param arquivoPrograma recebe um nome de arquivo existente
	   		 */
	        public void salvarCentral(CentralDeInformacoes programa, String arquivoPrograma){
	            try {
	                File arquivo = new File(arquivoPrograma);
	                arquivo.createNewFile();
	                PrintWriter pW = new PrintWriter(arquivo, "UTF-8");
	                String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	                xml += xStream.toXML(programa);
	                pW.print(xml);
	                pW.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	    /**
	     *  esse método recupera se o arquivo existe pelo seu nome, caso não exista ele 
	     * lança uma exceção do tipo FileNotFoundException e cria uma nova central 
	     * @param nomeArquivo nome do arquivo
	     * @return retorna uma central
	     */
	    public CentralDeInformacoes recuperarCentral(String nomeArquivo){
	        try {
	            File arquivo = new File(nomeArquivo);
	            xStream.allowTypes(new Class[] {CentralDeInformacoes.class,ProgramaDeTv.class,Canal.class, Usuario.class,Contato.class});
	            if (arquivo.exists()) {
	                return (CentralDeInformacoes) xStream.fromXML(new FileInputStream(arquivo));
	            }
	        }catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return new CentralDeInformacoes();
	    }
}
