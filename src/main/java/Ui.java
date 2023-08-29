public class Ui {
    static String horizontalLine = "--------------------";
    String chatbotName;

    Ui(String chatbotName) {
        this.chatbotName = chatbotName;
    }

    void greetUser() {
        printMessage("Hello I'm " + chatbotName + "\nWhat can I do for you?");
    }

    void farewellUser() {
        printMessage("Bye. Hope to see you again soon!");
    }

    void printMessage(String text) {
        // prints text with horizontal lines above and below it
        System.out.println(horizontalLine);
        System.out.println(text);
        System.out.println(horizontalLine);
        System.out.println();
    }

    void printInvalidCommandTypeExceptionResponse() {
        printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    void printInvalidMarkOrUnmarkResponse(int numTotalTasks) {
        printMessage("☹ OOPS!!! The description of a mark/unmark must be between 1 and " + numTotalTasks + ".");
    }

    void printEmptyTodoDescriptionResponse() {
        printMessage("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    void printInvalidDeleteResponse(int numTotalTasks) {
        printMessage("☹ OOPS!!! The description of a delete must be between 1 and " + numTotalTasks + ".");
    }

    void printNoDataResponse() {
        printMessage("No previous data found. Creating new task list for you!");
    }

    void printSaveFailResponse(String savePath) {
        System.out.println("Failed to write to " + savePath);
    }
}
