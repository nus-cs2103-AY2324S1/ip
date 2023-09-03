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
    public void parse(TaskList tasks) {
        Scanner echoObject = new Scanner(System.in);
        String echo = echoObject.nextLine();
        while (!echo.equals("bye")) {
            try {
                if (!echo.equals("list")) {
                    String[] parts = echo.split(" ");
                    if (parts[0].equals("mark")) {
                        if (parts.length == 1) {
                            throw new DukeException("AAA...AGHHH!!! The number to be marked done... c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        int taskNum = Integer.parseInt(parts[1]);
                        tasks.markDone(taskNum - 1);
                        Ui.line();
                        System.out.println("O...Omedeto! I have... have marked this as done!!:DD");
                        System.out.println(tasks.printTask(taskNum - 1));
                        Ui.line();

                    } else if (parts[0].equals("unmark")) {
                        if (parts.length == 1) {
                            throw new DukeException("AAA...AGHHH!!! The number to be unmarked done... c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        int taskNum = Integer.parseInt(parts[1]);
                        Ui.line();
                        System.out.println("Okk... this is not done yet QAQ");
                        tasks.markUndone(taskNum - 1);
                        System.out.println(tasks.printTask(taskNum - 1));
                        Ui.line();
                    } else if (parts[0].equals("deadline")) {
                        String removeDdl = echo.replace("deadline", "");
                        if (removeDdl.equals("")) {
                            throw new DukeException("AAA...AGHHH!!! The description of a deadline... c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        String removeBy = removeDdl.replace("by", "");
                        String[] ddl = removeBy.split("/");
                        String[] split = ddl[1].split(" ");
                        try {
                            LocalDate by = LocalDate.parse(split[1]);
                            Deadline x = new Deadline(ddl[0], by);
                            Ui.line();
                            System.out.println("Okk... I've... I've added this task:");
                            tasks.addToList(x);
                            System.out.println(x.toString());
                            tasks.numOfTask();
                        } catch (DateTimeException e) {
                            throw new DukeException("Th...th...the ddate you have inputed is invalid!!");
                        }
                        Ui.line();
                    } else if (parts[0].equals("event")) {
                        String removeEvent = echo.replace("event", "");
                        if (removeEvent.equals("")) {
                            throw new DukeException("AAA...AGHHH!!! The description of an event... c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        String removeFrom = removeEvent.replace("from", "");
                        String removeTo = removeFrom.replace("to", "");
                        String[] event = removeTo.split("/");
                        Event x = new Event(event[0], event[1], event[2]);
                        Ui.line();
                        System.out.println("Okk... I've... I've added this task:");
                        tasks.addToList(x);
                        System.out.println(x.toString());
                        tasks.numOfTask();
                        Ui.line();
                    } else if (parts[0].equals("todo")) {
                        String removeTodo = echo.replace("todo", "");
                        if (removeTodo.equals("")) {
                            throw new DukeException("AAA...AGHHH!!! The description of the todo... c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        ToDo x = new ToDo(removeTodo);
                        Ui.line();
                        System.out.println("Okk... I've... I've added this task:");
                        tasks.addToList(x);
                        System.out.println(x.toString());
                        tasks.numOfTask();
                        Ui.line();
                    } else if (parts[0].equals("delete")) {
                        if (parts.length == 1) {
                            throw new DukeException("AAA...AGHHH!!! The number to be deleted... c...cannot be empty!!!°(°ˊДˋ°) °");
                        }
                        int taskNum = Integer.parseInt(parts[1]);
                        Ui.line();
                        System.out.println("O...Okk... I've re...removed this task:");
                        tasks.deleteTask(taskNum - 1);
                        tasks.numOfTask();
                        Ui.line();
                    } else if (parts[0].equals("find")) {
                        if (parts.length == 1) {
                            throw new DukeException("AAA...AGHHH!!! The task to find... c...cannot be empty!!!°(°ˊДˋ°) °");
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
                        Ui.line();
                        System.out.println("Here are the matching tasks in your list:");
                        store.printList();
                        Ui.line();
                    } else {
                        throw new DukeException("AAA...AGHHH!!! Go...Gomenasaiii!!! I don't understand!!!°(°ˊДˋ°) °");
                    }
                } else { //equals list
                    Ui.line();
                    System.out.println("H...here are the tasks in your list:");
                    tasks.printList();
                    Ui.line();
                }
            } catch (DukeException e) {
                Ui.line();
                System.out.println(e.getMessage());
                Ui.line();
            }
            Scanner nextEchoObject = new Scanner(System.in);
            echo = nextEchoObject.nextLine();
        }
    }
}
