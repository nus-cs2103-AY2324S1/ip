public class MissingTitleException extends DukeException {

    public MissingTitleException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Title of the task is missing! Please give your task a name :)";
    }

}

