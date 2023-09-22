package components;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageTest {

    private Storage storage;
    private File tempFile;

    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException, DukeException {
        tempFile = File.createTempFile("temp", ".txt", tempDir.toFile());
        storage = new Storage(tempDir.toString(), tempFile.getAbsolutePath());
        storage.loadOrCreateFile();
    }

    @Test
    public void testDeleteLineWhenFileEmpty() {
        assertThrows(DukeException.class, () -> storage.deleteLine(1));
    }

    @Test
    public void testDeleteLineWithInvalidLineNumber() {
        assertThrows(DukeException.class, () -> storage.deleteLine(0));
        assertThrows(DukeException.class, () -> storage.deleteLine(-1));
    }

    @Test
    public void testDeleteLineWithLineNumberGreaterThanFileLines() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write("Line 1");
            bw.newLine();
            bw.write("Line 2");
        }
        assertThrows(DukeException.class, () -> storage.deleteLine(3));
    }

    @Test
    public void testDeleteLineWithValidLineNumber() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write("Line 1");
            bw.newLine();
            bw.write("Line 2");
        }
        assertDoesNotThrow(() -> storage.deleteLine(1));
    }
}
