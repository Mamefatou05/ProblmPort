package com.ProjetJavaApp.services.ExportImport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PDFExportImportService extends AbstractExportImportService {

    @Override
    public <T> byte[] exportData(Class<T> type, List<String> fieldNames, List<T> data) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        PdfPTable table = new PdfPTable(fieldNames.size());
        for (String fieldName : fieldNames) {
            table.addCell(new Phrase(fieldName));
        }

        for (T item : data) {
            for (String fieldName : fieldNames) {
                String value = getFieldValue(item, fieldName, type);
                table.addCell(new Phrase(value));
            }
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }

    @Override
    public <T> List<T> importData(Class<T> type, List<String> fieldNames, byte[] data) throws Exception {
        List<T> result = new ArrayList<>();
        PdfReader reader = new PdfReader(data);

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            String pageContent = PdfTextExtractor.getTextFromPage(reader, i);
            String[] lines = pageContent.split("\n");

            for (int j = 1; j < lines.length; j++) {  // Skip header
                String[] values = lines[j].split("\t");
                T item = createItem(type, fieldNames, values);
                result.add(item);
            }
        }

        reader.close();
        return result;
    }
}
