# Assurance Examen — API REST Spring Boot

Application de gestion de partenaires d'assurance.

## Stack

- Java 17 / Spring Boot 4.0.5
- Spring Data JPA + MySQL
- JUnit 5 + Mockito + MockMvc
- JaCoCo + Checkstyle
- GitHub Actions CI/CD

## Lancement

```bash
# Prérequis : MySQL avec base assurance_db
./mvnw spring-boot:run
```

L'API est accessible sur `http://localhost:8080/api/partenaires`.

## Tests

```bash
./mvnw test
```

Le rapport de couverture JaCoCo est généré dans `target/site/jacoco/index.html`.

## Endpoints principaux

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/api/partenaires` | Liste tous les partenaires |
| GET | `/api/partenaires/{id}` | Recherche par ID |
| POST | `/api/partenaires` | Créer un partenaire |
| PUT | `/api/partenaires/{id}` | Modifier un partenaire |
| DELETE | `/api/partenaires/{id}` | Supprimer un partenaire |
