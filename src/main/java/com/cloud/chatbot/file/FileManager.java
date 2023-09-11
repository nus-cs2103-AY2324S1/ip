package com.cloud.chatbot.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.cloud.chatbot.annotations.Nullable;



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

    /**
     * Returns a JSON object by reading from disk.
     */
    public @Nullable JSONObject read() {
        //TODO fix tests lib
        //TODO neater err print?
        //TODO enum for item type, for json keys
        String string;
        try {
            string = Files.readString(this.getFile().toPath());
        } catch (IOException e) {
            // The file probably doesn't exist yet
            return null;
        }

        return new JSONObject(string);
    }

    /**
     * Writes the specified JSON object to disk.
     *
     * @param json The JSON object to write.
     */
    public void write(JSONObject json) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.getFile());
        } catch (IOException e) {
            System.err.println("ERR Could not create FileWriter!");
            System.err.println(e);
            return;
        }

        try {
            writer.write(json.toString(4));
        } catch (IOException e) {
            System.err.println("ERR Could not write to file!");
            System.err.println(e);
            return;
        }

        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("ERR Could not close FileWriter!");
            System.err.println(e);
            return;
        }
    }
}
