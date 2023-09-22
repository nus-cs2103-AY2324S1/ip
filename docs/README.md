# BouncyBob
# User Guide
Hello ball lovers! Welcome to BouncyBob's user guide! BouncyBob is a task management application that helps you keep track of your tasks.  
In this guide, we will walk you through the step by step process of how to get BouncyBob up and running and start bouncing away with your tasks!
By the end of this guide, you would have:
- Installed and set up BouncyBob
- Be familiar with the purpose of BouncyBob and how it can help you
- Be able to use BouncyBob to manage your tasks

Now let's bounce right in!
## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Add a Todo task](#add-a-todo-task)
  - [Add a Deadline task](#add-a-deadline-task)
  - [Add an Event task](#add-an-event-task)
  - [Mark and unmark a task as done](#mark-and-unmark-a-task-as-done)
  - [Delete a task](#delete-a-task)
  - [Add notes to a task](#add-notes-to-a-task)
  - [Find tasks](#find-tasks)
  - [List all tasks](#list-all-tasks)
  - [Conclusion](#conclusion)
- [Glossary](#glossary)

## Quick Start
### Setting up
Before you can start using BouncyBob, you will need to have Java 11 installed on your computer. Here are the different guides to installing Java 11 on different operating systems:
- [Windows](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA)
- [MacOS](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)
- [Linux](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-linux-platforms.html#GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8)

You might also need to install JavaFX 11. You can find the installation guide [here](https://openjfx.io/openjfx-docs/#install-javafx).
> **ℹ️ Note:**
> You will see the word "JDK" in the installation guides. JDK stands for "Java Development Kit" and you can treat is as the same as Java.

Once you have Java 11 installed, you can download the latest version of BouncyBob [here]()
### Running BouncyBob
To run BouncyBob, you will need to open a [terminal](https://support.apple.com/en-sg/guide/terminal/apd5265185d-f365-44cb-8b09-71a064a42125/mac)(Mac) or [command prompt](https://support.kaspersky.com/common/windows/14637#block0)(Windows) and navigate to the directory where you have downloaded BouncyBob.
After that, type `cd` followed by the directory path to the folder where you have downloaded BouncyBob and press `Enter`. For example, if you have downloaded BouncyBob to your desktop, you can type the following command:  
```
cd ~/Desktop
```
Then run the following command:
```
java -jar BouncyBob.jar
```
If you have done everything correctly, you should see the BouncyBob application open up in a few seconds!
## Features 
Now that BouncyBob is up and running, you must be very excited to start using it! Here are some of the features that BouncyBob has to offer.
### General
Everything that you do in BouncyBob can be done in the single window! The top half of the window is where you can see all your tasks nicely listed out. Below it, there are some buttons and a text field where you can interact with BouncyBob! Don't worry too much about it, we will go through each of them in detail later on.
![UI](Ui.png)
### Add a Todo task
A todo task is the simplest form of task.
1. Toggle the `Task Type Selector` and select `TODO`.
2. Type the name of your task in the text field.
3. Click the `Add` button.
If you have done everything correctly, you should see your todo task appear in the list of tasks above!

#### Example
- `todo ip`
- `todo buy groceries`
- `todo go for a run`

### Add a Deadline task
A deadline task is a like a todo task, with a deadline.
1. Toggle the `Task Type Selector` and select `DEADLINE`.
2. Type `NAME /by DATE` in the text field.
    - `NAME` is the name of your task
    - `DATE` is the deadline of your task in the format `YYYY-MM-DD HHMM`
3. Click the `Add` button.
4. If you have done everything correctly, you should see your deadline task appear in the list of tasks above!

#### Examples
- `deadline ip /by 2023-09-22 2359`
- `deadline project proposal /by 2023-10-10 1200`

> **ℹ️ Note:**
> Take note of the format!

#### Invalid Examples
- `deadline ip /by 2023-09-22 23:59` (wrong time format - should be `2359`)
- `deadline ip /by 2023:09:22 23:59` (wrong date format - should be `YYYY-MM-DD`)
- `deadline /by 2023-09-22 23:59:00` (missing task name)

### Add an Event task
An event task is a like a todo task, with a start date and end date.
1. Toggle the `Task Type Selector` and select `EVENT`.
2. Type `NAME /from START_DATE /to END_DATE` in the text field.
    - `NAME` is the name of your task
    - `START_DATE` is the start date of your task in the format `YYYY-MM-DD HHMM`
    - `END_DATE` is the end date of your task in the format `YYYY-MM-DD HHMM`
3. Click the `Add` button.
4. If you have done everything correctly, you should see your event task appear in the list of tasks above!

#### Examples
- `career fair /from 2023-09-22 2359 /to 2023-09-23 2359`
- `ball handling (basketball dribbling) competition /from 2023-10-10 1200 /to 2023-10-10 1400`

#### Invalid Examples
- `career fair /from 2023-09-22 23:59 /to 2023-09-23 23:59` (wrong time format - should be `2359`)
- `career fair /from 2023:09:22 23:59 /to 2023:09:23 23:59` (wrong date format - should be `YYYY-MM-DD`)

### Mark and unmark a task as done
Marking a task as done is easy! It works the same regardless of whether it is a todo, deadline or event task.
1. A task will be unmarked by default when you add it. An unmarked task will have a ❌ symbol at the left end of the task.
2. Click on the `Toggle status` button zt the right end of the task
3. If you have done everything correctly, you should see the ❌ symbol change to a ✅ symbol!
4. To unmark a task, simply click on the `Toggle status` button again.
5. If you have done everything correctly, you should see the ✅ symbol change back to a ❌ symbol!

#### Delete a task
Deleting a task is easy! It works the same regardless of whether it is a todo, deadline or event task.
1. Click on the task that you want to delete. The selected task should be highlighted in blue.
2. Click the `Delete` button.
3. If you have done everything correctly, you should see your task disappear from the list of tasks above!

#### Add notes to a task
Adding notes to a task is easy! It works the same regardless of whether it is a todo, deadline or event task.
1. Type `note INDEX YOUR_NOTE` in the text field.
    - `INDEX` is the position number (starts from 0) of the task that you want to add notes to
    - `YOUR_NOTE` is the note that you want to add to the task
2. Click the `Add` button.
3. If you have done everything correctly, you should see your note appear in the list of notes for the task!

#### Example
Suppose you have the following task at index 0:
```
❌ do laundry
```
And you want to add the note `remember to separate the whites and the coloureds` to it. You can type the following command:
```
note 0 remember to separate the whites and the coloureds
```
And you should see the following:
```
❌ do laundry  | Note: remember to separate the whites and the coloureds
```

> **ℹ️ Note:**
> Make sure the position number is within bound, and it starts counting from 0 top down!

#### Find tasks
Filter the list of tasks by searching for a keyword.
1. Type `find KEYWORD` in the text field.
    - `KEYWORD` is the keyword that you want to search for
2. Click the `Add` button.
3. If you have done everything correctly, you should see the list of tasks filtered by the keyword!

#### Example
Suppose you have the following tasks:
```
❌ do laundry
❌ do math homework
❌ do physics homework
```
And you want to find all tasks that contain the keyword `homework`. You can type the following command:
```
find homework
```
And you should see the following:
```
❌ do math homework
❌ do physics homework
```

>> **ℹ️ Note:**
> The keyword is case-sensitive! Keyword has to be an exact match to the task name. A valid keyword is a substring of the task name separated by space.
> If you add or delete after finding, the application will automatically clear the filter and show you the full list of tasks.

### List all tasks
By default, you would already be able to see all tasks. This is useful if you have filtered the list of tasks and want to see the full list again.
1. Type `list` in the text field.
2. Click the `Add` button.
3. If you have done everything correctly, you should see the full list of tasks!

#### Example
Suppose you have the following tasks as filtered from before:
```
❌ do math homework
❌ do physics homework
```
And you want to see the full list of tasks again. You can type the following command:
```
list
```
And you should see the following:
```
❌ do laundry
❌ do math homework
❌ do physics homework
```

## Conclusion
That's it! You are now ready to bounce!
Here is a summary of the commands that you have learnt:  

| Task Type Selector | Command | Description                 |
|--------------------|---------|-----------------------------|
| `TODO`             | `NAME`  | Adds a todo task with `NAME` |
| `DEADLINE`         | `NAME /by DATE` | Adds a deadline task with `NAME` and `DATE` |
| `EVENT`            | `NAME /from START_DATE /to END_DATE` | Adds an event task with `NAME`, `START_DATE` and `END_DATE` |
| | `note INDEX YOUR_NOTE` | Adds a note `YOUR_NOTE` to the task at position `INDEX` |
| | `find KEYWORD` | Filters the list of tasks by `KEYWORD` |
| | `list` | Shows the full list of tasks |

- For delete, click on the task and click the `Delete` button
- For mark and unmark, click on the task and click the `Toggle status` button


## Glossary
- **Task Type Selector** - A dropdown menu that allows you to select the type of task that you want to add, with three options: `TODO`, `DEADLINE` and `EVENT`.
- **Task** - A task is a unit of work that you want to keep track of. There are three types of tasks: `TODO`, `DEADLINE` and `EVENT`.