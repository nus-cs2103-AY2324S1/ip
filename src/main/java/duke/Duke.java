package duke;

//The main class to run
public class Duke {
    //Main method to start the program
    public static void main(String[] args) {
        String name = "Termina";
        System.out.println("Hello, I am your chatbot!\nMy name is " + name + "\nHow may I help?");
        UI ui = new UI();
        Storage load = new Storage("tasks.ser");
        TaskList items = new TaskList(load.loadDataFromFile());
        Parser parser = new Parser();
        ui.run(items, load, parser);

        System.out.println("Byeeee! Use me again please!");
    }
}
