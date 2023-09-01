public class InvalidTaskDataException extends DudeException {
  public InvalidTaskDataException() {
    super(
      "Could not read task(s) from saved data.\n"
        + "The format is possibly corrupted.\n"
        + "Try checking the file at "
        + Dude.SAVE_FILE_PATH
        + " and try again,\n"
        + "or delete the file to start over."
    );
  }
}
