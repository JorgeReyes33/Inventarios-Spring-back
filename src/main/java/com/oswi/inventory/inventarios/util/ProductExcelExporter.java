package com.oswi.inventory.inventarios.util;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import com.oswi.inventory.inventarios.models.Product;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ProductExcelExporter {
    
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Product> product;

    public ProductExcelExporter(List<Product> products) {
        this.product = products;
        //Instanciar el objeto book
        workbook = new XSSFWorkbook();
    }

    //Dar estilo al header de excel
    private void writeHeaderLine() {
        //Asignar nombre a la hoja de excel
        sheet = workbook.createSheet("Resultado");
        //Primer file
        Row row = sheet.createRow(0);
        //Definir estilo para la celda
        CellStyle style = workbook.createCellStyle();
        //Definir la fuente
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Nombre", style);
        createCell(row, 2, "Precio", style);
        createCell(row, 3, "Cantidad", style);
        createCell(row, 4, "Categoria", style);
    }

    //Crear las celdas
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for( Product result : product ) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, String.valueOf(result.getId()), style);
            createCell(row, columnCount++, String.valueOf(result.getName()), style);
            createCell(row, columnCount++, String.valueOf(result.getPrice()), style);
            createCell(row, columnCount++, String.valueOf(result.getAccount()), style);
            createCell(row, columnCount++, String.valueOf(result.getCategory().getName()), style);
        }
    }

    //Realizar la exportacion
    //IOException es muy util cuando trabajamos con archivos
    public void export(HttpServletResponse response) throws IOException {
        
        writeHeaderLine(); //Pasar los headers
        writeDataLines(); //Pasar los datos

        //Escritura en nuestro archivo
        ServletOutputStream servletOutput = response.getOutputStream();
        //Escribimos el libro excel que estabamos trabajando
        workbook.write(servletOutput);
        workbook.close();

        servletOutput.close();
    }
    
}
