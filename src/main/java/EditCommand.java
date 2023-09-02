public class EditCommand extends Command{
    private String command;
    private Edit editType;
    public EditCommand(String command, Edit editType) {
        this.command = command;
        this.editType = editType;
    }

    @Override
    public void execute() {
        switch (this.editType) {
        case MARK:
            try {
                int index = Integer.parseInt(command.substring(5));
                Task targetedTask = TaskList.getTaskByIndex(index);
                targetedTask.mark();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                Ui.printAlertForMark();
            } catch (AlexException e) {
                System.out.println(Ui.horizontalLine
                        + e.getMessage() + "\n"
                        + Ui.horizontalLine
                );
            } finally {
                break;
            }

        case UNMARK:
            try {
                int index = Integer.parseInt(command.substring(7));
                Task targetedTask = TaskList.getTaskByIndex(index);
                targetedTask.unmark();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                Ui.printAlertForUnmark();
            } catch (AlexException e) {
                System.out.println(Ui.horizontalLine
                        + e.getMessage() + "\n"
                        + Ui.horizontalLine
                );
            } finally {
                break;
            }

        case DELETE:
            try {
                int toDeleteIndex = Integer.parseInt(command.substring(7, 8));
                TaskList.delete(toDeleteIndex);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                Ui.printAlertForDelete();
            } catch (AlexException e) {
                System.out.println(Ui.horizontalLine
                        + e.getMessage() + "\n"
                        + Ui.horizontalLine
                );
            } finally {
                break;
            }
        }
    }


}
