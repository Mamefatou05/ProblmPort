package com.ProjetJavaApp.services.ExportImport;

import java.util.List;

public interface ExportImportService {
    <T> byte[] exportData(Class<T> type, List<String> fieldNames, List<T> data) throws Exception;

    <T> List<T> importData(Class<T> type, List<String> fieldNames, byte[] data) throws Exception;
}
