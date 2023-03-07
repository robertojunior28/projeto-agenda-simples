package ListaAquecimento;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorRelatorio {
		
	 public static void obterProgramacaoDeUmCanal(/*Canal c*/) {
	        CentralDeInformacoes central = MetodosPersistencia.obterCentral("arquivo.xml");
	        //gerando documentos definindo tamanho da pagina e as margens.
	        Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
	      /*  String salva = "";

	        for(ProgramaDeTv p: cadastrar.getTodosProgramas()){
	            if(p.getCanal().equals(c)){
	                salva += p.getNome() + "- ";
	            }else
	                System.out.println("nao entrou");
	        }*/
	             try {
	                    //associa o documento e a saida
	                    PdfWriter.getInstance(doc, new FileOutputStream("relatório.pdf"));

	                    doc.open();
	                    doc.add(new Paragraph("Programas que passaram no canal "));
	                    doc.add(new Paragraph("fgasd"));

	                    doc.close();
	                

	                } catch (FileNotFoundException | DocumentException e) {
	                    e.printStackTrace();
	                }

	    }
   }


