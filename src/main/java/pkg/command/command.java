package pkg.command;

public interface command {
    public void execute(String input);
}

abstract class decorator implements command {
    private command command = null;

    public decorator(command command) {
        this.command = command;
    }

    @Override
    public void execute(String input) {
        if (this.command != null) {
            this.command.execute(input);
        }
    } 
}


