package tasks;

import org.junit.jupiter.api.Test;

import parser.ParserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The TaskListTest class contains unit tests for the TaskList class.
 * These tests verify the functionality of the TaskList class methods.
 */
public class TaskListTest {

    /**
     * This test method verifies the correctness of the TaskList class by testing the following actions:
     * 1. Creating a TaskList instance.
     * 2. Adding a completed ToDos task with the name "123" to the task list.
     * 3. Retrieving the task at index 0 from the task list.
     * 4. Checking if the added task is marked as done.
     * 5. Verifying that the retrieved task has the expected string representation.
     *
     * Expected Output:
     * - The added task should be marked as done.
     * - The retrieved task's string representation should be "[T] [X] 123".
     */
    @Test
    public void testList() {
        // Arrange
        TaskList tasks = new TaskList();
        Task task = new ToDos("123", true);

        // Act
        tasks.add(task);
        Task retrieveTask = tasks.retrieve(0);

        // Assert
        assert task.checkDone() : "Expected task to be marked as done";
        assert retrieveTask.toString().equals("[T] [X] 123") : "Expected '[T] [X] 123' but got '" + retrieveTask.toString() + " instead!";
    }

    public static void main(String[] args) {
        ParserTest parserTest = new ParserTest();
        parserTest.testList();
    }
}
