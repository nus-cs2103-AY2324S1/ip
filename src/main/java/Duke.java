import java.util.*;

public class Duke {
    private static ArrayList<Task> lst = new ArrayList<>();

    private static String stringFormat(String[] strArray) {
        String content = "";
        for (String s : strArray) {
            content += "\t " + s + "\n";
        }
        return   "\t_______________________________________________\n"
                + content
                + "\t_______________________________________________";
    }

    private static String greet() {
        return stringFormat(new String[]{"Hi there! I'm Bob", "How can I help?"});
    }

    private static String exit() {
        return stringFormat(new String[]{"See you soon!"});
    }

    private static String echo(String input) {
        return stringFormat(new String[]{input});
    }

    private static boolean isValidTaskType(TaskType type) {
        return type.equals(TaskType.todo) || type.equals(TaskType.deadline) || type.equals(TaskType.event);
    }

    private static String addToList(String description) {
        Task taskObj;
        try {
            // Split by the first " " into type, and task details
            String[] task = description.split(" ", 2);
            TaskType taskType;
            try {
                taskType = Enum.valueOf(TaskType.class, task[0]);
            } catch (Exception e) {
                throw new WrongInputException();
            }

            if (!isValidTaskType(taskType)) {
                throw new WrongInputException();
            }

            if (task.length == 1) {
                throw new MissingTaskException();
            }

            String taskDetails = task[1];

            if (taskType.equals(TaskType.deadline)) {
                taskObj = new Deadline(taskDetails);
            } else if (taskType.equals(TaskType.event)) {
                taskObj = new Event(taskDetails);
            } else {
                taskObj = new Todo(taskDetails);
            }

            lst.add(taskObj);
            return stringFormat(new String[]{"New task added: ", "\t" + taskObj.toString(),
                    "You now have " + lst.size() + (lst.size() == 1 ? " task!" : " tasks!")});

        } catch (WrongInputException e) {
            return e.message;
        } catch (MissingTaskException e) {
            return e.message;
        } catch (MissingDeadlineException e) {
            return e.message;
        } catch (MissingEventDatesException e) {
            return e.message;
        }
    }

    private static String displayList() {
        String[] tasks = new String[lst.size() + 1];
        if (lst.isEmpty()) {
            tasks[0] = "You currently have no tasks.";
        } else {
            tasks[0] = "Here are your tasks!";
        }

        for (int i = 0; i < lst.size(); i++) {
            tasks[i + 1] = (i + 1) + ". " + lst.get(i).toString();
        }
        return stringFormat(tasks);
    }

    private static String markDoneOrNot(int index, boolean doneOrNot) {
        lst.get(index - 1).SetDoneOrNot(doneOrNot);
        String statement = doneOrNot ? "Nice! You completed a task!" : "... This is now undone.";
        return stringFormat(new String[]{statement, "\t" + lst.get(index - 1).toString()});
    }

    private static String deleteTask(int index) {
        String taskStr = lst.get(index - 1).toString();
        lst.remove(index - 1);

        return stringFormat(new String[]{"I've removed this task from list: ", "\t" + taskStr,
                "You now have " + lst.size() + (lst.size() == 1 ? " task!" : " tasks!")});
    }

    public static void main(String[] args) {
        System.out.println(greet());

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String nextLine = sc.nextLine();

                if (nextLine.equals("list")) {
                    System.out.println(displayList());

                } else if (nextLine.contains("mark")) { // if command is to mark or unmark
                    String[] markIndex = nextLine.split(" ");
                    if (markIndex.length == 1) {
                        throw new MissingIndexException();
                    }
                    int index = 0;
                    try {
                        index = Integer.parseInt(markIndex[1]);
                    } catch (NumberFormatException e) {
                        throw new MissingIndexException();
                    }
                    boolean doneOrNot = true;
                    if (nextLine.contains("unmark")) {
                        doneOrNot = false;
                    }
                    System.out.println(markDoneOrNot(index, doneOrNot));

                } else if (nextLine.contains("delete")) {
                    String[] deleteIndex = nextLine.split(" ");
                    if (deleteIndex.length == 1) {
                        throw new MissingIndexException();
                    }
                    int index = 0;
                    try {
                        index = Integer.parseInt(deleteIndex[1]);
                    } catch (NumberFormatException e) {
                        throw new MissingIndexException();
                    }
                    System.out.println(deleteTask(index));
                } else if (nextLine.equals("bye")) {
                    System.out.println(exit());
                    sc.close();
                    break;

                } else { // if command is to add tasks
                    if (nextLine.isEmpty()) {
                        throw new NoSuchElementException();
                    }
                    System.out.println(addToList(nextLine));
                }
            }
            catch (NoSuchElementException e) {
                System.out.println("Write something!");
            } catch (MissingIndexException e) {
                System.out.println(e.message);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index provided is wrong!");
            }
        }
    }
}
