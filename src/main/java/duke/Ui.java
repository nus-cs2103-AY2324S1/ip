package duke;

public class Ui {
    private static final String name = "Bartholomew Hamish Montgomery";
    static final String line = "______________________________________________________________________________________\n";

    public void greet() {
        String greeting = line + "I extend to you my utmost felicitations, User! I am " + name + "." + "\n" + "What may I do for you?" + "\n" + line;
        System.out.println(greeting);
    }
    public void goodbye() {
        String goodbye = line + "Until we meet once more in the near future, I bid you farewell." + "\n" + line;
        System.out.println(goodbye);
    }
    public String line() {
        return line;
    }

    public String showLoadingError() {
        return "Error";
    }
}
