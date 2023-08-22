import java.util.ArrayList;
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
      String inputString = scanner.nextLine();
      String[] inputTokens = inputString.split(" ");

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
          int index = Integer.parseInt(inputTokens[1]);
          index--;
          System.out.println("Marked selected task as done");
          // set current task as done
          Task selectedTask = taskList.get(index);
          selectedTask.setDone();

          System.out.println(uiFormatter.displayTask(selectedTask));

          break;
        }
        case "unmark": {

          int index = Integer.parseInt(inputTokens[1]);
          index--;
          System.out.println("Marked selected task as un-done desu");
          // set current task as un-done
          Task selectedTask = taskList.get(index);
          selectedTask.setUnDone();
          System.out.println(uiFormatter.displayTask(selectedTask));

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
        default:
          System.out.println("Please enter a suitable task!");
      }
    }

    System.out.println(goodbye);

  }
}
