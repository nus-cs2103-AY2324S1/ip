package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.Task;

/**
 * JUnit test class for Storage.
 */
public class StorageTest {
    private static final String TEST_FOLDER_PATH = "./test";
    private static final String TEST_FILE_NAME = "task404bot.txt";

    /**
     * Deletes all files recursively5 in the given folder.
     *
     * @param folder The folder to delete all files in.
     */
    private static void deleteFolderContent(File folder) {
        for (File subfile : folder.listFiles()) {
            if (subfile.isDirectory()) {
                deleteFolderContent(subfile);
            }
            while (!subfile.delete()) {
                System.gc();
            }
        }
    }

    /**
     * Creates a folder and a file in the given folder path.
     *
     * @param folder The folder to create.
     * @param file  The file to create.
     */
    private static void createFolderAndFile(File folder, File file) {
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                fail();
            }
        }
    }

    /**
     * Writes valid data to the given file.
     *
     * @param file  The file to write to.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeValidData(File file) throws IOException {
        FileWriter fw = new FileWriter(file.getPath());
        String fileFormatTask = String.format("T%s0%sread book\n"
                        + "D%s1%sreturn book%s1/1/2023 11:11\n",
                Storage.SEPARATOR, Storage.SEPARATOR,
                Storage.SEPARATOR, Storage.SEPARATOR, Storage.SEPARATOR);
        fw.write(fileFormatTask);
        fw.close();
    }

    /**
     * Writes corrupt data to the given file.
     *
     * @param file The file to write to.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeCorruptData(File file) throws IOException {
        FileWriter fw = new FileWriter(file.getPath());
        String fileFormatTask = String.format("T%s0%sread book\n"
                        + "D%s1%sreturn book%s21/9/2023 31:11\n",
                Storage.SEPARATOR, Storage.SEPARATOR,
                Storage.SEPARATOR, Storage.SEPARATOR, Storage.SEPARATOR);
        fw.write(fileFormatTask);
        fw.close();
    }

    @Test
    public void createTaskFile_folderNotExist_success() {
        File folder = new File(TEST_FOLDER_PATH);
        File file = new File(TEST_FOLDER_PATH + "/" + TEST_FILE_NAME);
        if (folder.exists()) {
            deleteFolderContent(folder);
            folder.delete();
        }
        assertFalse(folder.exists());
        Storage storage = new Storage(TEST_FOLDER_PATH, TEST_FILE_NAME);
        try {
            storage.createTaskFile();
            assertTrue(folder.exists());
            assertTrue(file.exists());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void createTaskFile_fileNotExist_success() {
        File folder = new File(TEST_FOLDER_PATH);
        File file = new File(TEST_FOLDER_PATH + "/" + TEST_FILE_NAME);
        if (folder.exists()) {
            deleteFolderContent(folder);
        } else {
            folder.mkdir();
        }
        assertTrue(folder.exists());
        assertFalse(file.exists());
        Storage storage = new Storage(TEST_FOLDER_PATH, TEST_FILE_NAME);
        try {
            storage.createTaskFile();
            assertTrue(folder.exists());
            assertTrue(file.exists());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void createTaskFile_fileExist_success() {
        File folder = new File(TEST_FOLDER_PATH);
        File file = new File(TEST_FOLDER_PATH + "/" + TEST_FILE_NAME);
        createFolderAndFile(folder, file);
        assertTrue(folder.exists());
        assertTrue(file.exists());
        Storage storage = new Storage(TEST_FOLDER_PATH, TEST_FILE_NAME);
        try {
            storage.createTaskFile();
            assertTrue(folder.exists());
            assertTrue(file.exists());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void loadTasks_correctFormat_success() throws IOException {
        File folder = new File(TEST_FOLDER_PATH);
        File file = new File(TEST_FOLDER_PATH + "/" + TEST_FILE_NAME);
        createFolderAndFile(folder, file);
        assertTrue(folder.exists());
        assertTrue(file.exists());
        writeValidData(file);
        Storage storage = new Storage(TEST_FOLDER_PATH, TEST_FILE_NAME);
        try {
            storage.loadTasks(true, "");
            assertTrue(folder.exists());
            assertTrue(file.exists());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void loadTasks_incorrectFormat_exceptionThrown() throws IOException {
        File folder = new File(TEST_FOLDER_PATH);
        File file = new File(TEST_FOLDER_PATH + "/" + TEST_FILE_NAME);
        createFolderAndFile(folder, file);
        assertTrue(folder.exists());
        assertTrue(file.exists());
        writeCorruptData(file);
        Storage storage = new Storage(TEST_FOLDER_PATH, TEST_FILE_NAME);
        try {
            storage.loadTasks(true, "");
            fail();
        } catch (Exception e) {
            assertTrue(file.exists());
            assertTrue(folder.exists());
        }
    }

    @Test
    public void loadTasks_userDefinedFileNotExist_exceptionThrown() {
        File folder = new File(TEST_FOLDER_PATH);
        File file = new File(TEST_FOLDER_PATH + "/user_defined.txt");
        if (folder.exists()) {
            deleteFolderContent(folder);
        } else {
            folder.mkdir();
        }
        assertTrue(folder.exists());
        assertFalse(file.exists());
        Storage storage = new Storage(TEST_FOLDER_PATH, "user_defined.txt");
        try {
            storage.loadTasks(false, "/user_defined.txt");
            fail();
        } catch (Exception e) {
            assertTrue(folder.exists());
            assertTrue(file.exists());
        }
    }

    @Test
    public void loadTasks_userDefinedFileExist_success() throws IOException {
        File folder = new File(TEST_FOLDER_PATH);
        File file = new File(TEST_FOLDER_PATH + "/user_defined.txt");
        createFolderAndFile(folder, file);
        assertTrue(folder.exists());
        assertTrue(file.exists());
        writeValidData(file);
        Storage storage = new Storage(TEST_FOLDER_PATH, "user_defined.txt");
        try {
            List<Task> ls = storage.loadTasks(false, "/user_defined.txt");
            assertTrue(folder.exists());
            assertTrue(file.exists());
            assertEquals(2, ls.size());
        } catch (Exception e) {
            fail();
        }
    }
}
