package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MapLoader{

    public static Map load(String mapFile) {
        List<String[]> representation = new ArrayList<String[]>();

        try{
            FileReader reader = new FileReader(mapFile);
            BufferedReader bReader = new BufferedReader(reader);
            String line = "";
            while((line = bReader.readLine())!=null){
                representation.add(line.split(""));
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        if(representation.isEmpty()){
            return null;
        }
        String[][] rep = new String[representation.size()][representation.get(0).length];
        int i = 0;
        for(String[] row : representation){
            rep[i] = row;
            i++;
        }
        return new Map(rep, true);
    }
}
