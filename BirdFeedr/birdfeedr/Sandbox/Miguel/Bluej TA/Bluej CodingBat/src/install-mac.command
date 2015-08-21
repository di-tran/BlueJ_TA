#!/bin/bash
VMLEVEL=1.6
javac *.java -source 1.6 -target 1.6 -cp .:$(find +libs  /Applications/BlueJ  -iname "*.jar" | xargs echo | sed "s/jar \(.\)/jar:\1/g")
if [ $? != 0 ]; then exit; fi


#Set local variables.
projectFolder=$PWD
outJar=$PWD/CodingPractice.jar
tempMFFile=Manifest.mf
mainClass=Main.class
libPath=+libs
extPath=$HOME/Library/Preferences/org.bluej/extensions
JDK="$(ls -d /Library/Java/JavaVirtualMachines/jdk1.8*/Contents/Home/jre/lib/)"

#Create manifest.
echo Main-Class: Main> $tempMFFile
echo Class-Path: $(find +libs  -iname "*.jar" -print | xargs -I{} basename '{}' | xargs)>> $tempMFFile

#Make jar
ls -1 ../Exercises | sed "s|^|/Exercises/|g"> "exercises.lst"
jar -cfm  "$outJar" $tempMFFile *.class *.fxml Runner/*.class "exercises.lst"
pushd ../
jar -uf  "$outJar" Exercises/*
popd

#Remove manifest.
rm $tempMFFile
#rm "$projectFolder/exercises.lst"

#Copy the jars into the extensions folder.
mkdir -p "$extPath"
cp "$outJar" "$extPath/"
#XXX don't copy bluejext.jar

open /Applications/BlueJ/BlueJ.app/
