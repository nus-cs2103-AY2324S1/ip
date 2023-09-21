# User Guide
Duke is a chat-bot application where user can note down their task.

The chat-bot is named **Chewbacca, son of Attichukk**, also known as `Chewie`.

![Ui.png](Ui.png)

## Features 

### Adding To-Do - `ToDo` 

**Creates a ToDo task in the application.**

Command : `todo <task name>`

### Adding task with deadline - `Deadline`

**Creates a Deadline task in the application.**

Command : `deadline <task name> /by <yyyy-mm-dd>`

### Adding a event with set date - `Event`

**Creates an Event task in the application.**

Command : `event <task name> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`

### Find task - `Find`

**Find task with selected keyword.**

Command : `find <keyword>`

### Assign task with tags - `Tag`

* **Assign tag to a task.**
  * Command : `assign <task index in list> <tag name>`
  

* **Delete tag from task.**
  * Command : `remove <task index in list> <tag name>`


* **Search for a tag.**
  * Command : `search <tag name>`


### Listing all tasks - `List`

**List out all tasks.**

Command : `list`

### Marking / unmarking task as done - `Mark / Unmark`

**Mark & unmark selected task as done.**

Command :
1. `mark <task index in list>`
2. `unmark <task index in list>`

### Deleting task. - `Delete`

Delete the task from the list.

Command : `delete <task index in list>`

### Save & Load Data

Saving and loading are done automatically after every command executed.


