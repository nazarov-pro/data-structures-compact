package org.shaheen.nazarov.server.domain;

import lombok.Data;
import org.shaheen.nazarov.server.util.ServerConstants;

import java.time.LocalDateTime;

@Data
public class ApiResponse<M> {
    private String description;
    private String code;
    private LocalDateTime timestamp;
    private M data;

    public void ok(M data) {
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.code = ServerConstants.STATUS_UP;
        this.description = ServerConstants.STATUS_UP_DESCRIPTION;
    }
}
