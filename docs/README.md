# User Guide

### ChatGPO
ChatGP0 is a bot that helps you manage your assignments, tasks  
and contacts. It is easy to use and set up.

## Features 

### [Summary](#command-summary)

### Create different types of tasks

1. [Tasks without deadlines: `todo`](#todo)
2. [Tasks with deadlines: `deadline`](#deadline)
3. [Events with duration: `event`](#event)

### Manage tasks

1. [Delete task: `delete`](#delete)
2. [Mark task: `mark`](#mark)
3. [Unmark task: `unmark`](#unmark)

### Track tasks

1. [List of tasks: `list`](#list)
2. [Search tasks: `find`](#find)

### Manage contacts

1. [List of contacts: `contacts`](#contacts)
2. [Add contact: `contact`](#contact)
3. [Remove contact: `remove`](#remove)

## Usage

### `todo`

Creates and stores a task with no deadline.

Example of usage: `todo read book`

Expected outcome: Stores a todo task with a description "read book"

```
    __________________________________________________________
    Got it. I've added this task:
    [T][ ] read book
    Now you have 1 tasks in the list.
    __________________________________________________________
```

### `deadline`

Creates and stores a task with a deadline.

Example of usage: `deadline return book /by 2023-06-01 1800`

Expected outcome: Stores a task with a description "return book" and deadline.

```
    __________________________________________________________
    Got it. I've added this task:
    [D][ ] return book (by: Jun 1 2023 1800)
    Now you have 1 tasks in the list.
    __________________________________________________________
```

### `event`

Creates and stores an event with a duration.

Example of usage: `event party /from 2023-07-01 1500 /to 2023-07-01 2300`

Expected outcome: Stores an event with a description "party" and duration.

```
    __________________________________________________________
    Got it. I've added this task:
    [E][ ] party (from: Jul 1 2023 1500 to: Jul 1 2023 2300)
    Now you have 1 tasks in the list.
    __________________________________________________________
```

### `delete`

Deletes a task from your list of tasks.

Example of usage: `delete 1`

Expected outcome: First task is deleted from the list.

```
    __________________________________________________________
    Noted. I've removed this task:
    [T][ ] read book
    Now you have 2 tasks in the list.
    __________________________________________________________
```

### `mark`

Marks a task as complete.

Example of usage: `mark 1`

Expected outcome: Marks the first task.

```
    __________________________________________________________
    Nice! I've marked this task as done:
    [T][X] read book
    __________________________________________________________
```

### `unmark`

Marks a task as incomplete.

Example of usage: `unmark 1`

Expected outcome: Removes the mark for the first task.

```
    __________________________________________________________
    OK, I've marked this task as not done yet:
    [T][ ] read book
    __________________________________________________________
```

### `list`
Shows the user the list of tasks stored.

Example of usage: `list`

Expected outcome: Shows the list of tasks.

```
    __________________________________________________________
    Here are the tasks in your list:
    1.[T][ ] read book
    2.[D][ ] return book (by: Jun 1 2023 1800)
    3.[E][ ] party (from: Jul 1 2023 1500 to: Jul 1 2023 2300)
    __________________________________________________________
```

### `find`

Searches for a task based on the user's input.

Example of usage: `find book`

Expected outcome: Searches for tasks with descriptions that contain the string "book".

```
    __________________________________________________________
    Here are the matching tasks in your list:
    1.[T][ ] read book
    2.[D][ ] return book
    __________________________________________________________
```

### `contacts`

Show list of contacts that have been stored.

Example of usage: `contacts`

Expected outcome: Shows list of contacts.

```
    __________________________________________________________
    Here are your contacts:
    1.Bob 8562 6755
    2.Timmy 5575 2868
    __________________________________________________________
```

### `contact`

Adds a contact into the stored list of contacts.

Example of usage: `contact Sam 84689057`

Expected outcome: Sam's contact is saved in the list of contacts.

```
    __________________________________________________________
    Got it. I've added this contact:
    Sam 84689057
    Now you have 3 contacts.
    __________________________________________________________
```

### `remove`

Removes a contact from the stored list of contacts.

Example of usage: `remove 1`

Expected outcome: Removes the first contact in the contact list.

```
    __________________________________________________________
    Noted. I've removed this contact:
    Bob 8562 6755
    Now you have 1 contact.
    __________________________________________________________
```

## Command summary

| Action   | Format                                                      |
|----------|-------------------------------------------------------------|
| todo     | todo description (e.g. todo read)                           |
| deadline | deadline description /by yyyy-MM-dd HHmm                    |
| event    | event description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm |
| delete   | delete task number (e.g. delete 1)                          |
| mark     | mark task number (e.g. mark 1)                              |
| unmark   | unmark task number (e.g. unmark 1)                          |
| list     | list                                                        |
| find     | find keyword (e.g. find read)                               |
| contacts | contacts                                                    |
| contact  | contact details (e.g. contact Ryan 89064587)                |
| remove   | remove contact index (e.g. remove 1)                        |