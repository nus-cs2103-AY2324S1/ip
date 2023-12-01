# Duchess

Duchess is a CLI or GUI app that allows you to keep track of your To-Dos, Deadlines, and Events. You know, like that project documentation you were supposed to finish yesterday, but you got distracted watching cat videos, and promptly forgot. Or, to remind you about the fact that you have an important meeting tomorrow, but you can't quite remember when it begins or ends, so you don't know if you need to reschedule lunch. Is this anything like you? If so, Duchess has got you covered!

tl;dr Feature List:
- Keep track of ToDos!
  - These are Tasks with a name attached.
- Keep track of Deadlines!
  - These are also Tasks with a name and some kind of due-by date attached.
- Keep track of Events!
  - These are also Tasks with a name, but with some start time and end time.
- Mark tasks as completed!
- Tag tasks!
- Search for tasks!
- GUI Application included!
  - This is basically the CLI app, but you can see Duchess' cute face!
  - The commands and output are still in CLI format!

## Relevant Lore and Character Motivations
<details>
<summary>Lore</summary>

(Note: the authenticity and details of this section is up for your own interpretation.)

Once upon a time, there existed a developer. This developer was doing what all developers do, which is hole herself up in her room and watch YouTube videos, instead of doing more important stuff like doing work. But then, she realized something that shook her *to the core*.

"Wait," she muttered to herself. "I did submit that assignment due yesterday, right?"

Filled with a growing anxiety in her heart, she pulled up her laptop and accessed her most-frequently-accessed-but-somehow-not-her-favourite-(far-from-it-actually) website, Pandas. It was the learning management system used by her school to keep track of her own homework.

Because she didn't have any other app to do that for her, of course.

"Dang it," she cursed, while still somehow managing to maintain being PG-13. "I didn't."

The glowing red text "LATE" hovered over the homework assignment submission page.

Now, dear reader. What do you think this developer did? Did she:
- Admonish herself, and then remember to put a reminder for all homework assignments in the future, so she doesn't miss yet another deadline?
- Hurriedly type out an email to the professor, explaining the reasons for her late submission in the hopes that he'd take sympathy on her?
- Do... nothing?

Whichever you chose, dear reader, it wasn't looking good for our protagonist.

Sighing, she took a look at her assignments for the next week and... oh? What's this?

"I have to... add features to this incomplete project for a task managing application?" she asked, stifling a yawn. "You know what, maybe I *could* use this."

And, thus, Duchess was born.
</details>

## Features 
The following are a list of features that our beautiful Duchess has to offer:

### To-Dos!

Duchess allows keeping track of ToDos. To add a ToDo to keep track of, use the following command.

```bash
$> todo [name]
```

This will add a ToDo with that specified name into Duchess. Note that names only allow for alphanumeric characters and underscores and spaces.

### Deadlines!

Deadlines are Tasks, or ToDos, but with a specified deadline. This deadline has to be a legal date. To add a Deadline to Duchess, use the following command.

```bash
$> deadline [name] /by [deadline]
```

Note that `deadline` has to be a String in some format that Duchess accepts. (Currently, only one format is accepted, `ISO_LOCAL_DATE`. This the format of `yyyy-MM-dd`. For instance, "2023-12-30").

### Events!

Events are Tasks, or ToDos, but with a start time and an end time. To add an Event to Duchess, use the following command.

```bash
$> event [name] /from [startTime] /to [endTime]
```

For now, Duchess does **no validation** for `startTime` or `endTime`, unlike date validation for Deadlines. Internally, they are 
represented as a `String`. This means you can type in whatever you want, as long as it only has alphanumeric characters, underscores or spaces.

### List Tasks!

To list all currently stored tasks, use the following command:
```bash
$> list
```

For Linux users, you may alternatively use the alias `ls`, which does the same thing.

### Remove Tasks!

To remove a specific task from the list of tasks, use the following command:

```bash
$> delete [index of task]
```

The index of the task is displayed next to the task when `list` is called. 

Another alias for this command is `rm`, which does the same thing.

### Search for Tasks!
Want to find a particular task in your endless list of tasks? Fret no further, for we have the glorious search command:
```bash
$> find [name]
```
This finds any tasks whose name contains the search string. For instance, finding "hello" will match both "hello world" and "othello with friends". Spaces are considered as characters.


### Mark Tasks' Completion Status!

Tasks (a ToDo, Deadline or Event) are either *complete* or *incomplete*. By default, when you add a Task, it is automatically *incomplete*. To mark a task as *complete*, use:

```bash
$> mark [index]
```

Once again, the index of the task is displayed next to the task when `list` is called.

To unmark a task (set it back to *incomplete*), use:

```bash
$> unmark [index]
```

### Add Tags to a Task!
You can add tags to tasks! This will add a small little #tag next to the task in question.

```bash
$> tag add [index] [tag name] [tag name 2] [tag name 3]
```
You can add as many tags as you want in one command by separating them with spaces. This also means that a tag cannot contain a space. If you need multiple word tags, the dash (`-`) character is supported for tag names. All other constraints (alphanumeric or underscores) that apply to Task names also apply for tag names. Placing a hash (`#`) character before the tag name is optional, and does not affect anything.

An alias for adding a tag is `tag a`, which does the same thing.

If you need to remove a tag, you can instead use the corresponding command:

```bash
$> tag delete [index] [tag name] [tag name 2] [tag name 3]
```
As with adding tags, you can delete as many tags as you want in a single command, separated with spaces. Note that deleting tag names is case-sensitive and must match the corresponding tag exactly. If multiple of the same tag is already attached to one task, all instances of that tag are deleted. As with adding tags, placing a hash (`#`) character before the tag name is purely optional.

### Exiting
Just like VIM, Duchess can be a mystery to exit. Thankfully, it's very easy! All you need to do is wave goodbye to Duchess.

```bash
$> bye
```

Alternatively, you could also just tell her `exit`, which is an alias that does the same thing. But that's kind of rude. So `bye` is the preferred method.

## Setup
First, clone this repo;

```bash
$> git clone git@github.com:lawruixi/ip.git
$> cd ip
```

To run the CLI Duchess, I've conveniently written a script called `run.sh` that will compile and run Duchess instantly;

```bash
$> ./run.sh
```

If you would like to instead run the GUI Duchess, you can use the provided `build.gradle` build and run it;
```bash
$> ./gradlew clean run
```

## Sample Usage

### Creating some Tasks, and running operations on them
After running Duchess via one of the above methods, let's see Duchess in action. Currently, there should be no tasks yet added. Let's add a few;

```bash
$> deadline Submit Project Proposal for Review /by 2024-01-30
[D]: ... (Duchess output omitted)
$> deadline Upgrade Website to new Technology /by 2024-01-30
[D]: ...
$> deadline Feed the Dog /by 2024-01-30
[D]: ...
$> ls
```

This should display a list of all the deadlines we have added so far. 

Let's mark a few tasks as completed. (Productive today, aren't we?)

```bash
$> mark 1
[D]: ...
$> mark 3
[D]: ...
$> ls
[D]: ...
```

We should be able to see that Duchess has put a cute little indicator next to the task to show that it has been completed.

Let's get rid of our backlog now.

```bash
$> rm 3 
$> rm 2
$> rm 1
$> ls
```

We should see that after clearing our list of tasks, there are indeed no more tasks left on the list.

Let's leave Duchess.

```bash
$> bye
```

Duchess tearfully waves goodbye to us as she exits execution.

## Duchess Save Data

Duchess data is saved in a file named `data.duchess`. Edit this file at your own risk. This file must be placed in the same directory where the program is run; most of the time, this will be in the root directory.

## TODO:
In the future:
- Duchess config file (options like aliases)
- Syntax highlighting
- Command suggestions
- Prettier GUI

## Acknowledgements
This developer would like to thank and acknowledge the NUS CS2103T team for giving me the opportunity to do this project in the first place.

<details>
<summary> Detailed Acknowledgements </summary>
  
In no particular order:
---
- NUS CS2103T teaching team
- SE-EDU for providing tips and code for JavaFX, even if I knew them already; it was a great refresher.
- GitHub for serving as the remote git repository for this project.
---
- **No IDEs were used in the making of this project, from start to finish.**
- Vim, for being my trusty text editor and allowing me to work 100x faster than I would via an IDE.
- Git, the awesome version control software.
- Gradle, for helping automate some minor things.
- Bash, for the script that automated other things, and for being easier to use than Gradle.
- SceneBuilder, for helping me visualize the end product of the GUI, even if it was janky to use.
- Regex, for being my String analyser of choice.
- Regex101, for being my Regex analyser of choice.
- The Java language that this was built on, including the JDK, JRE and JavaFX packages.
- paint.net, where I drew the user's and Duchess's beautiful face.
---
- You, for reading this, and for trying out my small little project.
</details>



That's all from me for now. What's that? I should plug myself shamelessly here? Don't mind if I--
