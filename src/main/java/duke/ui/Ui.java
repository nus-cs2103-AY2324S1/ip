package duke.ui;

import java.util.Scanner;

public class Ui {
        private String name = "Bob";
        private String horizontal = "____________________________________________________________";
        private Scanner sc;

        public Ui() {
            this.sc = new Scanner(System.in);
        }

        public String messageCard(String message) {
            return horizontal + "\n" + message + "\n" + horizontal;
        }
        public void showWelcome() {
            String ln1 = String.format("Hello! I'm %s", this.name);
            String ln2 = "What can I do for you?";
            String ln3 = ln1 + "\n"+ ln2;

            System.out.println(messageCard(ln3));
        }
        public void bye() {
            String ln3 = "Bye. Hope to see you again soon!";
            System.out.println(messageCard(ln3));
        }

        public void showLoadingError() {
            String error = this.name + ": Error encountered, loading screen failed!";
            System.out.println(error);;
        }

        public void showError(String msg) {
            System.out.println(msg);
        }

        public void showLine() {
            System.out.println(horizontal);
        }

        public void printMessage(String msg) {
            System.out.println(messageCard(msg));
        }

        public String readCommand() {
            return this.sc.nextLine();
        }
}
