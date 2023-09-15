# Smolbrain Chatbot: User Guide

Smolbrain is a **chatbot for tracking of tasks, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). With a strong mastery of typing commands, tasks management can be much more efficient than conventional GUI apps.

Screenshot of Smolbrain Chatbot:
 ![Screenshot](Ui.png) 

## Quick start
1. Ensure that Java 11 or above installed in your Computer.
2. Download the latest addressbook.jar from here.
3. Copy the file to the folder you want to use as the _home folder_ for your Smolbrain Chatbot.
4. To run the application, either:
   * Double-click the jar file to open.
   * Or open a command terminal, cd into the folder you put the jar file in, and use the java -jar smolbrain.jar command to run the application.
5. Type the command in the command box and press Enter or click the SEND button to execute it. e.g. typing `list` and pressing Enter will list out all the current tasks saved.
   Some example commands you can try:
   * `list` : Lists all current saved tasks.
   * `todo NewTodo` : Adds a new task of type **Todo** with the title `NewTodo` to the chatbot.
   * `delete 3` : Deletes the 3rd task shown in the current task list.
   * `mark 1` : Marks the 3rd task shown in the current task list.
   * `bye` : Exits the app.
6. Refer to the Features below for details of each command.

## Key Features 

### Creation of tasks

There are 3 different type of tasks available: **Todo**, **Deadline** and **Event**.
* **Todo** : A task with only a description.
* **Deadline** : A task with a description with the associated due date and time.
* **Event** : A task with a description with the starting and ending date and times.

### Marking of tasks

Tasks can be marked when completed, or unmarked when required, allowing to track which tasks are done.

### Prioritising tasks

Tasks can be set with a priority level to indicate how urgent they are.

## Features List

> ℹ️ Notes about the command format:
> 
> * Words in `[UPPER_CASE]` are the parameters to be supplied by the user.
> * Task descriptions (indicated as `[DESCRIPTION]`), can have spaces in between them.
>   * Typing `todo This is a todo` will create a new **Todo** task with the description `This is a todo`.
> * Date and time must be given in this **exact format**: `DD/MM/YYYY HHmm`, otherwise there will be an error parsing.
>   * Single digit days or months should have 2 numbers such as `03`, `09`.
>   * Time must not have semicolon characters `:` and should be in 24-hour format.

### Adding a Todo: `todo`

Adds a new `todo` task to the chatbot. 

Format: `todo [DESCRIPTION]`

* Description parameter can have multiple spaces separating words. 
* By default, the todo is unmarked and has a priority level of 0.

Examples: 
* `todo Housework` : Creates a new **todo** task with the description `Housework`.
* `todo Create a new recipe` : Creates a new **todo** task with the description `Create a new recipe`.

Expected outcome:

Description of the outcome.

```
expected output
```
