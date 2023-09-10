package components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testDeleteLine_FileEmpty() {
        assertThrows(DukeException.class, () -> storage.deleteLine(1));
    }

    @Test
    public void testDeleteLine_InvalidLineNumber() {
        assertThrows(DukeException.class, () -> storage.deleteLine(0));
        assertThrows(DukeException.class, () -> storage.deleteLine(-1));
    }

    @Test
    public void testDeleteLine_LineNumberGreaterThanFileLines() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write("Line 1");
            bw.newLine();
            bw.write("Line 2");
        }
        assertThrows(DukeException.class, () -> storage.deleteLine(3));
    }

    @Test
    public void testDeleteLine_ValidLineNumber() throws IOException, DukeException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write("Line 1");
            bw.newLine();
            bw.write("Line 2");
        }
        assertDoesNotThrow(() -> storage.deleteLine(1));
    }
}
