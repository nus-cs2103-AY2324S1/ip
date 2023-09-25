package enums;

/**
 * The `FilePath` enum represents file paths used in the Woof application.
 */
public enum FilePath {
    CUSTOM_FONT("/fonts/sono/static/Sono-Light.ttf"),
    USER_DISPLAY_PICTURE("/images/userDisplayPicture.jpeg"),
    BOT_DISPLAY_PICTURE("/images/botDisplayPicture.jpeg"),
    CUSTOM_CURSOR("/images/paw.png"),
    DIALOG_AREA_CSS("/styles/dialogArea.css"),
    ROOT_CSS("/styles/root.css"),
    SCROLL_PANE_CSS("/styles/scrollPane.css"),
    SEND_BUTTON_CSS("/styles/sendButton.css"),
    USER_INPUT_CSS("/styles/userInput.css"),
    CLEAR_BUTTON_CSS("/styles/clearButton.css"),
    DEFAULT_STORAGE_PATH(".data/task.json"),
    FXML_VIEW_PATH("/views/WoofWoof.fxml");

    private final String value;

    /**
     * Constructs a `FilePath` enum with the given value.
     *
     * @param value The string representation of the `FilePath`.
     */
    FilePath(String value) {
        this.value = value;
    }

    /**
     * Gets the string representation of the `FilePath`.
     *
     * @return The string representation of the `FilePath`.
     */
    public String toValue() {
        return this.value;
    }
}

