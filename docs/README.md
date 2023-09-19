# Ekud 🤖

Ekud is a simple chatbot that helps you remember your to-dos, deadlines and events. The name Ekud comes from spelling Duke — the name of the project this chatbot is based upon — backwards. It is an attempt at humour.

## Features

* Create and delete tasks with _optional_ deadlines or start/end dates
* List all the tasks you currently have
* Mark tasks as done and unmark them as well
* Find tasks by searching their titles
* Remove any duplicate tasks
* Automatically saves tasks to a file and loads when the chatbot is opened

## Installation

1. Ensure a _recent_ version of Java is installed on your system
2. Download the [latest release](https://github.com/ravern/ip/releases) of Ekud from GitHub
3. Run the JAR file using the command `java -jar ekud.jar`

## Usage

Here's how to use Ekud.

### `list`

The `list` command lists all the tasks that are stored in Ekud at that moment. It will provide an index for each task e.g. 1, 2, 3 which is important for task manipulation with the other commands.

![Demo of `list` command](images/list.png)

### `todo`, `deadline`, `event`

Each of these commands creates a new task within the list. `todo` creates a new basic task, which only contains a title with no notion of date/time attached. `event` creates a task which has a start date/time and an end date/time. `deadline` creates a task which has a deadline date/time attached to it.

The date provided should be in the format `DD-MM-YYYY` e.g. `03-04-2023`, while the time should be provided in the format `HH:mm` e.g. `17:00`.

![Demo of `todo`, `event` and `deadline` commands](images/todo-event-deadline.png)

### `mark`, `unmark`

These commands `mark` or `unmark` a task as completed or not completed, respectively. They take in a single argument — task index — that must be an integer containing a valid index into the list of tasks shown by the `list` command.

![Demo of `mark` and `unmark` commands](images/mark-unmark.png)

### `find`

The `find` command searches and prints all tasks with titles that contain the given string. **Take note, the indices returned from this command aren't the same as in the `list` command. Do not use these indices to manipulate the tasks i.e. `mark`, `delete`, etc.**

![Demo of `find` command](images/find.png)

### `delete`

The `delete` command deletes the task with the given index from the list of tasks.

![Demo of `delete` command](images/delete.png)

### `clean`

The `clean` command removes any duplicate tasks that might have been added by accident into the task list. Two tasks are considered equal if their types i.e to-do, event, deadline, titles and any date/times attached are exactly the same.

![Demo of `clean` command](images/clean.png)