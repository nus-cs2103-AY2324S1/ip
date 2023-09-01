package phi;

import java.util.Scanner;
public class Phi {
    private final Ui phiUi;
    private final Storage taskStorage;
    private final TaskList tasks;
    private final Parser phiParser;

    public Phi(String filePath) {
        phiUi = new Ui();
        taskStorage = new Storage(filePath);
        tasks = taskStorage.readFromFile();
        phiParser = new Parser(tasks);
    }

    public static void main(String[] args) {
        new Phi("./data/tasklist.txt").run();
    }

    private void run() {
        phiUi.greeting();
        takeInput();
        phiUi.goodbye();
    }

    private void takeInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            phiParser.handle(input);
            taskStorage.writeToFile(tasks);
            System.out.println();
            input = sc.nextLine();
        }
        sc.close();
    }
}