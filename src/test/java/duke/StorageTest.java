package duke; //if packages are matched, the test class can access package-private members of the target class

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StorageTest {

//    @Test
//    public void storageTest1() throws FileNotFoundException {
//        Storage store = new Storage("storageTest.txt");
//        TaskList list = new TaskList(store.load());
//		System.out.println(list.getTask(0).toString());
//        assertEquals("[T][ ] read book", list.getTask(0).toString());
//    }
//
//	public void saveTest() {
//		ArrayList<Task> arr = new ArrayList {new Todo("read book"), new Deadline("return book", "2023")};
//		TaskList list = new TaskList {new Todo("read book"), new Deadline("return book", "2023")};
//		Storage store = new Storage("storage.txt");
//
//	}
}
