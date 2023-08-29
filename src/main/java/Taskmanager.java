import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Taskmanager {
    int counter = 0;
    //private Task[] tasks = new Task[100];
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected Save storage;


    public void manageTasks() {
        Scanner sn = new Scanner(System.in);
        String input = sn.nextLine();
        String[] splt = input.split(" ");
        String keyword = splt[0];
        while (!keyword.equals("bye")) {
            switch (keyword) {
                case "list":
                    list();
                    break;
                case "delete":
                    removeTask(Integer.parseInt(splt[1]));
                    break;
                case "mark":
                    mark(Integer.parseInt(splt[1]));
                    break;
                case "unmark":
                    unmark(Integer.parseInt(splt[1]));
                    break;
                default:
                    try {
                        switch (keyword) {
                            case "todo": {
                                String description = "";
                                for (int i = 1; i < splt.length; ++i) {
                                    description = description + splt[i] + " ";
                                }
                                if (description.isEmpty()) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
                                }
                                addTask(new ToDos(description));
                                break;
                            }
                            case "deadline": {
                                int i = 1;
                                if (i == splt.length) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
                                }
                                String temp = splt[i];
                                String description = "";
                                while (!temp.equals("/by")) {
                                    description = description + temp + " ";
                                    i += 1;
                                    temp = splt[i];
                                }
                                i += 1;
                                if (description.isEmpty()) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
                                }
                                String time = "";
                                while (i < splt.length) {
                                    time = time + splt[i] + " ";
                                    i += 1;
                                }
                                if (time.isEmpty()) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The time/date of a deadline cannot be empty.");
                                }
                                addTask(new Deadlines(description, time));
                                break;
                            }
                            case "event": {
                                int i = 1;
                                if (i == splt.length) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
                                }
                                String temp = splt[i];
                                String description = "";
                                while (!temp.equals("/from")) {
                                    description = description + temp + " ";
                                    i += 1;
                                    temp = splt[i];
                                }
                                i += 1;
                                if (description.isEmpty()) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
                                }
                                String start = "";
                                if (i == splt.length) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The starting time of an event cannot be empty.");
                                }
                                temp = splt[i];
                                while (!temp.equals("/to")) {
                                    start = start + temp + " ";
                                    i += 1;
                                    temp = splt[i];
                                }
                                i += 1;
                                if (start.isEmpty()) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The starting time of an event cannot be empty.");
                                }
                                String end = "";
                                while (i < splt.length) {
                                    end = end + splt[i] + " ";
                                    i += 1;
                                }
                                if (end.isEmpty()) {
                                    throw new IllegalArgumentException("☹ OOPS!!! The ending time of an event cannot be empty.");
                                }
                                addTask(new Events(description, start, end));
                                break;
                            }
                            default:
                                throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    } catch (IllegalArgumentException | DateTimeParseException e) {
                        System.out.println(e.getMessage() + "\nTry again: ");
                    }
            }
            input = sn.nextLine();
            splt = input.split(" ");
            keyword = splt[0];
        }
    }

    public void mark(int i) { //need handling
        tasks.get(i-1).setDone();
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(i-1).toString());
    }

    public void unmark(int i) { //need handling
        tasks.get(i-1).setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(i-1).toString());
    }

    public void list() {
        if (counter == 0) {
            System.out.println("There is no task in your list yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= counter; i++) {
                System.out.println(i + "." + tasks.get(i - 1).toString());
            }
        }
    }

    public void addTask(Task t) {
        tasks.add(t);
        counter += 1;
        System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + counter + " tasks in the list.");
        saveTask(t);
        //System.out.println("--------The following task has been successfully saved!--------");
        //readTask(t.getSavingFormat()); //testing reading task
    }

    public void removeTask(int index) {
        String temp = tasks.get(index-1).toString();
        tasks.remove(index-1);
        counter -= 1;
        System.out.println("Noted. I've removed this task:\n" + temp + "\nNow you have " + counter + " tasks in the list.");
    }

    public void saveTask(Task t) {
        if (this.storage == null) {
            this.storage = new Save();
        }
        storage.write(t.getSavingFormat());
    }

    public void readTask(String key) {
        if (this.storage == null) {
            System.out.println("There is no previously saved task!");
        } else {
            System.out.println(storage.read(key));
        }
    }
}
