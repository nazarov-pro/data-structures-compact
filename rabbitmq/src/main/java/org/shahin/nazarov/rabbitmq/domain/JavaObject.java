package org.shahin.nazarov.rabbitmq.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class JavaObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String data;
    private int index;
}
