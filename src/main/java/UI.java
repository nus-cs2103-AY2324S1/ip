public class UI {
    void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    void showWelcome() {
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Rua, your ChatBot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);
    }

    void showMessage(String str) {
        System.out.println(str);
    }

    void showError(String errorMessage) {
        System.out.println("You get an error: " + errorMessage + "\n");
    }

    void showLoadingError() {
        showError("Given tasklist cannot be loaded. Now creating a new tasklist instead.");
    }
}
