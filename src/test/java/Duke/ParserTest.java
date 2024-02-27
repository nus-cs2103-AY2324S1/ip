package Duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import Duke.GUI.Ui;
import Duke.Tasks.TaskList;
public class ParserTest {
    @Test
    public void testIncompletetAndInvalidInput() {
        String horizontalLineTop = "_______________ \n\n";
        String horizontalLineBottom = "_______________ \n";
        Ui iu = new Ui();
        TaskList tasks = new TaskList("Empty");

        String output_invalid = Parser.input("Nonsense",tasks,iu);
        assertEquals(output_invalid,
                horizontalLineTop
                        + "Someone should have paid attention in school... try again \n"
                        + horizontalLineBottom);

        String output_incomplete = Parser.input("todo", tasks, iu);
        assertEquals(output_incomplete,
                horizontalLineTop
                        + "Come on now... don't be shy, go on \n"
                        + horizontalLineBottom);
    }

    @Test
    public void testDuplicateInput() {
        String horizontalLineTop = "_______________ \n\n";
        String horizontalLineBottom = "_______________ \n";
        Ui iu = new Ui();
        TaskList tasks = new TaskList("Empty");

        Parser.input("todo homework", tasks, iu);

        String duplicate_1 = Parser.input("todo homework", tasks, iu);
        assertEquals(duplicate_1,
                horizontalLineTop
                        + "Is the schizophrenia finally starting to kick in? It's duplicated, but if you do it again I'll allow it. \n"
                        + horizontalLineBottom);

        String duplicate_2 = Parser.input("todo homework", tasks, iu);
        assertEquals(duplicate_2,
                horizontalLineTop
                        + "I'm totally not judging... \n"
                        + "[T] | [ ] | homework"
                        + " \n"
                        + " ... added to the list \n"
                        + "I wonder how you'll mess up this... "
                        + "2" + "\n"
                        + horizontalLineBottom);
    }
}
