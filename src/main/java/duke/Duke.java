package duke;

import java.util.Scanner;

//This class has a chatbot which will generate a list based on a set of tasks
//that have been input by the user. These tasks include deadlines, events and ToDos
//The user input will be parsed to extract the date and time of the events and deadlines
public class Duke {
    public static void main(String[] args) {
        Storage storage = new Storage();
        //System.out.println(tasks);
        Ui ui = new Ui();
        ui.printHello();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Parser parser = new Parser();
        TaskList tasks = storage.loadTasks("src/data/Duke.txt");
        while (!str.equals("bye")) {
            parser.chat(str, tasks);
            str = scanner.nextLine();
        }
        storage.saveTasks("src/data/Duke.txt", tasks);
        scanner.close();
        ui.printBye();
    }
}

