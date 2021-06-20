package ie.cct.cbwa.cashowcase.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String message;

    public StandardError(Integer status, String message) {
        super();
        this.status = status;
    }

    public StandardError() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
