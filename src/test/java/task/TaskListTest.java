package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void add() {
        TaskList tasks = new TaskList();
        tasks.todo("Read book");
        tasks.event("Career Fair", "30/8", "31/8");
        tasks.deadline("Homework", "15/09/2023");
        assertEquals("1. [T][ ] Read book\n2. [E][ ] Career Fair (from: 30/8 to: 31/8)\n3. [D][ ] Homework (by: 15 Sep 2023 00:00)", tasks.list());
    }

    @Test
    public void mark() {
        TaskList tasks = new TaskList();
        tasks.todo("Read book");
        tasks.event("Career Fair", "30/8", "31/8");
        tasks.deadline("Homework", "15/09/2023");
        tasks.mark(1);
        tasks.mark(3);
        tasks.unmark(1);
        assertEquals("1. [T][ ] Read book\n2. [E][ ] Career Fair (from: 30/8 to: 31/8)\n3. [D][X] Homework (by: 15 Sep 2023 00:00)", tasks.list());
    }

    @Test
    public void delete() {
        TaskList tasks = new TaskList();
        tasks.todo("Read book");
        tasks.event("Career Fair", "30/8", "31/8");
        tasks.deadline("Homework", "15/09/2023");
        tasks.mark(1);
        tasks.mark(3);
        tasks.delete(1);
        assertEquals("1. [E][ ] Career Fair (from: 30/8 to: 31/8)\n2. [D][X] Homework (by: 15 Sep 2023 00:00)", tasks.list());
    }

    @Test
    public void dueBy() {
        TaskList tasks = new TaskList();
        tasks.todo("Read book");
        tasks.event("Career Fair", "30/8", "31/8");
        tasks.deadline("Homework", "15/07/2023");
        tasks.deadline("Homework 2", "13/07/2023");
        tasks.deadline("Homework 3", "17/07/2023");
        tasks.mark(1);
        tasks.mark(3);
        tasks.delete(1);
        assertEquals("1. [D][X] Homework (by: 15 Jul 2023 00:00)\n2. [D][ ] Homework 2 (by: 13 Jul 2023 00:00)", tasks.dueBy("dueby 16/07/2023"));
    }

    @Test
    public void overDue() {
        TaskList tasks = new TaskList();
        tasks.todo("Read book");
        tasks.event("Career Fair", "30/8", "31/8");
        tasks.deadline("Homework", "15/07/2023");
        tasks.deadline("Homework 2", "13/07/2024"); // 2024
        tasks.deadline("Homework 3", "17/07/2023");
        tasks.mark(1);
        tasks.mark(3);
        tasks.delete(1);
        assertEquals("1. [D][X] Homework (by: 15 Jul 2023 00:00)\n2. [D][ ] Homework 3 (by: 17 Jul 2023 00:00)", tasks.overdue());
    }

}
