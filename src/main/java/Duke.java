import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

public class Duke {

  private TaskList taskList;
  private Parser parser;
  private Ui ui;
  private Storage storage;

  public Duke() {
    this.taskList = new TaskList();
    this.parser = new Parser();
    this.ui = new Ui();
    this.storage = new Storage(this.taskList);
  }

  public void run() {

    String greeting = "Hello! I'm KimochiUsagi (きもち　うさぎ)!\n";
    String info = "Ask the bunny a question!\n";
    String goodbye = "Bye. See you again! (またね)";

    storage.loadTasks();
    System.out.println(greeting);
    System.out.println(info);

    label:
    while (true) {

      try {
        parser.update();
      } catch (NoSuchElementException ex) {
        break;
      }

      // there is no input
      if (parser.isInputThere()) {
        break;
      }

      switch (parser.getCommandString()) {
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
            System.out.println(index + " " + ui.displayTask(selectedTask));

          }
          break;
        case "mark": {
          try {
            // set current task as done
            Task selectedTask = taskList.get(parser.getIndex());
            selectedTask.setDone();
            System.out.println("Marked selected task as done");
            System.out.println(ui.displayTask(selectedTask));
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid index!");
          }

          break;
        }
        case "unmark": {

          try {
            // set current task as un-done
            Task selectedTask = taskList.get(parser.getIndex());
            System.out.println("Marked selected task as un-done desu");
            selectedTask.setUnDone();
            System.out.println(ui.displayTask(selectedTask));
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid index!");
          }

          break;
        }
        case "todo": {
          try {
            Task curentTask = new Todo(parser.getTaskName());
            taskList.add(curentTask);

            System.out.println("added:\t" + ui.displayTask(curentTask));
          } catch (StringIndexOutOfBoundsException ex) {

            System.out.println("Please enter a name after the todo command!");
          }

          break;
        }
        case "deadline": {
          try {
            String taskName = parser.getTaskName();
            String[] parts = taskName.split("/by", 2);

            String name = parts[0];
            String endDate = parts[1];
            endDate = endDate.replace(" ", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(endDate, formatter);

            Task curentTask = new Deadline(name, date);
            taskList.add(curentTask);

            System.out.println("added:\t" + ui.displayTask(curentTask));
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
            String taskName = parser.getTaskName();
            String[] parts = taskName.split("/from", 2);
            String name = parts[0];
            String dates = parts[1];
            String[] datesplit = dates.split("/to", 2);
            String startDate = datesplit[0];
            String endDate = datesplit[1];

            Task curentTask = new Event(name, startDate, endDate);
            taskList.add(curentTask);

            System.out.println("added:\t" + ui.displayTask(curentTask));
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
            // remove the current task
            Task selectedTask = taskList.get(parser.getIndex());
            taskList.remove(parser.getIndex());
            System.out.println("Deleting selected task!");
            System.out.println(ui.displayTask(selectedTask));
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

  public static void main(String[] args) {

    Duke duke = new Duke();
    duke.run();

  }
}
