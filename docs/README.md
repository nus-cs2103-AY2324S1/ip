# Gideon User Guide
Gideon is a Java-based chatbot application designed to help users
manage tasks and interact with a friendly AI assistant. It's a 
versatile tool that allows you to create, update, and organize 
your list of tasks efficiently.
## Table of Contents
1. Getting started
2. Features
   * User-friendly interface
   * Add tasks
   * Delete tasks
   * Edit tasks
   * Mark tasks
   * Find tasks
   * List tasks

### Getting started

#### Prerequisites 
* Java 11: Gideon requires Java 11 or later to run. Make sure you have Java 11 installed
on your computer. You can download it from the official Oracle website or use an 
OpenJDK distribution.

#### Installation
* Download Gideon: You can download the application from my iP repository.

#### Running the program
1. Open a Terminal (PowerShell or Command Prompt): To run Gideon, use the command line.
    * run `java -jar duke.jar`
   
### Features
#### User-friendly interface
Gideon has a pleasant GUI that is interactive and gives you feedback with each command.
It is almost like you are having a normal conversation!

![Screenshot of the UI of Gideon](Ui.png)

#### Add tasks
Gideon allows you to add tasks so that you can keep track of them. Tasks come in three 
different forms:
1. To-do 
2. Deadline
3. Event

#### Delete tasks
Gideon allows you to delete tasks from your list. This is handy when you want to remove
tasks that have already been completed or are no longer relevant.

#### Edit tasks
Gideon's edit feature makes it easy for you to change the requirements of your task down
the road.

#### Mark tasks
You can mark specified tasks in your list as completed, or unmark them if you feel that
you are not quite done with the task.

#### Find tasks
As your list of tasks gets longer, you may find yourself having to sort through countless 
entries. Fortunately, Gideon's find feature shows you the tasks that match your specified
keyword!

#### List tasks
You can use the list tasks feature to view all of your tasks at once.

## Usage

### `todo` - Add a To-do

To add a To-do, type in the following command:
```agsl
todo <description>
```

Example of usage: 

```
todo read book
```



After a To-do has successfully been added, Gideon will display:

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
```

### `deadline` - Add a Deadline

To add a Deadline, type in the following command:
```agsl
deadline <description> /by <due_date>
```
* `<due_date>` should be in the form `yyyy-MM-dd HH:mm`

Example of usage:

```
deadline return book /by 2023-08-06 18:00
```

After a Deadline has successfully been added, Gideon will display:

```
Got it. I've added this task:
[D][ ] return book (by: 6 Aug 2023 6:00 PM)
Now you have 2 tasks in the list.
```

### `event` - Add an Event

To add an Event, type in the following command:
```agsl
event <description> /from <start> /to <end>
```

Example of usage:

```
event project meeting /from 23 Sep 2023 9:00 PM /to 10:30PM
```

After an Event has successfully been added, Gideon will display:

```
Got it. I've added this task:
[E][ ] project meeting (from:23 Sep 2023 9:00 PM to:10:30PM)
Now you have 3 tasks in the list.
```

### `delete` - Delete a task

To delete a task, type in the following command:
```agsl
delete <task_index>
```

Example of usage:

```
delete 1
```

After successfully deleting a task, Gideon will display:

```
Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
```
>[!WARNING] This command cannot be undone, the task will be deleted forever.

### `edit` - Edit a task

To edit a task, type in the following command:
```agsl
edit <task_index> /to <new_description>
```

Example of usage:

```
edit 2 /to presentation rehearsal 
```

After successfully editing a task, Gideon will display:

```
Your task has been updated to:
[E][ ] presentation rehearsal (from:23 Sep 2023 9:00 PM to:10:30PM)
```
>[!NOTE] Once a task has been edited, the old description cannot be retrieved.

### `mark` - Mark a task as done

To mark a task, type in the following command:
```agsl
mark <task_index> 
```

Example of usage:

```
mark 1
```

After marking a task, Gideon will display:

```
Nice! I've marked this task as done:
[D][X] return book (by: 6 Aug 2023 6:00 PM)
```

### `unmark` - Unmark a task

To unmark a task, type in the following command:
```agsl
unmark <task_index> 
```

Example of usage:

```
unmark 1
```

After unmarking a task, Gideon will display:

```
OK, I've marked this task as not done yet:
[D][ ] return book (by: 6 Aug 2023 6:00 PM)
```

### `find` - Find a task

To find a task, type in the following command:
```agsl
find <keyword> 
```

Example of usage:

```
find return 
```

After finding a task that matches the keyword, Gideon will display:

```
Here are the matching tasks in your list:
1. [D][ ] return book (by: 6 Aug 2023 6:00 PM)
```

### `list` - List out the tasks in your list

To list the tasks, type in the following command:
```agsl
list
```

Gideon will display:
```
Here are the tasks in your list:
1. [D][ ] return book (by: 6 Aug 2023 6:00 PM)
2. [E][ ] presentation rehearsal (from:23 Sep 2023 9:00 PM to:10:30PM)
```

If your list is empty:
```agsl
There are no tasks.
Looks like you have a lot of time on your hands!
```

### Contributing
Contributions are welcome! If you'd like to improve the chatbot or report issues,
please create a pull request or submit an issue.