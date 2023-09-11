package com.cloud.chatbot.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.json.JSONObject;



/**
 * Handles reading from and writing to disk.
 */
public final class FileManager {
    private static final String RELATIVE_PATH = "./data/items.json";

    /**
     * Initialises the FileManager.
     */
    public FileManager() {
        // Create directories as needed
        File file = getFile();
        File folder = file.getParentFile();
        folder.mkdirs();
    }

    private File getFile() {
        return Paths.get(FileManager.RELATIVE_PATH).toFile();
    }

    /*
     * Saves the specified JSON object to disk.
     *
     * @param json The JSON object to save.
     */
    public void save(JSONObject json) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.getFile());
        } catch (IOException e) {
            System.out.println("ERR Could not create FileWriter!");
            System.out.println(e);
            return;
        }

        try {
            writer.write(json.toString());
        } catch (IOException e) {
            System.out.println("ERR Could not write to file!");
            System.out.println(e);
            return;
        }

        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("ERR Could not close FileWriter!");
            System.out.println(e);
            return;
        }
    }
}
