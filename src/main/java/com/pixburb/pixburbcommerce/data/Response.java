package com.pixburb.pixburbcommerce.data;

import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

import java.util.Arrays;
import java.util.List;

public class Response {

    private int status;

    private String errorMessage;

    private String displayMessage;

    private List<Object> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(final String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
