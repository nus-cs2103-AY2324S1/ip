package dude.exception;

public class SearchStringMissingException extends DudeException {
  public SearchStringMissingException() {
    super("Please specify a keyword to search.");
  }
}
