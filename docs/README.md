# SillyBot - User Guide

> _"The real risk is doing nothing." - Denis
Waitley ([source](https://quotefancy.com/quote/793908/Denis-Waitley-The-real-risk-is-doing-nothing))_

## Introduction

SillyBot is a fun and easy-to-use todo list manager designed to help you organize your tasks, deadlines, and events.
You can quickly find the total tasks in your list and mark them as done on the go! The best part:

- text-based
- easy to learn
- ~~FAST~~ **SUPER FAST** to use

## Features

### Task Management

SillyBot helps you manage your tasks.

You can do a lot with them, such as:

- add
- delete
- mark
- unmark
- find

### Task Types

SillyBot supports 3 types of tasks:

- todo
- deadline
- and event.

### Task Storage

SillyBot stores your tasks in a local file. You can save and load your tasks from this file.

### User Guidance

SillyBot comes with a user guide to help you get started. All you need to do is to type `help` and SillyBot will guide
you through the rest along with examples.

## Usage

### `help` - Show help

Shows a message explaining how to use SillyBot.

Example of usage: `help`

### `todo` - Add todo

Adds a todo task containing the given description.

Example of usage: `todo <DESCRIPTION>`

Expected outcome:

```
For some reason I believe you are gonna do that!
[T][ ] read book
Now you have 1 tasks in the list.
```

### `deadline` - Add deadline

Adds a deadline task containing the given description and date.

Example of usage: `deadline <DESCRIPTION> /by <DATE IN YYYY-MM-DD>`

Expected outcome:

```
For some reason I believe you are gonna do that!
[D][ ] return book (by: Sep 17 2021)
Now you have 2 tasks in the list.
```

### `event` - Add event

Adds an event task containing the given description and the event duration.

Example of usage: `event <DESCRIPTION> /from <DATE IN YYYY-MM-DD> /to <DATE IN YYYY-MM-DD>`

Expected outcome:

```
For some reason I believe you are gonna do that!
[E][ ] project meeting (at: Sep 16 2021 to Sep 17 2021)
Now you have 3 tasks in the list.
```

### `delete` - Delete task

Deletes the task at specified index.

Example of usage: `delete <INDEX>`

Expected outcome:

```
HAhahah! You couldn't do it! Could ya?
[E][ ] project meeting (at: Sep 16 2021 to Sep 17 2021)
Now you have 2 tasks in the list.
```

### `mark` - Mark task as done

Marks the specified index task as done.

Example of usage: `mark <INDEX>`

Expected outcome:

```
Whoa... are you kidding me? You did that!?
[T][X] read book
```

### `unmark` - Unmark task as done

Unmarks the specified index task as done in SillyBot.

Example of usage: `unmark <INDEX>`

Expected outcome:

```
HAHHAA! I knew it! You won't be able to!
[T][ ] read book
```

### `list` - List tasks

Lists all tasks currently present in SillyBot.

Example of usage: `list`

Expected outcome:

```
This is what you've been procastinating about...
1. [T][ ] read book
2. [D][ ] return book (by: Sep 17 2021)
```

### `find` - Find tasks

Finds all tasks that contain the given keyword.

Example of usage: `find <KEYWORD>`

Expected outcome:

```
you actually have this:
1. [T][ ] read book
2. [D][ ] return book (by: Sep 17 2021)
```

### `bye` - Exit SillyBot

Exits SillyBot.

Example of usage: `bye`

Expected outcome:

```
See ya! I know you are gonna procrastinate again!
```

### `exit` - Exits the console

Close the console window.

Example of usage: `exit`

## How to use

1. download it from [here](https://github.com/suryanshkushwaha/ip).
2. double-click on the .jar file.
3. for new users, type `help` to get started.
4. let it manage your tasks for you 😉

And the BEST PART, it is **FREE**

If you Java programmer, you can use it to practice Java too. Here's the main method:

```java
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
```