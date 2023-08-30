import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {

    String greeting = "Hello! I'm KimochiUsagi (きもち　うさぎ)!\n";
    String info = "Ask the bunny a question!\n";
    String goodbye = "Bye. See you again! (またね)";

    ArrayList<Task> taskList = new ArrayList<>();
    Parser parser = new Parser();
    UiFormatter uiFormatter = new UiFormatter();
    Storage storage = new Storage(taskList);

    storage.loadTasks();
    System.out.println(greeting);
    System.out.println(info);

//    Scanner scanner = new Scanner(System.in);

    label:
    while (true) {

      String inputString = null;
      String[] inputTokens = null;

      try {
//        inputString = scanner.nextLine();
//        inputTokens = inputString.split(" ");
        parser.update();
        inputString = parser.getInputString();
        inputTokens = parser.getInputTokens();
      } catch (NoSuchElementException ex) {
        break;
      }

      // there is no input
      if (inputTokens.length == 0) {
        break;
      }

      String commandString = inputTokens[0];

      switch (commandString) {
        case "bye":
          break label;
        case "list":

          if (taskList.isEmpty()) {
            System.out.println("list is empty!");
            break;
          }

          for (int i = 0; i < taskList.size(); i++) {

            String index = Integer.toString(i + 1);
            Task selectedTask = taskList.get(i);
            System.out.println(index + " " + uiFormatter.displayTask(selectedTask));

          }
          break;
        case "mark": {
          try {
            int index = Integer.parseInt(inputTokens[1]);
            index--;
            // set current task as done
            Task selectedTask = taskList.get(index);
            selectedTask.setDone();
            System.out.println("Marked selected task as done");
            System.out.println(uiFormatter.displayTask(selectedTask));
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid index!");
          }

          break;
        }
        case "unmark": {

          try {
            int index = Integer.parseInt(inputTokens[1]);
            index--;
            // set current task as un-done
            Task selectedTask = taskList.get(index);
            System.out.println("Marked selected task as un-done desu");
            selectedTask.setUnDone();
            System.out.println(uiFormatter.displayTask(selectedTask));
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid index!");
          }

          break;
        }
        case "todo": {
          try {
            int commandLength = commandString.length() + 1;
            String taskName = inputString.substring(commandLength);
            Task curentTask = new Todo(taskName);
            taskList.add(curentTask);

            System.out.println("added:\t" + uiFormatter.displayTask(curentTask));
          } catch (StringIndexOutOfBoundsException ex) {

            System.out.println("Please enter a name after the todo command!");
          }

          break;
        }
        case "deadline": {
          try {
            int commandLength = commandString.length() + 1;
            String taskName = inputString.substring(commandLength);
            String[] parts = taskName.split("/by", 2);

            String name = parts[0];
            String endDate = parts[1];
            endDate = endDate.replace(" ", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(endDate, formatter);

            Task curentTask = new Deadline(name, date);
            taskList.add(curentTask);

            System.out.println("added:\t" + uiFormatter.displayTask(curentTask));
          } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please include a (/by) command, followed by a date");
          } catch (StringIndexOutOfBoundsException ex) {
            System.out.println(
                "Please enter a name, followed by a (/by) command, followed by a date");
          } catch (DateTimeParseException ex) {
            System.out.println("Please enter a time format as dd/MM/yyyy");
          }

          break;
        }
        case "event": {
          try {
            int commandLength = commandString.length() + 1;
            String taskName = inputString.substring(commandLength);
            String[] parts = taskName.split("/from", 2);
            String name = parts[0];
            String dates = parts[1];
            String[] datesplit = dates.split("/to", 2);
            String startDate = datesplit[0];
            String endDate = datesplit[1];

            Task curentTask = new Event(name, startDate, endDate);
            taskList.add(curentTask);

            System.out.println("added:\t" + uiFormatter.displayTask(curentTask));
          } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("The event command cannot be empty!");
          } catch (ArrayIndexOutOfBoundsException ex) {

            System.out.println(
                "Please enter a name, followed by a (/from) command, followed by a date, followed by a (/to) command and a date");
          }
          break;
        }
        case "delete": {
          if (taskList.isEmpty()) {
            System.out.println("The list is empty!");
            break;
          }
          try {
            int index = Integer.parseInt(inputTokens[1]);
            index--;
            // remove the current task
            Task selectedTask = taskList.get(index);
            taskList.remove(index);
            System.out.println("Deleting selected task!");
            System.out.println(uiFormatter.displayTask(selectedTask));
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid index!");
          }
          break;


        }
        default:
          System.out.println("Please enter a suitable task!");
      }
    }

    System.out.println(goodbye);
    storage.saveTasks();

  }
}
