package com.cloud.chatbot.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.cloud.chatbot.Ui;
import com.cloud.chatbot.annotations.Nullable;



/**
 * Handles reading from and writing to file.
 */
public final class FileStorage {
    private static final String RELATIVE_PATH = "./data/items.json";

    /**
     * Initialises the FileStorage.
     */
    public FileStorage() {
        // Create directories as needed
        File file = getFile();
        File folder = file.getParentFile();
        folder.mkdirs();
    }

    private File getFile() {
        return Paths.get(FileStorage.RELATIVE_PATH).toFile();
    }

    /**
     * Returns a JSON object by reading from file.
     */
    public @Nullable JSONObject read() {
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
     * Writes the specified JSON object to file.
     *
     * @param json The JSON object to write.
     */
    public void write(JSONObject json) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.getFile());
        } catch (IOException e) {
            Ui.error(
                "Could not create FileWriter",
                e
            );
            return;
        }

        try {
            writer.write(json.toString(4));
        } catch (IOException e) {
            Ui.error(
                "Could not write to file",
                e
            );
            return;
        }

        try {
            writer.close();
        } catch (IOException e) {
            Ui.error(
                "Could not close FileWriter",
                e
            );
            return;
        }
    }
}
