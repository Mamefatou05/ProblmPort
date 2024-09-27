# Utiliser une image Maven avec OpenJDK pour construire le projet
FROM maven:3.8.1-openjdk-17 AS builder

# Définir le répertoire de travail pour la construction
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances Maven
COPY pom.xml .

# Télécharger les dépendances sans construire
RUN mvn dependency:go-offline

# Copier tout le projet source dans le conteneur
COPY src ./src

# Construire le projet Maven pour générer le fichier .jar
RUN mvn clean install

# Étape 2: Utiliser une image OpenJDK légère pour exécuter l'application
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail pour exécuter l'application
WORKDIR /app

# Copier le fichier .jar généré dans l'image de runtime
COPY --from=builder /app/target/ProjetJavaApp-0.0.1-SNAPSHOT.jar /app/ProjetJavaApp.jar

# Exposer le port sur lequel votre application écoute
EXPOSE 8001

# Lancer l'application Java
CMD ["java", "-jar", "ProjetJavaApp.jar"]