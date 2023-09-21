package duke.data.task.tasklist;

import duke.data.task.Task;
import duke.data.task.builder.TaskBuilder;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

public class TasklistTest {

    private Tasklist init() throws Exception {
        Tasklist tasklist = new Tasklist();
        TaskBuilder taskBuilder = new TaskBuilder();
        Task task1 = taskBuilder.buildFromString("todo read book");
        Task task2 = taskBuilder.buildFromString("deadline return book /by 2020-02-02");
        Task task3 = taskBuilder.buildFromString("event project meeting /from 2020-02-02 12:00 /to 2020-02-02 13:00");
        tasklist.addTask(task1);
        tasklist.addTask(task2);
        tasklist.addTask(task3);
        assert tasklist.getTaskCount() == 3;
        assert tasklist.getTask(1) == task1;
        return tasklist;
    }

    @Test
    public void toStringAndGetTaskRepresentationTest() {
        Tasklist tasklist;
        try {
            tasklist = init();
        } catch (Exception e) {
            assert false;
            return;
        }
        String temp = tasklist.toString();
        String expected = "1. [T][ ] read book.  (tags: todo)\n" +
                "2. [D][ ] return book.  (tags: deadline) (by: Feb 02 2020)\n" +
                "3. [E][ ] project meeting.  (tags: event) (from: 2020-02-02 12:00 to: 2020-02-02 13:00)\n";
        assert tasklist.toString().equals(expected);
        expected = "todo read book/isMarked false/tags todo\n" +
                "deadline return book /by 2020-02-02/isMarked false/tags deadline\n" +
                "event project meeting /from 2020-02-02 12:00 /to 2020-02-02 13:00/isMarked false/tags event\n";
        assert tasklist.getTaskRepresentations().equals(expected);
    }

    @Test
    public void markAndTagTest() {
        try {
            Tasklist tasklist = init();
            Task task1 = tasklist.getTask(1);
            assert task1.toString().equals("[T][ ] read book.  (tags: todo)");
            tasklist.mark(1);
            assert task1.toString().equals("[T][X] read book.  (tags: todo)");
            tasklist.unmark(1);
            assert task1.toString().equals("[T][ ] read book.  (tags: todo)");
            tasklist.addTagToTaskAtIndex(1, "newTag");
            assert task1.toString().contains("newTag");
            tasklist.removeTagFromTaskAtIndex(1, "todo");
            assert !task1.toString().contains("todo");
        } catch (Exception e) {
            assert false;
            return;
        }
    }

    @Test
    public void findAndFindTagTest() {
        try {
            Tasklist tasklist = init();
            Tasklist testTasklist = tasklist.findTasksWithKeyword("read");
            assert testTasklist.getTaskCount() == 1;
            testTasklist = tasklist.findTasksWithKeyword("book");
            assert testTasklist.getTaskCount() == 2;
            testTasklist = tasklist.findTasksWithTag("deadline");
            assert testTasklist.getTaskCount() == 1;
            assert testTasklist.getTask(1) == tasklist.getTask(2);
        } catch (Exception e) {
            assert false;
            return;
        }
    }
}




