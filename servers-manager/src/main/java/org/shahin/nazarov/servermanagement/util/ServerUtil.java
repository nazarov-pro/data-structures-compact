package org.shahin.nazarov.servermanagement.util;

import org.shahin.nazarov.servermanagement.model.JarFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class ServerUtil {


    public List<JarFile> jarFiles() throws IOException {
        List<JarFile> jarFileList = new ArrayList<>();
        Files.list(ManagementConstants.path).filter(p -> p.getFileName().toString().endsWith(".jar"))
                .forEach(p -> {
            JarFile jarFile = new JarFile();
            jarFile.setName(p.getFileName().toString());
            try {
                Files.move(p, p, StandardCopyOption.ATOMIC_MOVE);
                jarFile.setOpen(false);
            } catch (IOException e) {
                jarFile.setOpen(true);
            }

            try {
                jarFile.setModified(Files.getLastModifiedTime(p).toString());
            } catch (IOException e) {
                jarFile.setModified("N/A");
            }
            jarFileList.add(jarFile);
        });
        return jarFileList;
    }

}
