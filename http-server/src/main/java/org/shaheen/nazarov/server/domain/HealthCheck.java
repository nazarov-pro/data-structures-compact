package org.shaheen.nazarov.server.domain;

import lombok.Data;
import org.shaheen.nazarov.server.util.ServerConstants;

import java.util.List;

@Data
public class HealthCheck {
    private String status = ServerConstants.STATUS_UP;
    private int runningThreads;
    private String maxThreads;
    private String startedTime;
    private String time;
    private String port;
    private String path;
    private List<String> contexts;
    private String heapCurrent;
    private String heapMax;
}
