package br.com.erudio.exceptions;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@RequiredArgsConstructor
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;

}
