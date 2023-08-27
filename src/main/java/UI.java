class UI {
    Duke.Model model;
    boolean isExit = false;
    InputHandler inputHandler;

    public UI() {
        this.model = new Duke.Model();
        this.inputHandler = new InputHandler(model);
    }

    public void run() {
        while (!isExit) {
            inputHandler.handleInput();
            isExit = inputHandler.isExit();
        }

        model.bye();
    }
}
