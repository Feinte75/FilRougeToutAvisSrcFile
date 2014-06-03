   FilRougeToutAvis
=======================
Projet INF 112 J.B / G.F

Ce programme est un réseau social ecrit en Java. 
Les fonctionnalités proposées sont : 
- L'ajout de membres au réseau social
- L'ajout de films ou de livres
- La possibilité pour les membres de commenter les films et livres et de leurs attribuer une note.
- Les membres peuvent ensuite donner leurs opinions sur les commentaires en leurs attribuant une note
- Ces notes determineront le karma du membre
	
Instructions d'installation

Grace à un terminal, se placer sur le repertoire des sources
Compiler chaques packages avec :
javac ./avis/*.java
javac ./exception/*.java
javac ./ihm/*.java

Pour les tests selon le systèmes d'exploitation utilisé pour compiler il est possible qu'il faille utiliser un encodage spécifique :
javac -encoding UTF-8 ./test/*.java
ou
javac -encoding ISO-8859-1 ./test/*.java

Une fois les classes compilées, il est possible de lancer une interface graphique en executant la classe IHM avec :
java ./avis.IHM

Les tests se trouvent dans le packages test et peuvent être executés indépendamment :
java ./test.TestsAddMember par exemple

ou bien en executant la classe TestSocialNetwork, tout les tests seront executés les uns à la suite des autres avec un bilan à la fin :
java ./test.TestSocialNetwork

Il est possible de générer la javadoc en tapant la commande :
javadoc -d repertoire *.java
