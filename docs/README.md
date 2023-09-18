# Duke User Guide

Duke is a command-line task management application that helps you keep track of your tasks. This guide will walk you through the features and usage of Duke.

## Features

### Add a Todo Task

You can add a new todo task to your task list.

Example of usage:

`todo Buy groceries`

Expected outcome:

Duke will add the todo task and confirm it.

```
Got it. I've added this task:
[T][✘] Buy groceries
Now you have 1 task in the list.
```

### Add a Deadline Task

You can add a new deadline task with a description and due date.

Example of usage:

`deadline Submit report /by 2023-10-01`

Expected outcome:

Duke will add the deadline task and confirm it.

```
Got it. I've added this deadline task:
[D][✘] Submit report (by: Oct 01 2023)
Now you have 2 tasks in the list.
```

### Add an Event Task

You can add a new event task with a description and start and end dates.

Example of usage:

`event Team meeting /from 2023-09-20 /to 2023-09-21`

Expected outcome:

Duke will add the event task and confirm it.

```
Got it. I've added this event task:
[E][✘] Team meeting (from: Sep 20 2023, to: Sep 21 2023)
Now you have 3 tasks in the list.
```

### List Tasks

You can view the list of all tasks in your task list.

Example of usage:

`list`

Expected outcome:

Duke will display your list of tasks.

```
1: [T][✘] Buy groceries
2: [D][✘] Submit report (by: Oct 01 2023)
3: [E][✘] Team meeting (from: Sep 20 2023, to: Sep 21 2023)
```

### Mark Task as Done

You can mark a task as done by specifying its task number.

Example of usage:

`mark 1`

Expected outcome:

Duke will mark the task as done and confirm it.

```
Nice! I've marked this task as done:
[T][✓] Buy groceries
```

### Unmark Task as Undone

You can mark a task as undone by specifying its task number.

Example of usage:

`unmark 1`

Expected outcome:

Duke will mark the task as undone and confirm it.

```
OK, I've marked this task as not done yet:
[T][✘] Buy groceries
```

### Delete Task

You can delete a task by specifying its task number.

Example of usage:

`delete 1`

Expected outcome:

Duke will delete the task and confirm it.

```
Noted. I've removed this task:
[T][✘] Buy groceries
Now you have 2 tasks in the list.
```

### Find Tasks

You can search for tasks with a specific keyword.

Example of usage:

`find report`

Expected outcome:

Duke will display a list of tasks that match the keyword.

```
1: [D][✘] Submit report (by: Oct 01 2023)
```

### Help

You can view the list of available commands at any time.

Example of usage:

`help`

Expected outcome:

Duke will display the available commands.

```
Here are the available commands:
1. bye - Exit the program
2. list - List all tasks
3. mark <taskNumber> - Mark a task as done
4. unmark <taskNumber> - Mark a task as undone
5. delete <taskNumber> - Delete a task
6. todo <description> - Add a new todo task
7. deadline <description> /by <dueDate> - Add a new deadline task
8. event <description> /from <startDate> /to <endDate> - Add a new event task
9. help - Displays the available commands
10. find <keyword> - Search for tasks with a keyword
```

### Exit

You can exit the program at any time.

Example of usage:

`bye`

Expected outcome:

Duke will bid you farewell.

```
Bye. Hope to see you again soon!
```

## Getting Started

To use Duke, follow these steps:

1. Download and install Java on your computer if it's not already installed.

2. Download the Duke JAR file under `Releases`

3. Open your command prompt or terminal and navigate to the directory where you saved the JAR file.

4. Run Duke by executing the following command:

```
java -jar duke.jar
```

5. You can now start using Duke by entering the available commands as mentioned above.

Enjoy using Duke to manage your tasks efficiently!
