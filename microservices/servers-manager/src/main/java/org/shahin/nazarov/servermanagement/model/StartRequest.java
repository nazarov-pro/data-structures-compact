package org.shahin.nazarov.servermanagement.model;

import lombok.Data;

@Data
public class StartRequest {
    private String fileName;
    private int maxHeapMb;

}
