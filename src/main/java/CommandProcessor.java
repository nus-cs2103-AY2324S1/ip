public class CommandProcessor {
    private final TaskList tasks;

    CommandProcessor() {
        this.tasks = new TaskList();
    }
    //processCommand is a method that process the command and prints the relevant output
    void processCommand(String command) {

        if (command.equals("list")) {
            this.tasks.listContent();
            return;
        }

        String frontCommand = command.split(" ")[0];
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


        Task task = new Task(command);
        tasks.add(task);
    }
}
