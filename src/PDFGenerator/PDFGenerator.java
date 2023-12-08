package PDFGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.PublicKey;
import java.time.LocalDate;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator 
{
	public PDFGenerator()
	{
	}

		
		public static void generarFactura(String placa, LocalDate fechaInicial,
				String nombreCliente, String numeroDocumento, String telefono, String correo,
				char categoriaCarro, int precioDiaCategoria, int numeroDeDias,
				String seguro, int precioSeguroDia, int numConductoresAdicionales,
				int precioCondAdicional, boolean diferenteSede, int tarifaCambioSede)
		{
		
		Document document = new Document();
		
		//Crea el nombre factura con la placa y la fecha de la factura
		String nombreFactura = placa + "-" + fechaInicial.toString() + ".pdf";
		
        try {
            // Crear el escritor PDF
            PdfWriter.getInstance(document, new FileOutputStream("data/facturas/" + nombreFactura));

            // Abrir el documento
            document.open();
            
            //definir tamaño
            document.setPageSize(PageSize.LEGAL);

            // Agregar contenido al documento
            
            // Configuración tabla título
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            float[] columnWidths = {2.5f, 1f}; // Ancho relativo de las columnas
            table.setWidths(columnWidths);
            
            Font fuenteTitulo = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);

            //celda 1: titulo
            PdfPCell cell1 = new PdfPCell();
            cell1.addElement(new Paragraph("Recibo de alquiler", fuenteTitulo));
            cell1.setBorder(PdfPCell.NO_BORDER);
            
            PdfPCell cell2 = new PdfPCell();
            
            //configuración tabla 2: fecha
            PdfPTable tablaFecha = new PdfPTable(2);
            float[] columnWidthsFecha = {1f, 1.5f};
            tablaFecha.setWidths(columnWidthsFecha);
            
            PdfPCell celdaLabFecha = new PdfPCell();
            PdfPCell celdaFecha = new PdfPCell();
            
            Font fuenteLabFecha = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
            Font fuenteFecha = new Font(Font.FontFamily.COURIER, 10);
            
            celdaLabFecha.addElement(new Paragraph("Fecha:" , fuenteLabFecha));
            celdaFecha.addElement(new Paragraph(fechaInicial.toString() , fuenteFecha));
            
            celdaLabFecha.setBorder(PdfPCell.NO_BORDER);
            celdaFecha.setBorder(PdfPCell.NO_BORDER);
            
            tablaFecha.addCell(celdaLabFecha);
            tablaFecha.addCell(celdaFecha);
            
            //se añade la tabla fecha a la celda 2
            cell2.addElement(tablaFecha);
            cell2.setBorder(PdfPCell.NO_BORDER);
            
            table.addCell(cell1);
            table.addCell(cell2);
            
            document.add(table);
            
            //linea separadora
            Font fuenteEnBlanco = new Font(Font.FontFamily.COURIER, 10,Font.BOLD,BaseColor.WHITE);
            document.add(new Paragraph("Nombre", fuenteEnBlanco));
            document.add(createHorizontalLine());
            
            //seccion información cliente
            Font fuenteLabCliente = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);
            Font fuenteLabNegrita = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);
            Font fuenteLabInfo = new Font(Font.FontFamily.COURIER, 11);
            
            document.add(new Paragraph("Información Cliente" , fuenteLabCliente));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Nombre", fuenteLabNegrita));
            document.add(new Paragraph(nombreCliente, fuenteLabInfo));
            document.add(new Paragraph("Documento", fuenteLabNegrita));
            document.add(new Paragraph(numeroDocumento, fuenteLabInfo));
            document.add(new Paragraph("Telefono", fuenteLabNegrita));
            document.add(new Paragraph(telefono, fuenteLabInfo));
            document.add(new Paragraph("Correo", fuenteLabNegrita));
            document.add(new Paragraph(correo, fuenteLabInfo));
            
            //linea separadora
            document.add(new Paragraph("Nombre", fuenteEnBlanco));
            document.add(createHorizontalLine());
            document.add(new Paragraph("Nombre", fuenteEnBlanco));
            
            //parte de los productos
            
            PdfPTable tablaProductos = new PdfPTable(3);
            float[] columnWidthsEncabezados = {3f, 0.8f, 1.8f};
            tablaProductos.setWidths(columnWidthsEncabezados);
            
            PdfPCell encabezadoProducto = new PdfPCell();
            PdfPCell encabezadoDias = new PdfPCell();
            PdfPCell encabezadoPrecio = new PdfPCell();
            
            Font fuenteEncabezados = new Font(Font.FontFamily.COURIER, 11,Font.NORMAL,BaseColor.WHITE);
            Font fuenteInformacion = new Font(Font.FontFamily.COURIER, 8,Font.NORMAL);
            
            encabezadoProducto.addElement(new Paragraph("Producto" , fuenteEncabezados));
            encabezadoDias.addElement(new Paragraph("Días", fuenteEncabezados));
            encabezadoPrecio.addElement(new Paragraph("Precio", fuenteEncabezados));
            
            encabezadoProducto.setBackgroundColor(BaseColor.DARK_GRAY);
            encabezadoDias.setBackgroundColor(BaseColor.DARK_GRAY);
            encabezadoPrecio.setBackgroundColor(BaseColor.DARK_GRAY);
            
            
            tablaProductos.addCell(encabezadoProducto);
            tablaProductos.addCell(encabezadoDias);
            tablaProductos.addCell(encabezadoPrecio);
            
            //producto1
            PdfPCell cellProducto1 = new PdfPCell();
            PdfPCell cellDias1 = new PdfPCell();
            PdfPCell cellPrecio1 = new PdfPCell();
            
            int intPrecio1 = precioDiaCategoria * numeroDeDias;
            String producto1 = "Vehiculo de categoría " + categoriaCarro;
            String dias1 = String.valueOf(numeroDeDias);
            String precio1 = String.valueOf(intPrecio1);
            
            cellProducto1.addElement(new Paragraph(producto1, fuenteLabInfo));
            cellDias1.addElement(new Paragraph(dias1, fuenteLabInfo));
            cellPrecio1.addElement(new Paragraph(precio1, fuenteLabInfo));
            
            cellProducto1.setBorder(PdfPCell.NO_BORDER);
            cellDias1.setBorder(PdfPCell.NO_BORDER);
            cellPrecio1.setBorder(PdfPCell.NO_BORDER);
            
            tablaProductos.addCell(cellProducto1);
            tablaProductos.addCell(cellDias1);
            tablaProductos.addCell(cellPrecio1);
            
            //producto2 seguro
            PdfPCell cellSeguro = new PdfPCell();
            PdfPCell cellDias2 = new PdfPCell();
            PdfPCell cellPrecio2 = new PdfPCell();
            
            int intPrecio2 = precioSeguroDia * numeroDeDias;
            String precio2 = String.valueOf(intPrecio2);
            
            cellSeguro.addElement(new Paragraph(seguro, fuenteLabInfo));
            cellDias2.addElement(new Paragraph(dias1, fuenteLabInfo));
            cellPrecio2.addElement(new Paragraph(precio2, fuenteLabInfo));
            
            cellSeguro.setBorder(PdfPCell.NO_BORDER);
            cellDias2.setBorder(PdfPCell.NO_BORDER);
            cellPrecio2.setBorder(PdfPCell.NO_BORDER);
            
            tablaProductos.addCell(cellSeguro);
            tablaProductos.addCell(cellDias2);
            tablaProductos.addCell(cellPrecio2);
            
            //producto 3 conductores adicionales
            PdfPCell cellCondAdicionales = new PdfPCell();
            PdfPCell cellDiasNull = new PdfPCell();
            PdfPCell cellPrecio3 = new PdfPCell();
            
            int intPrecio3 = numConductoresAdicionales * precioCondAdicional;
            String producto3 = "Cond. adicionales x" + numConductoresAdicionales;
            String precio3 = String.valueOf(intPrecio3);
            
            cellCondAdicionales.addElement(new Paragraph(producto3, fuenteLabInfo));
            cellDiasNull.addElement(new Paragraph(""));
            cellPrecio3.addElement(new Paragraph(precio3, fuenteLabInfo));
            
            cellCondAdicionales.setBorder(PdfPCell.NO_BORDER);
            cellDiasNull.setBorder(PdfPCell.NO_BORDER);
            cellPrecio3.setBorder(PdfPCell.NO_BORDER);
            
            tablaProductos.addCell(cellCondAdicionales);
            tablaProductos.addCell(cellDiasNull);
            tablaProductos.addCell(cellPrecio3);
            
            //producto 4 cambio de sede
            
            if(diferenteSede)
            {
            	PdfPCell cellCambioSede = new PdfPCell();
                PdfPCell cellPrecio4 = new PdfPCell();
                
                int intPrecio4 = tarifaCambioSede;
                String producto4 = "Tarifa cambio de sede";
                String precio4 = String.valueOf(intPrecio4);
                
                cellCambioSede.addElement(new Paragraph(producto4, fuenteLabInfo));
                cellPrecio4.addElement(new Paragraph(precio4, fuenteLabInfo));
                
                cellCambioSede.setBorder(PdfPCell.NO_BORDER);
                cellPrecio4.setBorder(PdfPCell.NO_BORDER);
                
                tablaProductos.addCell(cellCambioSede);
                tablaProductos.addCell(cellDiasNull);
                tablaProductos.addCell(cellPrecio4);
            }
            
            document.add(tablaProductos);
            
            // Cerrar el documento
            document.close();

            System.out.println("El PDF ha sido creado correctamente.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
	System.out.println("Programa pdf ejecutado");
	}
	
	 private static PdfPTable createHorizontalLine() {
	        PdfPTable table = new PdfPTable(1);
	        PdfPCell cell = new PdfPCell();
	        
	        // Set the height of the cell to create a line
	        cell.setFixedHeight(5f);

	        // Set border color and width
	        cell.setBorderColorBottom(BaseColor.BLACK);
	        cell.setBorder(Rectangle.OUT_TOP);
	        cell.setBorderWidthBottom(1f);

	        // Add the cell to the table
	        table.addCell(cell);

	        return table;
	    }
}


