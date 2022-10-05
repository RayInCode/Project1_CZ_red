default: runTests

compileInterface: IBook.java IBookMapperBackend.java IterableMapADT.java MapADT.java
	javac IBook.java
	javac IBookMapperBackend.java
	javac IterableMapADT.java
	javac MapADT.java

compileBook: IBook.java BookBD.java
	javac IBook.java
	javac BookBD.java

compileHashtable:  KVPair.java MapADT.java BookIteratorBD.java HashtableMapBD.java HashtableMap.java
	javac KVPair.java
	javac MapADT.java
	javac BookIteratorBD.java
	javac HashtableMap.java
	javac HashtableMapBD.java

BookMapperBackend.class: BookMapperBackend.java compileInterface compileBook compileHashtable
	javac BookMapperBackend.java

BackendDeveloperTest.class: BackendDeveloperTest.java
	javac BackendDeveloperTest.java

runTests: BookMapperBackend.class BackendDeveloperTest.class
	java BackendDeveloperTest

clean:
	rm *.class