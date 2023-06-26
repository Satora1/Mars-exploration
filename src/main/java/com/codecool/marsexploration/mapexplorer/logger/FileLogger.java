package com.codecool.marsexploration.mapexplorer.logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger{

    String filename;

    public FileLogger(String filename){
        this.filename = filename;
    }

    @Override
    public void log(String message) {
        try {
            FileWriter logWriter = new FileWriter(filename, true);
            logWriter.write(message);
            logWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
