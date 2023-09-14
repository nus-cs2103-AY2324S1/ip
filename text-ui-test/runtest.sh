#!/usr/bin/env bash

# navigate to the same directory the script is in
cd "$(dirname "$0")"

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

# clear all existing jar files and data files
rm -r ../build/libs/todoify*.jar 2> /dev/null
rm -r ./todoifydata 2> /dev/null

# return to upper dir for compilation
cd ..

# compile a new full jar file
if ! ./gradlew shadowJar
then
    exit 1
fi

# go back into curr dir
cd - >> /dev/null

echo

# run the program with en-US locale (for consistency, as the program uses locale-dependent formatting)
# this also feed commands from input.txt file and redirect the output to the ACTUAL.TXT
echo "Executing text UI tests..."
java -Duser.language=en -Duser.country=US -classpath ../bin -jar ../build/libs/todoify*.jar --text-ui < input.txt > ACTUAL.TXT

# delete the data output file
rm -r ./todoifydata

# compare the output to the expected output
diff --strip-trailing-cr ACTUAL.TXT EXPECTED.TXT

if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi

