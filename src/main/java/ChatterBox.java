import java.util.*;

public class ChatterBox {

    public static void main(String[] args) throws DukeException {

        ArrayList<Task> taskList = new ArrayList<>();
        linePrinter();
        tabPrinter("Hello! I'm ChatterBox");
        tabPrinter("What can I do for you?");
        System.out.println("");
        tabPrinter("Available commands:");
        tabPrinter("todo <TASK>"); 
        tabPrinter("deadline <TASK> /by <DATE>");
        tabPrinter("event <TASK> /from <START> /to <END>");
        linePrinter();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String fullLine = sc.nextLine();
            String[] inputLine = fullLine.split(" ");
            String input = inputLine[0];
            if (input.equals("bye")) {
                linePrinter(); 
                tabPrinter("Bye. Hope to see you again soon!");
                linePrinter();
                break;

            } else if (input.equals("list")) {
                linePrinter();
                tabPrinter("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    tabPrinter(String.format("%d. %s", i + 1, 
                                taskList.get(i).toString()));
                } 
                linePrinter();

            } else if (input.equals("mark")) {
                int index = Integer.parseInt(inputLine[1]);
                taskList.get(index - 1).mark();
                linePrinter();
                tabPrinter("Nice! I've marked this task as done:");
                tabPrinter(taskList.get(index - 1).toString());
                linePrinter();

            } else if (input.equals("unmark")) {
                int index = Integer.parseInt(inputLine[1]);
                taskList.get(index - 1).unmark();
                linePrinter();
                tabPrinter("OK, I've marked this task as not done yet:");
                tabPrinter(taskList.get(index - 1).toString());
                linePrinter();
            
            } else if (input.equals("delete")) {
                int index = Integer.parseInt(inputLine[1]);
                Task tempDelete = taskList.get(index - 1);
                taskList.remove(index - 1);
                linePrinter();
                tabPrinter("Noted. I've removed this task:");
                tabPrinter(tempDelete.toString());
                linePrinter();

            } else {

                if (input.equals("todo")) {
                    if (fullLine.split("todo ").length < 1) {
                        linePrinter();
                        tabPrinter("The description of a todo cannot be empty!");
                        linePrinter();
                        throw new DukeException(
                                "The description of a todo cannot be empty!");
                    }
                    String taskName = fullLine.split("todo ")[1];
                    ToDo tempToDo = new ToDo(taskName);
                    taskList.add(tempToDo);
                    linePrinter();
                    tabPrinter("Got it. I've added this task:");
                    tabPrinter(" " + tempToDo.toString());
                    sizePrinter(taskList);
                    linePrinter();


                } else if (input.equals("deadline")) {
                    if (fullLine.split("/by ").length < 1) {
                        linePrinter();
                        tabPrinter("The due date of a deadline cannot be empty!");
                        linePrinter();
                        throw new DukeException(
                                "The due date of a deadline cannot be empty!");
                    }
                    String longName = fullLine.split("/by ")[0];
                    String date = fullLine.split("/by ")[1];
                    String taskName = longName.split("deadline ")[1];
                    String deadlineName = taskName + 
                        String.format("(by: %s)", date);
                    Deadline tempDeadline = new Deadline(deadlineName);
                    taskList.add(tempDeadline);
                    linePrinter();
                    tabPrinter("Got it. I've added this task:");
                    tabPrinter(" " + tempDeadline.toString());
                    sizePrinter(taskList);
                    linePrinter();

                } else if (input.equals("event")) {
                    if (fullLine.split("/").length < 3) {
                        linePrinter();
                        tabPrinter("An event must have both start and end date");
                        linePrinter();
                        throw new DukeException(
                                "An event must have both start and end date");
                    }
                    String[] longNameArray = fullLine.split("/");
                    String longName = longNameArray[0];
                    String fromTime = longNameArray[1];
                    String endTime = longNameArray[2];
                    String taskName = longName.split("event ")[1];
                    String eventName = taskName + 
                        String.format("(from: %s to: %s)",
                                fromTime, endTime);
                    Event tempEvent = new Event(eventName);
                    taskList.add(tempEvent);
                    
                    linePrinter();
                    tabPrinter("Got it. I've added this task:");
                    tabPrinter(" " + tempEvent.toString());
                    sizePrinter(taskList);
                    linePrinter();

                } else {
                    //linePrinter();
                    //tabPrinter("added: " + fullLine);
                    //linePrinter();
                    //taskList.add(new Task(fullLine));  
                    linePrinter();
                    throw new 
                        DukeException("I'm sorry I don't know what that means.");
                }
            }
        }
    }

        private static void linePrinter() {
            tabPrinter
                ("___________________________________________________________");
            System.out.println(" ");
        }

        private static void tabPrinter(String s) {
            System.out.println("      " + s);
        }

        private static void sizePrinter(ArrayList<Task> tasks) {
            tabPrinter(
                    String.format("Now you have %d tasks in the list.", 
                        tasks.size()));
        }
    }
