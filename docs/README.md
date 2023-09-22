# User Guide
Welcome to THE **CENATOR**.

*I HOPE YOU WEREN'T EXPECTING A SIMPLE CHATBOT TO 
HELP YOU ORGANIZE TASKS, BECAUSE YOU'RE NOT GETTING IT*

> How did I keep this dumb project going for as long as I did... - Eola-Z

## Features

### Task management

The **CENATOR** will begrudgingly keep track of tasks you enter and list them out for you if requested.

Tasks can be marked done, and are separated into the following categories: Todo; Event; Deadline.

### Tagging

Tags can be added to each task to make recognizing them easier

## Getting started

1. Go [here](https://github.com/Eola-Z/ip/releases) and install the `v.1.0.0` .jar file
2. Run `java -jar release.jar`
3. Enjoy the pain!

## Usage

### `todo` `deadline` `event` - Add tasks

Add tasks alongside additional details if needed

Example of usage: 

`todo (task)`

`deadline (task) /by (yyyy-mm-dd)`

`event (task) /from (yyyy-mm-dd) /to (yyyy-mm-dd)`

Expected outcome:

```
You want to add (task)? Sure whatever
```

### `mark` `unmark` - Mark tasks

Mark tasks as complete or incomplete.

Example of usage:

`mark (index)`

`unmark (index)`

Expected outcome:

```
Marked the following task: (task)
```

### `delete` - Delete tasks

Delete a task.

Example of usage:

`delete (index)`

Expected outcome:

```
See this? (task) Now you don't.
```

### `find` - Search for tasks

Search for tasks containing a keyword.

Example of usage:

`find (keyword)`

Expected outcome:

```
Here you go (list of tasks containing the keyword)
```

### `list` - List all tasks

Lists all stored tasks.

Example of usage:

`list`

Expected outcome:

```
Here you go (list of tasks)
```

### `save` - Save current tasklist

Saves the current tasklist so that they can be loaded on the next startup.

Example of usage:

`save`

Expected outcome:

```
SAVED
```