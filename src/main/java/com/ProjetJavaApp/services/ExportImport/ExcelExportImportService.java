package com.ProjetJavaApp.services.ExportImport;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Primary
public class ExcelExportImportService extends AbstractExportImportService {

    @Override
    public <T> byte[] exportData(Class<T> type, List<String> fieldNames, List<T> data) throws Exception {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < fieldNames.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fieldNames.get(i));
        }

        int rowNum = 1;
        for (T item : data) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < fieldNames.size(); i++) {
                Cell cell = row.createCell(i);
                String value = getFieldValue(item, fieldNames.get(i), type);
                cell.setCellValue(value);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();
        return baos.toByteArray();
    }

    @Override
    public <T> List<T> importData(Class<T> type, List<String> fieldNames, byte[] data) throws Exception {
        List<T> result = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(data));
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();  // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String[] values = new String[fieldNames.size()];
            for (int i = 0; i < fieldNames.size(); i++) {
                Cell cell = row.getCell(i);
                values[i] = cell.getStringCellValue();
            }
            T item = createItem(type, fieldNames, values);
            result.add(item);
        }

        workbook.close();
        return result;
    }
}
