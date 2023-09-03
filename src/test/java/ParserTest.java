import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import vedaexceptions.ExcessiveArgumentException;
import vedaexceptions.IncorrectInputException;
import vedaexceptions.NoDescriptionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_emptyInput_negativeOne() {
        int methodType = Parser.parse("");

        assertEquals(-1, methodType);
    }

    @Test
    public void parse_exit_one() {
        int methodType = Parser.parse("bye");

        assertEquals(0, methodType);
    }

    @Test
    public void parse_statementWithExitFront_negativeOne() {
        int methodType = Parser.parse("bye world");

        assertEquals(-1, methodType);
    }

    @Test
    public void parse_statementWithExitMiddle_negativeOne() {
        int methodType = Parser.parse("good bye cruel world");

        assertEquals(-1, methodType);
    }

    @Test
    public void parse_listCall_One() {
        int methodType = Parser.parse("list");

        assertEquals(1, methodType);
    }

    @Test
    public void parse_instantiateNewTask_fiveFiveFive() {
        int methodTypeOne = Parser.parse("todo hello world");
        int methodTypeTwo = Parser.parse("deadline see you /by 08/12/2023 0800");
        int methodTypeThree = Parser.parse("event bye world /from 08/12/2023 0900 /to 0900");

        String actualOutput = String.valueOf(methodTypeOne) + String.valueOf(methodTypeTwo) + String.valueOf(methodTypeThree);

        assertEquals("555", actualOutput);
    }

    @Test
    public void getTargetIndex_excessiveArguments_throwExcessiveArgumentException() {
        assertThrows(ExcessiveArgumentException.class, () -> Parser.getTargetIndex("mark 1 remove"));
    }

    @Test
    public void getTargetIndex_stringArgumentIndex_throwNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> Parser.getTargetIndex("mark 1t"));
    }

    @Test
    public void getTargetIndex_noArgumentIndex_throwNoDescriptionException() {
        assertThrows(NoDescriptionException.class, () -> Parser.getTargetIndex("mark  "));
    }

    @Test
    public void getTargetIndex_invalidArgumentIndex_throwIncorrectInputException() {
        assertThrows(IncorrectInputException.class, () -> Parser.getTargetIndex("mark -1"));
    }

    @Test
    public void getTargetIndex_correctArgumentIndex_correctIndex() {
        int index = Parser.getTargetIndex("mark 4");
        // argument index is offset by +1 from actual index of task for user-friendliness
        assertEquals(3, index);
    }

    @Test
    public void getTask_todoTaskArgument_todoTask() {
        Task task = Parser.getTask("todo test mission");

        //We test if they are equal if they return the same storage form
        assertEquals(new ToDo("test mission").convertToStorageForm(), task.convertToStorageForm());
    }

    @Test
    public void getTask_deadlineTaskArgument_deadlineTask() {
        final String ARG = "deadline test mission /by 08/08/2023 0800";
        final Task ACTUAL_TASK = Parser.getTask(ARG);

        //We test if they are equal if they return the same storage form
        assertEquals(new Deadline("test mission", "08/08/2023 0800").convertToStorageForm(), ACTUAL_TASK.convertToStorageForm());
    }

    @Test
    public void getTask_eventTaskArgument_eventTask() {
        final String ARG = "event test mission /from 08/08/2023 0800 /to 0900";
        final Task ACTUAL_TASK = Parser.getTask(ARG);

        //We test if they are equal if they return the same storage form
        assertEquals(new Event("test mission", "08/08/2023 0800", "0900").convertToStorageForm(), ACTUAL_TASK.convertToStorageForm());
    }
}
