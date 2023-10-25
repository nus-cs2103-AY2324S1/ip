# Duke Chatbot User Guide

Duke is a command-line chatbot that allows you to manage your tasks easily. You can interact with Duke by typing various commands to add, list, mark, unmark, delete, and save tasks. Duke also stores your tasks in a file for persistence across sessions.

## Table of Contents

1. [Getting Started](#getting-started)
2. [Available Commands](#available-commands)
3. [Task Types](#task-types)
4. [Exiting Duke](#exiting-duke)
5. [Graphical User Interface (GUI)](#graphical-user-interface-gui)
6. [Troubleshooting](#troubleshooting)
7. [Additional Help](#additional-help)

---

### Getting Started

To start using Duke, follow these steps:

1. Launch Duke by running the application.

2. Duke will greet you with a welcome message and a list of available commands.

3. You can type commands in the chat and press "Send" to interact with Duke.


### Available Commands

Duke supports the following commands:

- `bye`: Exits the chatbot and saves the task list to a file.


- `list`: Lists all tasks in the current task list.

Example:

User: `list`

Duke: 
```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [E][X] Attend webinar (from: 2023-09-25 14:00, to: 2023-09-25 16:00)
```

- `mark <task_id>`: Marks a task as done by its ID.

Example:

User: `mark 1`

Duke: 
```
Great! I've marked this task as done:
[T][X] Buy groceries
```

- `unmark <task_id>`: Unmarks a previously marked task.

Example:

User: `unmark 1`
Duke: 
```
Great! I've marked this Task as undone:
[T][ ] Buy groceries
```


- `todo <description>`: Adds a to-do task with a description.

Example:

User: `todo Read a book`
Duke: 
```
Got it! I've added this task:
[T][ ] Read a book
```



- `event <description> /from <datetime> /to <datetime>`: Adds an event task with a description, start date, and end date.

Example:
User: `event Team meeting /from 27/09/2023 1500 /to 27/09/2023 1630`
Duke: 
```
Great! I have added this task:
[E][ ] Team meeting (from: 2023-09-27 15:00, to: 2023-09-27 16:30)
```


- `deadline <description> /by <datetime>`: Adds a deadline task with a description and due date.

Example:
User: `deadline Submit report /by 30/09/2023 2359`
Duke: 
```
Great! I have added this task:
[D][ ] Submit report (by: 2023-09-30 23:59)
```

- `delete <task_id>`: Deletes a task by its ID.

Example:

User: `delete 1`
Duke: 
```
Great! I have removed this task:
[T][ ] Read a book
```

- `find <keyword>`: Searches for tasks containing the specified keyword.

Example:

User: `find meeting`
Duke: 
```
Here are the matching tasks in your list:
1. [E][ ] Team meeting (from: 2023-09-27 15:00, to: 2023-09-27 16:30)
```

### Task Types

Duke supports different types of tasks:

- **To-Do**: A simple task with a description.

- **Event**: A task with a description and a start date and end date.

- **Deadline**: A task with a description and a due date.

### Exiting Duke

To exit Duke, simply type `bye` in the chat and press "Send." Duke will save your task list to a file before closing.

### Graphical User Interface (GUI)

Duke also provides a graphical user interface (GUI) for a more interactive experience. The GUI allows you to type commands in a text field and displays Duke's responses in a chat-like interface.

### Troubleshooting

If you encounter any issues while using Duke, consider the following:

- Ensure you're using valid commands and following the correct syntax.

- If Duke encounters an error, it will provide an error message with details.

- If you need help with a specific command, type `help` to get a list of available commands and their usage.

### Additional Help

For additional help or questions, please refer to the chatbot's responses or type `help` for a list of available commands and their descriptions.

---

Enjoy using Duke to manage your tasks efficiently!