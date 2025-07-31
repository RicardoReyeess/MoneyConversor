package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JsonGenerator {
    public void saveJson(Object object, String nombreArchivo) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(nombreArchivo + ".json");
        fileWriter.write(gson.toJson(object));
        fileWriter.close();
    }
}