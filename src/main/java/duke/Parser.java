package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import tasks.Task;

public class Parser {
    public String parse(TaskList tasks, String echo) {
        String message = "";

        while (!echo.equals("bye")) {
            try {
                if (!echo.equals("list")) {
                    String[] parts = echo.split(" ");
                    if (parts[0].equals("mark")) {
                        if (parts.length == 1) {
                            throw new DukeException("AAA...AGHHH!!! The number to be marked done... " +
                                    "c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        int taskNum = Integer.parseInt(parts[1]);
                        tasks.markDone(taskNum - 1);
                        message = Ui.line() + "O...Omedeto! I have... have marked this as done!!ヾ(*´▽‘*)ﾉ\n"
                                + tasks.printTask(taskNum - 1) + "\n" + Ui.line();
                        return message;
                    } else if (parts[0].equals("unmark")) {
                        if (parts.length == 1) {
                            throw new DukeException("AAA...AGHHH!!! The number to be unmarked done... " +
                                    "c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        int taskNum = Integer.parseInt(parts[1]);
                        tasks.markUndone(taskNum - 1);
                        message = Ui.line() + "Okk... this is not done yet ﾍ(;´Д｀ﾍ)" + tasks.printTask(taskNum - 1)
                                + "\n" + Ui.line();
                        assert tasks.printTask(taskNum - 1) != null;
                        return message;
                    } else if (parts[0].equals("deadline")) {
                        String removeDdl = echo.replace("deadline", "");
                        if (removeDdl.equals("")) {
                            throw new DukeException("AAA...AGHHH!!! The description of a deadline... " +
                                    "c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        String removeBy = removeDdl.replace("by", "");
                        String[] ddl = removeBy.split("/");
                        String[] split = ddl[1].split(" ");

                        try {
                            LocalDate by = LocalDate.parse(split[1]);
                            Deadline x = new Deadline(ddl[0], by);
                            tasks.addToList(x);
                            message = Ui.line() + "Okk... I've... I've added this task:\n" + x.toString() + "\n"
                                    + tasks.numOfTask() + Ui.line();
                            assert tasks.numOfTask() != null;
                            return message;
                        } catch (DateTimeException e) {
                            throw new DukeException("Th...th...the ddate you have inputed is invalid!!");
                        }
                    } else if (parts[0].equals("event")) {
                        String removeEvent = echo.replace("event", "");
                        if (removeEvent.equals("")) {
                            throw new DukeException("AAA...AGHHH!!! The description of an event... " +
                                    "c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        String removeFrom = removeEvent.replace("from", "");
                        String removeTo = removeFrom.replace("to", "");
                        String[] event = removeTo.split("/");
                        Event x = new Event(event[0], event[1], event[2]);
                        tasks.addToList(x);
                        message = Ui.line() + "Okk... I've... I've added this task:\n" + x.toString() + "\n"
                                + tasks.numOfTask() + Ui.line();
                        assert Ui.line() != null;
                        return message;
                    } else if (parts[0].equals("todo")) {
                        String removeTodo = echo.replace("todo", "");
                        if (removeTodo.equals("")) {
                            throw new DukeException("AAA...AGHHH!!! The description of the todo... " +
                                    "c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        ToDo x = new ToDo(removeTodo);
                        tasks.addToList(x);
                        message = Ui.line() + "Okk... I've... I've added this task:\n" + x.toString()
                                + "\n" + tasks.numOfTask() + Ui.line();
                        return message;
                    } else if (parts[0].equals("delete")) {
                        if (parts.length == 1) {
                            throw new DukeException("AAA...AGHHH!!! The number to be deleted... " +
                                    "c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        if (tasks.totalTaskNum() == 0) {
                            throw new DukeException("AAA...AGHHH!!! The list... " +
                                    "is already empty!!!°(°ˊДˋ°) °");
                        }
                        if (Integer.parseInt(parts[1]) > tasks.totalTaskNum()) {
                            throw new DukeException("AAA...AGHHH!!! You do not have so many tasks... " +
                                    "in the list!!!°(°ˊДˋ°) °");
                        }
                        int taskNum = Integer.parseInt(parts[1]);
                        tasks.deleteTask(taskNum - 1);
                        message = Ui.line() + "O...Okk... I've re...removed this task:\n" + tasks.numOfTask()
                                + Ui.line();
                        assert tasks.numOfTask() != null;
                        return message;
                    } else if (parts[0].equals("find")) {
                        if (parts.length == 1) {
                            throw new DukeException("AAA...AGHHH!!! The task to find... " +
                                    "c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        String keyword = parts[1];
                        TaskList store = new TaskList(new ArrayList<Task>());
                        for (int i = 0; i < tasks.totalTaskNum(); i++) {
                            String temp = tasks.printTask(i);
                            List<String> words = Arrays.asList(temp.split(" "));
                            if (words.contains(keyword)) {
                                store.addToList(tasks.tasks.get(i));
                            }
                        }
                        message = Ui.line() + "Here are the matching tasks in your list:\n" + store.printList() + Ui.line();
                        return message;
                    } else {
                        throw new DukeException("AAA...AGHHH!!! Go...Gomenasaiii!!! I don't understand!!!°(°ˊДˋ°) °");
                    }
                } else { //equals list
                    message = Ui.line() + "H...here are the tasks in your list:\n" + tasks.printList() + Ui.line();
                    assert tasks.printList() != null;
                    return message;
                }
            } catch (DukeException e) {
                message = Ui.line() + e.getMessage() + "\n" + Ui.line();
                assert e.getMessage() != null;
                return message;
            }
        }
        message = Ui.line() + Ui.sayBye() + Ui.line();
        assert Ui.sayBye() != null;
        return message;
    }
}
