runTests: DataWranglerTest.class  BookLoader.class Book.class
	java DataWranglerTest

DataWranglerTest.class: DataWranglerTest.java
	javac DataWranglerTest.java 

BookLoader.class: BookLoader.java 
	javac BookLoader.java

Book.class: Book.java
	javac Book.java

clean:
	rm *.class
