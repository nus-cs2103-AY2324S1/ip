# User Guide

## Features 

- Adding todo/ deadline/ events 
- Deleting added events 
- Marking tasks as done 
- Listing out all tasks 
- Find command
- Tasks are automatically saved into memory and loaded up upon bot starting 
- Help command that can be run to list each command

### Adding todo/ deadline/ event 

Allows you to add a specific task.

- Todos are simple tasks that have to be done 
- Deadline allows for a specific time as well
- Events allow for a from and to time

### Deleting tasks

Tasks can be deleted as required by specifying the task number that can be found by using list

### Marking tasks as done

- Tasks can be marked as done when completed and can be unmarked as well if required.

### Listing out all tasks

- Using the list command lists all saved tasks and will number them.

### Help command 

- Help command lists all commands that can be used.

### Find

- Find command helps users find any task that may have been stored using a keyword.

## Usage

### `Adding todo/ deadline/ event`

Tasks can be added in 3 ways.

Example of usage: 

`todo call mom`

Expected outcome:

This will add the task and and show the total number of tasks added as well.

```
OK DONE ALR added your todo ah:
[T][ ] call mom
Got 4 task in list boy
```


### `Deleting tasks` 

Delete tasks that have been added.

Example of usage:

`delete 1`

Expected outcome:

Will delete the task specified with the task number.

Task is deleted and deleted task is shown.

```
OK DONE ALR removed your todo ah:
[T][ ] sell book
Got 3 task in list boy
```


### `Mark`

Marks existing tasks as done with an X.

Example of usage:

`mark 1`

Expected outcome:

Marks task 1 as done with an X.

Task is marked and marked task is shown.

```
Ok boy i mark for you already 
[X] return book 
```

### `Unmark`

unmarks done tasks and removes the X.

Example of usage:

`unmark 1`

Expected outcome:

unmarks task 1.

Tasks is unmarked and unmarked task is shown.

```
Ok boy I unmark for you already 
[ ] return book 
```

### `List`

Command lists out all saved tasks.

Example of usage:

`list`

Expected outcome:

Task is listed out together with information if it has been marked or not.

```
Here are your tasks ah boy:
1. [D][ ] return book (by: Dec 02 2019 18:00)
2. [T][ ] read
3. [T][ ] call mom
```

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

### `Help`

Displays all commands that can be used within the bot

Example of usage:

`!help (each command)`

Expected outcome:

Lists out all commands

```
Here is a list of the commands:
 1.list 
 2.mark 
 3.unmark 
 4.find 
5.todo\event\deadline
 Type these commands together like 
 !help mark
```


### `Find`

Returns task that matches the keyword

Example of usage:

`find book`

Expected outcome:

Returns task that matches the keyword

```
1. [D][ ] return book (by: Dec 02 2019 18:00)
```
