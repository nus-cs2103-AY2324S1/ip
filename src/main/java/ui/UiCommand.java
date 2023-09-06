package ui;

public class UiCommand {

    public enum UiInstructType {
        PRINT,
        QUIT
    }

    private UiInstructType instructType;
    private String arg;

    public UiCommand(UiInstructType instructType, String arg) {
        this.instructType = instructType;
        this.arg = arg;
    }

    public UiInstructType getInstructType() {
        return instructType;
    }

    public String getArg() {
        return arg;
    }



}
