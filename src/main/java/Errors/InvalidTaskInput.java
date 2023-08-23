package Errors;

public class InvalidTaskInput extends Exception{
    public InvalidTaskInput() {
        super("Invalid input entered. Check whether your text follows the requirements for the specific task provided.");
    }
}
