/**
 * ip Project Duke Chat bot
 *
 * @author Aaron Tay
 * @since 2023-08-24
 */
import java.io.IOException;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.startBot();
        boolean isExit = false;

        while(!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }

        ui.endBot();
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/duke.txt").run();
    }


//    public static void main(String[] args) {
//        String name = "Obi-wan Kenobi";
//        String line = "_____________________________________";
//        String FILEPATH = "./src/main/data/duke.txt";
//        File f = new File(FILEPATH);
//        ArrayList<Task> taskList = new ArrayList<>();
//
//        try {
//
//            if (f.exists()) {
//                Scanner s = new Scanner(f); // create a Scanner using the File as the source
//                while (s.hasNext()) {
//                    addFileTask(taskList, s.nextLine());
//                }
//            } else {
//                f.createNewFile();
//            }
//        } catch (IOException e) {
//            System.out.print(e.getMessage());
//        }
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Hello There! I am " + name);
//        System.out.println("What can I do for you?");
//        System.out.println(line);
//
//        while (true) {
//            String input = scanner.nextLine();
//            String[] command = input.split(" ", 2);
//
//            if (command[0].equals("bye") && command.length == 1) {
//                break;
//            } else if (command[0].equals("list") && command.length == 1) {
//                System.out.println("Here are the tasks in your list:");
//                for (int i = 0; i < taskList.size(); i++) {
//                    System.out.print((i + 1) + "." + taskList.get(i).toString() + "\n");
//                }
//            } else if (command[0].equals("mark") || command[0].equals("unmark") || command[0].equals("delete")) {
//                try {
//                    editTask(command, taskList);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//            } else {
//                try {
//                    addTask(taskList, command);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//
//            System.out.println(line);
//        }
//
//        try {
//            writeTaskToFile(FILEPATH, taskList);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Bye. May the force be with you!");
//    }
}