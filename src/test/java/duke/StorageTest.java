package duke;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

  @Test
  public void loadFile_validFilePathNotEmpty_allFilesLoaded() {
    Storage storage = new Storage("./src/test/java/duke/StorageTest3Tasks.txt");
    LinkedList<Task> loadedTaskLists = storage.loadFile();
    assertEquals(loadedTaskLists.get(0).toString(), "[T][ ] task");
    assertEquals(loadedTaskLists.get(1).toString(), "[E][ ] task    task (from: 11 Apr 2000 to: 11 Apr 1999)");
    assertEquals(loadedTaskLists.get(2).toString(), "[D][X] task (by: 11 Apr 2001)");
  }

  @Test
  void loadFile_validFilePathEmpty_emptyList() {
    Storage storage = new Storage("./src/test/java/duke/StorageTestEmpty.txt");
    LinkedList<Task> loadedTaskLists = storage.loadFile();
    assertEquals(loadedTaskLists.size(), 0);
  }

  @Test
  void loadFile_invalidFilePath_emptyList() {
    Storage storage = new Storage("./src/test/java/duke/doesNotExist.txt");
    LinkedList<Task> loadedTaskLists = storage.loadFile();
    assertEquals(loadedTaskLists.size(), 0);
  }

}
