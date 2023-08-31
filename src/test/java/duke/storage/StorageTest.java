package duke.storage;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



public class StorageTest {

	@Test
	public void correctFile_load() {
		Storage storage = new Storage("./test.txt");
		try {
			assertEquals(storage.load().size(), 3);
		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void wrongFile_load(){
		Storage storage = new Storage("./wrongTest.txt");
		try {
			assertEquals(storage.load().size(), 3);
			fail();
		} catch (DukeException e) {
			assertEquals("No tasklist record was found", e.getMessage());
		}
	}
}
