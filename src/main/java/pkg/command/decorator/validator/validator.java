package pkg.command.decorator.validator;
import java.util.regex.Pattern;

import pkg.command.command;

public abstract class validator implements command { 
    private Pattern markPattern;
    private command command;
    private String errorMessage;

    public validator(command command, String pattern, String errorMessage) {
        this.command = command;
        this.markPattern = Pattern.compile(pattern);
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(String input) {
        if (markPattern.matcher(input).matches()) {
            command.execute(input);
            return;
        }
        // to fix: this is not a good way to handle error
        System.out.println("Validating error: " + errorMessage);
    }  
}
