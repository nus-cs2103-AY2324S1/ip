import java.util.ArrayList;
import java.util.Scanner;
import java.lang.NumberFormatException;
public class Duke {
    private int numofList;
    private ArrayList<Task> list = new ArrayList<Task>();
    private Storage storage;
    private void displayList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < numofList; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println("____________________________________________________________");
    }
    private void delete(Integer index) throws DukeException{
        if (index <= 0 || index > numofList) {
            throw new TaskNotFoundException("Task Not Found :'(");
        }
        Task storeTaskTemp = list.get(index - 1);
        list.remove(index - 1);
        numofList--;
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + storeTaskTemp);
        System.out.println("Now you have " + numofList + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    private void addList(String Input) throws DukeException {
        int index= Input.indexOf(" ");
        String taskDescription = Input.substring(index + 1);
        if (index == -1) {
            if (taskDescription.equalsIgnoreCase("todo")) {
                throw new InvalidInputException("The description of a todo cannot be empty.");
            } else if (taskDescription.equalsIgnoreCase("deadline")) {
                throw new InvalidInputException("The description of a deadline cannot be empty.");
            } else if (taskDescription.equalsIgnoreCase("event")) {
                throw new InvalidInputException("The description of an event cannot be empty.");
            } else {
                throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
            }
        }
        String type = Input.substring(0, index);
        if (type.equalsIgnoreCase("todo")) {
            list.add(new Todo(taskDescription));

        } else if (type.equalsIgnoreCase("deadline")) {
            int byIndex = taskDescription.indexOf("/by");
            if (byIndex == -1) {
                throw new InvalidInputException("Deadline must contain /by");
            }
            list.add(new Deadline(taskDescription));
        } else if (type.equalsIgnoreCase("event")) {
            int fromIndex = taskDescription.indexOf("/from");
            if (fromIndex == -1) {
                throw new InvalidInputException("Event must contain /from");
            }
            int toIndex = taskDescription.substring(fromIndex).indexOf("/to");
            if (toIndex == -1) {
                throw new InvalidInputException("Event must contain /to");
            }
            list.add(new Event(taskDescription));
        } else {
            throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
        }
        numofList++;
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + list.get(numofList - 1));
        System.out.println(" Now you have " + numofList + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    private void run() {
        try {
            this.storage = new Storage();
            this.list = this.storage.getTaskList();
            this.numofList = this.list.size();
            Scanner scanner = new Scanner(System.in);
            String Input = scanner.nextLine();
            while (!Input.equalsIgnoreCase("bye")) {
                try {
                    String[] splittedInput = Input.split(" ");
                    if (Input.equals("list")) {
                        displayList();
                    } else if (splittedInput[0].equalsIgnoreCase("mark") && splittedInput.length == 2 &&
                            isInteger((splittedInput[1]))) {
                        if (Integer.parseInt(splittedInput[1]) <= 0 || Integer.parseInt(splittedInput[1]) > numofList) {
                            throw new TaskNotFoundException("Task Not Found :'(");
                        }
                        list.get(Integer.parseInt(splittedInput[1]) - 1).mark();
                    } else if (splittedInput[0].equalsIgnoreCase("unmark") && splittedInput.length == 2 &&
                            isInteger((splittedInput[1]))) {
                        if (Integer.parseInt(splittedInput[1]) <= 0 || Integer.parseInt(splittedInput[1]) > numofList) {
                            throw new TaskNotFoundException("Task Not Found :'(");
                        }
                        list.get(Integer.parseInt(splittedInput[1]) - 1).unmark();
                    } else if (splittedInput[0].equalsIgnoreCase("delete") && splittedInput.length == 2 &&
                            isInteger((splittedInput[1]))) {
                        delete(Integer.parseInt(splittedInput[1]));
                    } else {
                        addList(Input);
                    }
                    this.storage.writeFile(this.list);
                } catch (DukeException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" ☹ OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }

                Input = scanner.nextLine();
            }
        }
        catch (DukeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" ☹ OOPS!!! " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }
    private boolean isInteger(String str) {
        try {
            Integer check = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String Introduction = "____________________________________________________________\n" +
                        " Hello! I'm FootyCouch\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n";
        String Exit =   "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
        System.out.printf(Introduction);
        duke.run();
        System.out.printf(Exit);
    }
}
