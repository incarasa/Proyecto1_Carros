package pasarelasPago;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class TestPDF {
	public static void main (String[] args) {
		
		Document document = new Document();

	        try {
	            // Crear el escritor PDF
	            PdfWriter.getInstance(document, new FileOutputStream("ruta/del/archivo.pdf"));

	            // Abrir el documento
	            document.open();

	            // Agregar contenido al documento
	            document.add(new Paragraph("¡Hola, mundo!"));

	            // Cerrar el documento
	            document.close();

	            System.out.println("El PDF ha sido creado correctamente.");
	        } catch (DocumentException | FileNotFoundException e) {
	            e.printStackTrace();
	        }
		System.out.println("Programa pdf ejecutado");
	}
}
