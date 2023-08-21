import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void setDone() {
            this.isDone = true;
        }

        public void setNotDone() {
            this.isDone = false;
        }
        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
    }
    public static void main(String[] args) {
        Task[] list = new Task[100]; // List to be returned when input is "list"
        int counter = 0; // Items in list
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Good day to you, I'm ButlerBot.\n" +
                "How may I be of service to you?\n"); // Greets user

        String echo = myObj.nextLine(); // Reads user input

        while (!echo.equals("bye")) {
            if (echo.startsWith("mark ")) { // Mark task complete
                int index = Integer.valueOf(echo.substring(5)) - 1; // Index of task
                list[index].setDone(); // Item mark complete
                System.out.println("Congratulations on finishing the task. I will now mark it as complete: \n" +
                        list[index].toString());
            }

            else if (echo.startsWith("unmark ")) { // Mark task complete
                int index = Integer.valueOf(echo.substring(7)) - 1; // Index of task
                list[index].setNotDone(); // Item mark complete
                System.out.println("No worries. I will now mark it as incomplete: \n" +
                        list[index].toString());
            }

            else if (echo.equals("list")) {
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(i + 1 + ". " + list[i]); // Returns the list
                    }
                }
            } else {
                // Echos the input
                String response = "Understood, I will add \"" + echo + "\" to your to do list.";
                System.out.println(response);

                // Insert action into list
                Task add = new Task(echo);
                list[counter] = add;
                counter += 1;
            }
            echo = myObj.nextLine();
        }
        System.out.println("Goodbye and have a nice day.\n"); // Exits the bot
    }
}
