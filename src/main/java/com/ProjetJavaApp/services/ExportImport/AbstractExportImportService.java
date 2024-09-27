package com.ProjetJavaApp.services.ExportImport;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public abstract class AbstractExportImportService implements ExportImportService {

    private final ConversionService conversionService;

    public AbstractExportImportService() {
        this.conversionService = new DefaultConversionService();
    }

    protected <T> String getFieldValue(T item, String fieldName, Class<T> type) {
        Field field = ReflectionUtils.findField(type, fieldName);
        if (field != null) {
            ReflectionUtils.makeAccessible(field);
            Object value = ReflectionUtils.getField(field, item);
            return value != null ? value.toString() : "";
        }
        return "";
    }

    protected <T> void setFieldValue(T item, String fieldName, String value, Class<T> type) {
        Field field = ReflectionUtils.findField(type, fieldName);
        if (field != null) {
            ReflectionUtils.makeAccessible(field);
            Object convertedValue = convertStringToFieldType(field, value);
            ReflectionUtils.setField(field, item, convertedValue);
        }
    }

    protected Object convertStringToFieldType(Field field, String value) {
        Class<?> targetType = field.getType();

        if (String.class.equals(targetType)) {
            return value;
        }

        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        if (conversionService.canConvert(String.class, targetType)) {
            return conversionService.convert(value, targetType);
        }

        if (LocalDate.class.equals(targetType)) {
            return LocalDate.parse(value);
        }

        if (LocalDateTime.class.equals(targetType)) {
            return LocalDateTime.parse(value);
        }

        if (Date.class.equals(targetType)) {
            return new Date(Long.parseLong(value));
        }

        if (targetType.isEnum()) {
            return Enum.valueOf((Class<Enum>) targetType, value);
        }

        return null;
    }

    protected <T> T createItem(Class<T> type, List<String> fieldNames, String[] values) throws Exception {
        T item = type.getDeclaredConstructor().newInstance();
        for (int i = 0; i < fieldNames.size(); i++) {
            setFieldValue(item, fieldNames.get(i), values[i], type);
        }
        return item;
    }
}
