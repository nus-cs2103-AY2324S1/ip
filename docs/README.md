# Cristiano Ronaldo Task Manager 🤖

Penaldo is a graphical user interface (GUI) desktop app that helps you track your upcoming tasks. To start using Cristiano, you can:

- Download the `.jar` file under `Releases`,
- Navigate to the directory in terminal,
- run `java -jar penaldo.jar`, and voila!

## Features 👾

Penaldo is packed with various features, such as:

* Addition and deletion of tasks
    * Todos
    * Events
    * Deadlines
* Marking tasks as done
* Searching tasks with keywords

## User Guide 🕹

### - `list`

See a list of your tasks.

#### Usage

`list`

```
> list
Here are the tasks in your list. Finish them all so we can beat Messi.
	1. [T][ ] CS2103T assignment
	2. [D][ ] CS2101 slides (by 21 Sep 2023, 23:59)
	3. [E][X] Football (from 19 Sep 2023 17:00 to 19 Sep 2023 19:00)
```

### - `todo`

Create a todo.

#### Usage

`todo <task name>`

```
> todo CS2103T assignment
Got it. I've added the task:
    [T][ ] CS2101 slides
You now have 1 task in your list, just like how I have 5 Ballon d'Ors.
```

### - `event`

Create an event.

#### Usage

`event <event name> /from <start time> /to <end time>`

`<start time>` and `<end time>` should be `DD/MM/YYYY HH:MM`.

```
> event Football /from 19/09/2023 16:00 /to 19/09/2023 19:00
Got it. I've added the task:
    [E][ ] Football (from 19 Sep 2023 17:00 to 19 Sep 2023 19:00)
You now have 2 tasks in your list, just like how I have 5 Ballon d'Ors.
```

### - `deadline`

Create a deadline.

#### Usage

`deadline <deadline name> /by <time>`

`<time>` should be in the form of `DD/MM/YYYY HH:MM`.

```
> deadline CS2101 slides /due 21/09/2023 23:59
Got it. I've added the task:
    [D][ ] CS2101 slides (by 21 Sep 2023, 23:59)
You now have 3 tasks in your list, just like how I have 5 Ballon d'Ors.
```

### - `mark`

Mark a task as completed.

#### Usage

`mark <task number>`

```
> mark 3
SIUUU! I've marked this task as done. We will make Saudi League number 1.
    [E][X] Football (from 19 Sep 2023 17:00 to 19 Sep 2023 19:00)
```

### - `delete`

Delete a specific task.

#### Usage

`delete <task number>`

```
> delete 1
I've deleted this task from the list!
    [T][ ] CS2103T assignment
Now you have 2 tasks in your list.
```

### - `find`

Search a task based on the keyword provided.

#### Usage

`find <keyword>`

```
> find assignment
Here are the matching tasks in your list
    1. [T][ ] CS2103T assignment
```

### - `bye`

Quit the app.

#### Usage

`bye`

```
> bye
Bye. Hope to see you again in the Saudi League!
```