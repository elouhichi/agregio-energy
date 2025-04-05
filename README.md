# Agregio - Test Technique Java Spring Boot

Ce projet est un test technique visant à modéliser et exposer des APIs REST pour la gestion d'offres d'énergie sur différents marchés (Réserve Primaire, Secondaire, Rapide), avec une approche **TDD**.

## Technologies utilisées

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Gradle
- JUnit 5 / MockMvc

## Fonctionnalités

-  Création de **parcs** producteurs (solaire, éolien, hydraulique)
-  Création d'**offres** d'énergie associées à des **blocs horaires**
-  Consultation des **offres par marché**
-  Liste des **parcs vendant sur un marché**

## Lancer le projet

1. Cloner le repo :

   https://github.com/elouhichi/agregio-energy.git

2. lancer le projet :

   ./gradlew bootRun
