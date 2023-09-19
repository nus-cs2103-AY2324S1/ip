# User Guide

Jeoe is a desktop chat app for adding tasks and sorting them lexicographically
via a graphical interface.
- [Quick start](#quick-start-anchor)
- [Features](#features-anchor)
  - [Adding a todo task: `todo`](#feature-todo-anchor)
  - [Adding a deadline task: `deadline`](#feature-deadline-anchor)
  - [Adding an event task: `event`](#feature-event-anchor)
  - [Listing all tasks: `list`](#feature-list-anchor)
  - [Marking a task as done: `mark`](#feature-mark-anchor)
  - [Un-marking a task: `unmark`](#feature-unmark-anchor)
  - [Deleting a task: `delete`](#feature-delete-anchor)
  - [Finding a task: `find`](#feature-find-anchor)
  - [Sorting the tasks: `sort`](#feature-sort-anchor)
  - [Ending the Jeoe chat bot: `bye`](#feature-bye-anchor)
  - [Saving the data](#feature-save-anchor)
- [FAQ](#faq-anchor)
- [Known issues](#known-issues-anchor)
- [Command summary](#command-summary-anchor)


---
## Quick start <a id="quick-start-anchor"></a>
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `Jeoe.jar` from [here](https://github.com/wasjoe1/ip/releases/tag/v0.1).

3. Copy the file to the folder you want to use as the home folder for your Jeoe chatbot.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Jeoe.jar` command to run the application.
A GUI similar to the below should appear in a few seconds.

5. Type the command in the chat box and click send to execute it. e.g. typing list and
pressing enter will list out the current list of tasks you have in the list.
Some example commands you can try:

   - `list` : list all tasks. 
   - `todo task 1 test 1` : Adds task that is called task 1 test 1.
   - `delete 1` : Deletes the 1st task in the current list.
   - `bye` : Exits the program.

Refer to the Features below for details of each command.

---
## Features <a id="features-anchor"></a>

>:information_source:ℹ️ Notes about the command format:
>- words in upper `UPPER_CASE` are the parameters to be supplied by the user. 
e.g. in delete NUMBER, NUMBER is a parameter which can be used as delete 1.
>- Extraneous parameters for commands (such as list, bye, todo) are not allowed
e.g. if the command specifies help 123, it will throw an error.
>- If you are using a PDF version of this document, be careful when copying and
pasting commands that span multiple lines as space characters surrounding line-breaks
>may be omitted when copied over to the application.


#### <span id="feature-todo-anchor" style="color: orange;"> Adding a todo task: `todo` </span>
Adds an event task to the task list. 

Format: event [TODO] 
Tip: An empty todo is still a todo
Examples:
- `todo task 1`
- `todo I need to brush my teetch`

#### <span id="feature-deadline-anchor" style="color: orange;">Adding a deadline task: `deadline`</span>
Adds a todo task to the task list.

Format: todo [TODO]
Tip: An empty todo is still a todo
Examples:
- `todo task 1`
- `todo I need to brush my teetch`

#### <span id="feature-event-anchor" style="color: orange;">Adding an event task: `event`</span>
Adds a todo task to the task list.

Format: todo [TODO]
Tip: An empty todo is still a todo
Examples:
- `todo task 1`
- `todo I need to brush my teetch`

#### <span id="feature-list-anchor" style="color: orange;">Listing all tasks: `list`</span>
Shows your current list of tasks.
Format: `list`

#### <span id="feature-mark-anchor" style="color: orange;">Marking a task as done: `mark`</span>
marks the task you specified as done.
Format

#### <span id="feature-unmark-anchor" style="color: orange;">Un-marking a task: `unmark`</span>

#### <span id="feature-delete-anchor" style="color: orange;">Deleting a task: `delete`</span>

#### <span id="feature-find-anchor" style="color: orange;">Finding a task: `find`</span>
Finds tasks whose description contain any of the given words.
Format: find [KEYWORDS]
- The search is case-sensitive. e.g `hans` will match `Hans`
- The order of the keywords does matter. e.g. `Hans Bo` will NOT match `Bo Hans`
- Only full words will be matched e.g. Han will not match Hans
- Persons matching at least one keyword will be NOT returned (i.e. OR search). e.g. `Hans Bo` will NOT return `Hans Gruber`, `Bo Yang`

Examples:
- find `task` returns `task 1` and `task 2`
- find returns 

#### <span id="feature-sort-anchor" style="color: orange;">Sorting the tasks: `sort`</span>
#### <span id="feature-bye-anchor" style="color: orange;">Ending the Jeoe chat bot: `bye`</span>
Exits the program.
Format: `bye`

#### <span id="feature-save-anchor" style="color: orange;">Saving the data</span>
Jeoe chat bot data is saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.


---
## FAQ <a id="faq-anchor"></a>
Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data
file it creates with the file that contains the data of your previous 
Jeoe chatbot home folder.

---
## Known issues <a id="known-issues-anchor"></a>
The background color is `#0969DA` for light mode and `#000000` for dark mode.

---
## Command summary <a id="command-summary-anchor"></a>