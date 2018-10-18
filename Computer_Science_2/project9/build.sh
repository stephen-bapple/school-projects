rm *.jar
javac *.java
jar cfm CarProgram.jar Manifest.mf *.class
rm *.class

