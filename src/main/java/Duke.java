import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {

    String greeting = "Hello! I'm KimochiUsagi (きもち　うさぎ)!\n";
    String info = "Ask the bunny a question!\n";
    String goodbye = "Bye. See you again! (またね)";

    ArrayList<Task> taskList = new ArrayList<>();
    UiFormatter uiFormatter = new UiFormatter();
    System.out.println(greeting);
    System.out.println(info);

    Scanner scanner = new Scanner(System.in);

    label:
    while (true) {

      String inputString = null;
      String[] inputTokens = null;

      try {
        inputString = scanner.nextLine();
        inputTokens = inputString.split(" ");
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
          int commandLength = commandString.length() + 1;
          String taskName = inputString.substring(commandLength);
          Task curentTask = new Todo(taskName);
          taskList.add(curentTask);

          System.out.println("added:\t" + uiFormatter.displayTask(curentTask));

          break;
        }
        case "deadline": {
          int commandLength = commandString.length() + 1;
          String taskName = inputString.substring(commandLength);
          String[] parts = taskName.split("/by", 2);
          String name = parts[0];
          String endDate = parts[1];
          Task curentTask = new Deadline(name, endDate);
          taskList.add(curentTask);

          System.out.println("added:\t" + uiFormatter.displayTask(curentTask));

          break;
        }
        case "event": {

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
          break;
        }
        default:
          System.out.println("Please enter a suitable task!");
      }
    }

    System.out.println(goodbye);

  }
}
