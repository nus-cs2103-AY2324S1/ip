package duke.util;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private static TaskList testTaskList;
    private static Storage testStorage;

    @BeforeEach
    public void setUp() {
        // Create a new TaskList object for each test case
        testStorage = new Storage("./data/taskListTest.txt");
        testTaskList = new TaskList(testStorage);
    }

    void populateTestTasks() {
        testTaskList.listOfTasks.add(new Todo("Let's party someday"));
        testTaskList.listOfTasks.add(new Deadline("Complete CS2103T ip", LocalDate.parse("2023-09-12")));
        testTaskList.listOfTasks.add(new Event("Ren's birthday party",
                LocalDate.parse("2023-09-09"), LocalDate.parse("2023-09-10")));
        testTaskList.listOfTasks.add(new Deadline("URGENT Assignment", LocalDate.parse("2023-09-20")));
        testTaskList.listOfTasks.add(new Event("CCA Welcome Tea",
                LocalDate.parse("2023-09-10"), LocalDate.parse("2023-09-11")));
    }

    @Test
    void testListAllTasks_nonEmptyList() {
        populateTestTasks();
        String actualOutput = testTaskList.listAllTasks();
        String expectedOutput = "You have 5 tasks now. Here is your task list:\n" +
                "\t1.[T][ ] Let's party someday\n" +
                "\t2.[D][ ] Complete CS2103T ip (by: Sep 12 2023)\n" +
                "\t3.[E][ ] Ren's birthday party (from: Sep 9 2023 to: Sep 10 2023)\n" +
                "\t4.[D][ ] URGENT Assignment (by: Sep 20 2023)\n" +
                "\t5.[E][ ] CCA Welcome Tea (from: Sep 10 2023 to: Sep 11 2023)\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testListAllTasks_emptyList() {
        String actualOutput = testTaskList.listAllTasks();
        String expectedOutput = "No tasks for now!";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDeleteTask_validIndex() throws EmptyDescriptionException, IOException {
        populateTestTasks();
        String actualOutput = testTaskList.deleteTask("delete 5");
        String expectedOutput = "Noted. I've removed this task:\n" +
                "[E][ ] CCA Welcome Tea (from: Sep 10 2023 to: Sep 11 2023)\n" +
                String.format("Now you have %d task(s) in the list.\n", testTaskList.listOfTasks.size());
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDeleteTask_indexOutOfBound() throws EmptyDescriptionException, IOException {
        populateTestTasks();
        String actualOutput = testTaskList.deleteTask("delete 1234567");
        String expectedOutput = "OOPS!!! The task index is invalid.\n" +
                String.format("You currently have %d task(s).\n", testTaskList.listOfTasks.size());
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDeleteTask_notNumberInput_throwNumberFormatException() throws EmptyDescriptionException, IOException {
        populateTestTasks();
        String actualOutput = testTaskList.deleteTask("delete NaN");
        String expectedOutput = "OOPS!!! Please enter the index after 'delete' command." +
                "For example: delete 5" +
                "This will remove Task 5 from your Task List, assuming you have at least 5 tasks.";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testMarkTask_validIndex() throws IOException {
        populateTestTasks();
        String actualOutput = testTaskList.markTask(0);
        String expectedOutput = "Nice! I've marked this Task as done:\n" +
                "\t[X] Let's party someday\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testMarkTask_invalidIndex() throws IOException {
        populateTestTasks();
        String actualOutput = testTaskList.markTask(-8);
        String expectedOutput = String.format("Invalid Index of Task. You currently have %d Task(s)\n",
                testTaskList.listOfTasks.size());
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testFindTask_matchingKeyword() {
        populateTestTasks();
        String keyword = "party";
        String actualOutput = testTaskList.findTask(keyword);
        String expectedOutput = String.format("Here are your tasks that contains '%s':", keyword) +
                "\n\t [T][ ] Let's party someday" +
                "\n\t [E][ ] Ren's birthday party (from: Sep 9 2023 to: Sep 10 2023)";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testFindTask_noMatchingKeyword() {
        populateTestTasks();
        String keyword = "lalaland";
        String actualOutput = testTaskList.findTask(keyword);
        String expectedOutput = String.format("\t Hm there are no matching tasks with '%s'. "
                + "Try with another keyword.", keyword);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testFindTask_emptyTaskList() {
        String keyword = "Anything";
        String actualOutput = testTaskList.findTask(keyword);
        String expectedOutput = "\t You currently have no tasks so I can't find any matching tasks :(";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testViewSchedule_matchingTasks() throws InvalidDateException {
        populateTestTasks();
        String actualOutput = testTaskList.viewSchedule("view 2023-09-10");
        String expectedOutput = "Here are your scheduled tasks happening on Sep 10 2023 :\n" +
                "\t [E][ ] Ren's birthday party (from: Sep 9 2023 to: Sep 10 2023)\n" +
                "\t [E][ ] CCA Welcome Tea (from: Sep 10 2023 to: Sep 11 2023)";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testViewSchedule_noMatchingTasks() throws InvalidDateException {
        populateTestTasks();
        String actualOutput = testTaskList.viewSchedule("view 2033-09-10");
        String expectedOutput = "There is no tasks happening on Sep 10 2033. " +
                "Please double check your list of tasks.";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testViewSchedule_invalidDate_throwsInvalidDateException() throws InvalidDateException {
        populateTestTasks();
        assertThrows(InvalidDateException.class, () -> {
            testTaskList.viewSchedule("view 1-9-80");
        });
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        testStorage.clearAllData();
    }


}
