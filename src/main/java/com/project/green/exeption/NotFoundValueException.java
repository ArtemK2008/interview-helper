package com.project.green.exeption;

public class NotFoundValueException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public NotFoundValueException(Class<?> resourceClass, String fieldName, Object fieldValue) {
        this.resourceName = resourceClass.getSimpleName();
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}