package com.pixburb.pixburbcommerce.data;

import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

import java.util.Arrays;

public class Response {

    private int status;

    private String errorMessage;

    private String displayMessage;

    private String[] data;

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

    public String[] getData() {
        return data;
    }

    public void setData(final String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", displayMessage='" + displayMessage + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
