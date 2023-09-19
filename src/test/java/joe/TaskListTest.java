package joe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import joe.stubs.TaskStub;
import joe.tasks.Task;

public class TaskListTest {
    @Test
    public void add_expectedUsage_success() {
        TaskList tasks = new TaskList();
        TaskStub taskStub = new TaskStub();
        tasks.add(taskStub);
        assertEquals(taskStub, tasks.get(0));
    }

    @Test
    public void get_negativeIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        TaskStub taskStub = new TaskStub();
        tasks.add(taskStub);
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.get(-1));
    }

    @Test
    public void get_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        TaskStub taskStub = new TaskStub();
        tasks.add(taskStub);
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.get(1));
    }

    @Test
    public void remove_negativeIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        TaskStub taskStub = new TaskStub();
        tasks.add(taskStub);
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.remove(-1));
    }

    @Test
    public void remove_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        TaskStub taskStub = new TaskStub();
        tasks.add(taskStub);
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.remove(1));
    }

    @Test
    public void remove_afterAddTask_success() {
        TaskList tasks = new TaskList();
        Task oldTaskStub = new TaskStub();
        tasks.add(oldTaskStub);
        tasks.remove(0);
        assertEquals(0, tasks.size());
        TaskStub newTaskStub = new TaskStub();
        tasks.add(newTaskStub);
        assertEquals(newTaskStub, tasks.get(0));
        assertNotEquals(oldTaskStub, tasks.get(0));
    }

    @Test
    public void size_emptyList_zero() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    public void size_afterAddingTwoTasks_two() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        tasks.add(new TaskStub());
        assertEquals(2, tasks.size());
    }

    @Test
    public void size_addAndRemoveTask_zero() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        tasks.remove(0);
        assertEquals(0, tasks.size());
    }

    @Test
    public void toString_noTasks_noResult() {
        assertEquals("No tasks available.", new TaskList().toString());
    }

    @Test
    public void toString_multipleTasks_success() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        tasks.add(new TaskStub());
        tasks.add(new TaskStub());
        assertEquals("1. toString\n"
                + "2. toString\n"
                + "3. toString", tasks.toString());
    }

    @Test
    public void findByDesc_wordExists_success() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findByDesc("desc", false);
        assertEquals("1. toString", res.toString());
    }

    @Test
    public void findByDesc_wordDoesNotExist_noResult() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findByDesc("asdadsda", false);
        assertEquals("No tasks available.", res.toString());
    }

    @Test
    public void findByDesc_emptySearchString_allTasks() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findByDesc("", false);
        assertEquals("1. toString", res.toString());
    }

    @Test
    public void findByDesc_randomCase_success() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findByDesc("DeSCrIption", false);
        assertEquals("1. toString", res.toString());
    }

    @Test
    public void findByDesc_searchToString_noResult() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findByDesc("toString", false);
        assertEquals("No tasks available.", res.toString());
    }

    @Test
    public void findByDesc_matchingCase_success() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findByDesc("Description", true);
        assertEquals("1. toString", res.toString());
    }

    @Test
    public void findByDesc_matchingCase_noResult() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findByDesc("description", true);
        assertEquals("No tasks available.", res.toString());
    }

    @Test
    public void findAll_toString_success() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findAll("toString");
        assertEquals("1. toString", res.toString());
    }

    @Test
    public void findAll_toStringLowerCase_success() {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        TaskList res = tasks.findAll("tostring");
        assertEquals("1. toString", res.toString());
    }
}
