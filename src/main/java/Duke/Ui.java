package Duke;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public Ui() {
    }
    private Parser parser = new Parser();



    public String showLoadingError() {
        return "Got error loading!";
    }

    public void getInput(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("bye")) {
            try {
                parser.parse(s, tasks);
                s = sc.nextLine();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                s = sc.nextLine();
            }
        }
        if (s.equals("bye")) {
            try {
                storage.saveTasks(tasks.taskList);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Bye! Auntie maggie see you later!");
        }
    }
}


