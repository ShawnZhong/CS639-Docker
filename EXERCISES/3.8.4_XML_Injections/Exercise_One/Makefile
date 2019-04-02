all: xmlParser.java largeFile.xml
	@echo compile xmlParser.java
	javac xmlParser.java

clean: 
	@echo removing parsers
	rm *.class
	rm largeFile
	rm largeFile.xml

largeFile.xml:
	gcc largeFile.c -o largeFile && ./largeFile
