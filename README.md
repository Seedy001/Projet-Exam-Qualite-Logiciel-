# Projet Fin de Module — Qualité Logicielle

Examen de Qualité Logicielle — Backend Spring Boot CI/CD et Tests  
Université Amadou Mahtar Mbow (UAM)

## Stack technique

- Java 17 / Spring Boot 4.0.5
- Spring Data JPA + MySQL
- Bean Validation (Jakarta)
- JUnit 5 + Mockito + MockMvc
- JaCoCo + Checkstyle
- GitHub Actions CI/CD

## Structure du projet

```
src/
├── main/java/com/riffo/users/
│   ├── controller/   → PartenaireRESTController
│   ├── entity/       → Partenaire
│   ├── exception/    → GlobalExceptionHandler, exceptions personnalisées
│   ├── repository/   → PartenaireRepository
│   └── service/      → PartenaireService + PartenaireServiceImpl
└── test/java/com/riffo/users/
    ├── controller/   → PartenaireRESTControllerTest (MockMvc)
    └── service/      → PartenaireServiceImplTest (Mockito)
```

## Tests

```bash
mvn test
```

Le rapport de couverture JaCoCo est généré dans `target/site/jacoco/index.html`.

## Qualité du code

```bash
mvn checkstyle:check
```

## Endpoints

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/api/partenaires` | Liste tous les partenaires |
| GET | `/api/partenaires/{id}` | Recherche par ID |
| POST | `/api/partenaires` | Créer un partenaire |
| PUT | `/api/partenaires/{id}` | Modifier un partenaire |
| DELETE | `/api/partenaires/{id}` | Supprimer un partenaire |

## CI/CD

Pipeline GitHub Actions déclenché à chaque push sur `main` :
1. Compilation
2. Exécution des tests
3. Vérification Checkstyle
4. Génération du rapport JaCoCo
