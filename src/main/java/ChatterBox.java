import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;


public class ChatterBox {

    public static void main(String[] args) throws DukeException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File("data.txt");

        if (!f.exists()) {
            f.createNewFile();
        }

        Storage.fileToTaskList(f, taskList);


        Ui.linePrinter();
        Ui.tabPrinter("Hello! I'm ChatterBox");
        Ui.tabPrinter("What can I do for you?");
        System.out.println("");
        // Ui.tabPrinter("Available commands:");
        // Ui.tabPrinter("todo <TASK>"); 
        // Ui.tabPrinter("deadline <TASK> /by <DATE>");
        // Ui.tabPrinter("event <TASK> /from <START> /to <END>");
        Ui.linePrinter();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String fullLine = sc.nextLine();
            String[] inputLine = fullLine.split(" ");
            String input = inputLine[0];
            if (input.equals("bye")) {
                Ui.linePrinter(); 
                Ui.tabPrinter("Bye. Hope to see you again soon!");
                Ui.linePrinter();
                break;

            } else if (input.equals("list")) {
                Ui.linePrinter();
                Ui.tabPrinter("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Ui.tabPrinter(String.format("%d. %s", i + 1, 
                                taskList.get(i).toString()));
                } 
                Ui.linePrinter();

            } else if (input.equals("mark")) {
                int index = Integer.parseInt(inputLine[1]);
                taskList.get(index - 1).mark();
                Storage.taskListToFile(f, taskList);
                Ui.linePrinter();
                Ui.tabPrinter("Nice! I've marked this task as done:");
                Ui.tabPrinter(taskList.get(index - 1).toString());
                Ui.linePrinter();

            } else if (input.equals("unmark")) {
                int index = Integer.parseInt(inputLine[1]);
                taskList.get(index - 1).unmark();
                Storage.taskListToFile(f, taskList);
                Ui.linePrinter();
                Ui.tabPrinter("OK, I've marked this task as not done yet:");
                Ui.tabPrinter(taskList.get(index - 1).toString());
                Ui.linePrinter();
            
            } else if (input.equals("delete")) {
                int index = Integer.parseInt(inputLine[1]);
                Task tempDelete = taskList.get(index - 1);
                taskList.remove(index - 1);
                Storage.taskListToFile(f, taskList);
                Ui.linePrinter();
                Ui.tabPrinter("Noted. I've removed this task:");
                Ui.tabPrinter(tempDelete.toString());
                Ui.linePrinter();

            } else {

                if (input.equals("todo")) {
                    if (fullLine.split("todo ").length < 1) {
                        Ui.linePrinter();
                        Ui.tabPrinter("The description of a todo cannot be empty!");
                        Ui.linePrinter();
                        throw new DukeException(
                                "The description of a todo cannot be empty!");
                    }
                    String taskName = fullLine.split("todo ")[1];
                    ToDo tempToDo = new ToDo(taskName);
                    taskList.add(tempToDo);
                    Storage.taskListToFile(f, taskList);
                    Ui.linePrinter();
                    Ui.tabPrinter("Got it. I've added this task:");
                    Ui.tabPrinter(" " + tempToDo.toString());
                    sizePrinter(taskList);
                    Ui.linePrinter();


                } else if (input.equals("deadline")) {
                    if (fullLine.split("/by ").length < 1) {
                        Ui.linePrinter();
                        Ui.tabPrinter("The due date of a deadline cannot be empty!");
                        Ui.linePrinter();
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
                    Storage.taskListToFile(f, taskList);
                    Ui.linePrinter();
                    Ui.tabPrinter("Got it. I've added this task:");
                    Ui.tabPrinter(" " + tempDeadline.toString());
                    sizePrinter(taskList);
                    Ui.linePrinter();

                } else if (input.equals("event")) {
                    if (fullLine.split("/").length < 3) {
                        Ui.linePrinter();
                        Ui.tabPrinter("An event must have both start and end date");
                        Ui.linePrinter();
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
                    Storage.taskListToFile(f, taskList);
                    
                    Ui.linePrinter();
                    Ui.tabPrinter("Got it. I've added this task:");
                    Ui.tabPrinter(" " + tempEvent.toString());
                    sizePrinter(taskList);
                    Ui.linePrinter();

                } else {
                    //Ui.linePrinter();
                    //Ui.tabPrinter("added: " + fullLine);
                    //Ui.linePrinter();
                    //taskList.add(new Task(fullLine));  
                    Ui.linePrinter();
                    throw new 
                        DukeException("I'm sorry I don't know what that means.");
                }
            }
        }
    }


        private static void sizePrinter(ArrayList<Task> tasks) {
            Ui.tabPrinter(
                    String.format("Now you have %d tasks in the list.", 
                        tasks.size()));
        }
    }
