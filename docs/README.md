# Bot 🤖

Bot is a graphical user interface (GUI) desktop app that helps you track your upcoming tasks. To start using Duke, you can:

- Download the `.jar` file under `Releases`,
- Navigate to the directory in terminal,
- run `java -jar bot.jar`, and voila!

## Features 👾

Bot is packed with various features, such as:

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
Here are the tasks in your list:
	1. [T][ ] CS2101 slides
	2. [D][ ] CS2109S lecture quiz (by 24 Sep 2023 23:59)
	3. [E][ ] CS2103T finals (from 1 Dec 2023 09:00 to 1 Dec 2023 11:00)
	4. [T][ ] CS3230 problem set
```

### - `todo`

Create a todo.

#### Usage

`todo <task name>`

```
> todo CS2101 slides
added:
	[T][ ] CS2101 slides
You have 1 tasks in the list.
```

### - `event`

Create an event.

#### Usage

`event <event name> /from <start time> /to <end time>`

`<start time>` and `<end time>` should be `DD/MM/YY HH:MM`.

```
> event CS2103T finals /from 01/12/23 09:00 /to 01/12/23 11:00
added:
	[E][ ] CS2103T finals (from 1 Dec 2023 09:00 to 1 Dec 2023 11:00)
You have 3 tasks in the list.
```

### - `deadline`

Create a deadline.

#### Usage

`deadline <deadline name> /by <time>`

`<time>` should be in the form of `DD/MM/YY HH:MM`.

```
> deadline CS2109S lecture quiz /by 24/09/23 23:59
added:
	[D][ ] CS2109S lecture quiz (by 24 Sep 2023 23:59)
You have 2 tasks in the list.
```

### - `done`

Mark a task as completed.

#### Usage

`done <task number>`

```
> done 3
I've marked this task as done!
	[E][X] CS2103T finals (from 1 Dec 2023 09:00 to 1 Dec 2023 11:00)
```

### - `delete`

Delete a specific task.

#### Usage

`delete <task number>`

```
> delete 1
I've deleted this task from the list!
	[T][ ] CS2101 slides
You have 3 tasks in the list.
```

### - `find`

Search a task based on the keyword provided.

#### Usage

`find <keyword>`

```
> find CS
Here are the matching tasks in your list:
	1. [E][X] CS2103T finals (from 1 Dec 2023 09:00 to 1 Dec 2023 11:00)
	2. [D][ ] CS2109S lecture quiz (by 24 Sep 2023 23:59)
```

### - `bye`

Quit the app.

#### Usage

`bye`

```
> bye
Bye bye
```