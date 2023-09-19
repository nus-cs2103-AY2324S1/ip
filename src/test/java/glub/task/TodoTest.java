package glub.task; //same package as the class being tested

import glub.Glub;
import glub.GlubException;
import glub.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void toSaveFormat_success(){
        assertEquals("T|X|run far\n", new ToDo("run far", true).toSaveFormat());
    }

}