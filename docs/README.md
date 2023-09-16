# AdaBot User Guide

![UI Example](Ui.png)

## About

AdaBot is a **fast chatbot app** designed to help you manage your tasks.
Simply type your commands and AdaBot will handle the rest.

## Features

### User-friendly GUI

As we can see from the image above, AdaBot is user-friendly and intuitive.

### Local Storage

Your interaction with AdaBot will be saved in a task list stored in your local device,
so all your tasks will be safe as long as your device is safe.

### Deadline, Event, Todo

AdaBot handles 3 types of tasks:

- Deadline, which has a deadline date
- Event, which has start and end dates
- Todo, which is not associated with time

## Usage - List of Commands

### ` ` - List all available commands

You're a new user? Don't worry! Simply type nothing and click Enter to see all available commands.

To support fast typers, AdaBot provides an alias for each command.

### `list`/`l` - List all tasks

List all tasks saved on AdaBot.

> `list`

### `deadline`/`d` Add a deadline

Add a deadline to your task list with specified `YYYY-MM-DD` deadline date

> `deadline [description] /by [YYYY-MM-DD]`

Example:

`deadline homework /by 2023-12-31` will add a deadline named **homework** that is due on 31 Dec 2023.

### `event`/`e` Add an event

Add an event to your task list with specified `YYYY-MM-DD` start date and `YYYY-MM-DD` end date

> `event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`

Example:

`event holiday /from 2023-12-01 /to 2023-12-31` will add an event named **holiday** from 1 Dec 2023 to 31 Dec 2023.

### `todo`/`t` Add a todo

Add a todo to your task list without any specified date

> `todo [description]`

Example:

`todo workout` will add a todo task named **workout** to your task list.

### `mark`/`m` Mark your task as done

> `mark [number]`

Example:

`mark 3` will mark **the third task**, i.e., task number 3 in your task list as done.

Outcome:

```
Nice! I've marked this task as done:
 [E][X] holiday (1 Dec 2023 - 31 Dec 2023)
```

### `unmark`/`u` Mark your task as not done

> `unmark [number]`

Example:

`unmark 3` will mark **the third task**, i.e., task number 3 in your task list as not done.

### `find`/`f` Find all tasks with specified keyword

This command will list all tasks that contain the keyword you're looking for.

> `find [keyword]`

Example:

`find exam` will list all tasks in your task list that contain the word "exam" in it. Keyword is case-sensitive.

### `date`/`a` Find all tasks you have on a specific date

> `date [YYYY-MM-DD]`

Example:

`date 2023-12-31` will list all tasks you have on 31 Dec 2023.

Outcome:

```
Here are the tasks on 31 Dec 2023:
1.[E][X] holiday (1 Dec 2023 - 31 Dec 2023)
2.[D][] tP (by: 31 Dec 2023)
```

### `delete`/`del` Delete a task

> `delete [number]`

Example:

`delete 3` will delete **the third task**, i.e., task number 3 in your task list.

### `bye`/`b` Close AdaBot

Terminates the AdaBot session. Note that the window is not automatically closed.

> `bye`

Outcome:

```
Bye. Hope to see you again soon!
AdaBot is terminated. You may close this window.
```

## Command Summary

| Alias                                                 | Command Word | Function                                   |
| ----------------------------------------------------- | ------------ | ------------------------------------------ |
| ` `                                                   | ` `          | List all available commands                |
| `l`                                                   | `list`       | List all tasks                             |
| `d [description] /by [YYYY-MM-DD]`                    | `deadline`   | Add a deadline                             |
| `e [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]` | `event`      | Add an event                               |
| `t [description]`                                     | `todo`       | Add a todo task                            |
| `m [number]`                                          | `mark`       | Mark task as done                          |
| `u [number]`                                          | `unmark`     | Mark task as not done                      |
| `f [keyword]`                                         | `find`       | Find all tasks with specified keyword      |
| `a [YYYY-MM-DD]`                                      | `date`       | Find all tasks you have on a specific date |
| `del [number]`                                        | `delete`     | Delete a task                              |
| `b`                                                   | `bye`        | Close AdaBot                               |

# Get Started

- You can download the jar file [here](https://github.com/nixonwidjaja/ip/releases)
- Make sure you have Java and JRE installed on your device
- Run the jar file
