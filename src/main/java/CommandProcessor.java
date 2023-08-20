public class CommandProcessor {
    private final TaskList tasks;

    CommandProcessor() {
        this.tasks = new TaskList();
    }
    //processCommand is a method that process the command and prints the relevant output
    void processCommand(String command) {


        // print the list of tasks
        if (command.equals("list")) {
            this.tasks.listContent();
            return;
        }

        String frontCommand = command.split(" ", 2)[0];
        if (frontCommand.equals("mark")) {
            try {
                String index = command.split(" ")[1];
                int taskNumber = Integer.parseInt(index);
                tasks.mark(taskNumber);
            } catch(Exception e) {
                System.out.println("Please enter valid task number that you want to mark");
            }

            return;
        }

        if (frontCommand.equals("unmark")) {
            try {
                String index = command.split(" ")[1];
                int taskNumber = Integer.parseInt(index);
                tasks.unMark(taskNumber);
            } catch(Exception e) {
                System.out.println("Please enter valid task number that you want to unmark");
            }

            return;
        }

        if (frontCommand.equals("todo")) {
            try {
                String taskName = command.split(" ", 2)[1];
                Task task = new Todo(taskName);
                tasks.add(task);
            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (frontCommand.equals("deadline")) {
            try {
                String taskName = command.split(" ", 2)[1];
                Task task = new Deadline(taskName);
                tasks.add(task);
            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (frontCommand.equals("event")) {
            try {
                String taskName = command.split(" ", 2)[1];
                Task task = new Event(taskName);
                tasks.add(task);
            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }
        } else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            return;
        }

    }
}
