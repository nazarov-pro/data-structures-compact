package org.shahin.nazarov.jdbi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 40000000L;

    private Integer id;
    private String username;
    private String password;
}
