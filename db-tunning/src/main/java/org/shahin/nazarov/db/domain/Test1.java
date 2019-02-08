package org.shahin.nazarov.db.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Test1 implements Serializable {
    private static final long serialVersionUID = -821826330941829539L;

    private String id;
    private String name;
    private boolean gender;
    private String data;
    private double amount;
    private String status;
    private LocalDateTime timestamp;
}
