package org.shahin.nazarov.servermanagement.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class ProcessStart {

    public String run(Path folder, String fileName, String additionalParams) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-jar",
                additionalParams,
                fileName);
        pb.directory(folder.toFile());
        Process proc =  pb.start();
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));
        String s = stdInput.readLine();
        if(s == null || s.isEmpty()){
            stdInput = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));
            s = stdInput.readLine();
        }
        return s;
    }
}
