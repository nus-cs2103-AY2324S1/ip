package Frenchie;

public class Frenchie {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    public Frenchie(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
        try {
            this.tasks = storage.load();
        } catch (FrenchieException e) {
            tasks = new TaskList();
        }
    }

    public void exit() {
        ui.exitMessage();
        ui.closeScanner();
    }
    public void run() {
        ui.welcomeMessage();
        while (true) {
            String input = ui.retrieveUserInput();
            Command command = Parser.parseCommand(input);
            String details = Parser.parseDetails(input);
            try {
                switch (command) {
                    case bye:
                        storage.save(tasks);
                        this.exit();
                        return;
                    case list:
                        ui.listTasks(tasks);
                        break;
                    case mark:
                        int index = Integer.parseInt(details) - 1;
                        tasks.markTaskAsCompleted(index);
                        ui.markTaskAsComplete(tasks.get(index));
                        break;
                    case unmark:
                        index = Integer.parseInt(details) - 1;
                        tasks.markTaskAsIncomplete(index);
                        ui.markTaskAsIncompelte(tasks.get(index));
                        break;
                    case delete:
                        index = Integer.parseInt(details) - 1;
                        Task task = tasks.get(index);
                        tasks.deleteTask(index);
                        ui.deleteTask(task, tasks);
                        break;
                    case todo:
                        String taskName = details;
                        ToDo todo = new ToDo(taskName);
                        tasks.addTask(todo);
                        ui.addTask(todo, tasks);
                        break;
                    case deadline:
                        taskName = details.split("/by")[0].trim();
                        String deadlineDate = details.split("/by")[1].trim();
                        Deadline deadline = new Deadline(taskName, deadlineDate);
                        tasks.addTask(deadline);
                        ui.addTask(deadline, tasks);
                        break;
                    case event:
                        taskName = details.split("/")[0].trim();
                        String startTime = details.split("/from")[1].split("/to")[0].trim();
                        String endTime = input.split("/from")[1].split("/to")[1].trim();
                        Event event = new Event(taskName, startTime, endTime);
                        tasks.addTask(event);
                        ui.addTask(event, tasks);
                        break;
                    case find:
                        String keyword = details;
                        ui.findMessage();
                        tasks.returnMatchTasks(keyword);
                        break;
                    case invalid:
                        throw new FrenchieException("____________________________________________________________\n" +
                                "OOPS!!! I'm sorry but I don't know what that means! :-(\n" +
                                "____________________________________________________________");
                }
            } catch (FrenchieException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Frenchie("frenchie.txt").run();
    }
}
