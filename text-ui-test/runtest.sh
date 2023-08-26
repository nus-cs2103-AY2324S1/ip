#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# set up the tasks.txt file
if [ -e "./data/tasks.txt" ]
then
  echo "E|F|i love cs|now|forever
T|T|complete ip week3
D|T|complete cs2100 labs|monday" > ./data/tasks.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp \
../src/main/java:\
../src/main/java/primitives:\
../src/main/java/actions:\
../src/main/java/exceptions:\
../src/main/java/exceptions/arguments:\
../src/main/java/exceptions/storage:\
../src/main/java/parsers:\
../src/main/java/storage:\
../src/main/java/tasks \
-Xlint:none -d ../bin \
../src/main/java/*.java \
../src/main/java/primitivies/*.java \
../src/main/java/actions/*.java \
../src/main/java/exceptions/*.java \
../src/main/java/exceptions/arguments/*.java \
../src/main/java/exceptions/storage/*.java \
../src/main/java/parsers/*.java \
../src/main/java/storage/*.java \
../src/main/java/tasks/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin main.java.Juke < input.txt > ACTUAL.TXT

# compare the output to the expected output, ignoring ending whitespaces, since the output generates them but the
# expected file erases them
diff ACTUAL.TXT EXPECTED.TXT -b --ignore-blank-lines
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi