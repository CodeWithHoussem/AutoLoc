# 🚗 AutoLoc — Gestion de location de véhicules

## 📌 Description

**AutoLoc** est une application web de gestion de location de véhicules développée avec **Java 17 et Spring Boot**. Le projet permet de gérer les principales fonctionnalités d'une agence de location, notamment les véhicules, agences, clients, employés, réservations, contrats, paiements et opérations de maintenance.

L'application est conçue selon une **architecture monolithique en couches (Layered Architecture)** afin de séparer clairement les responsabilités entre la présentation, le métier, la persistance et le domaine.

## 🏗️ Architecture

Le projet est organisé autour des couches suivantes :

* **Presentation** : contrôleurs REST et exposition des API.
* **DTO** : transfert des données entre les différentes couches.
* **Service** : gestion de la logique métier et des règles de gestion.
* **Repository** : accès aux données avec Spring Data JPA.
* **Domain** : entités métier et énumérations.
* **Transverse** : gestion des aspects communs comme le logging, les performances et les tâches planifiées.

## 🗃️ Modèle de données

Le modèle métier comprend notamment :

* **Agence** : gestion des agences de location.
* **Vehicule** : gestion des véhicules et de leur disponibilité.
* **Equipement** : gestion des équipements associés aux véhicules.
* **Client** : gestion des informations des clients.
* **Employe** : gestion des employés et de leurs rôles.
* **Reservation** : gestion des réservations.
* **Contrat** : gestion des contrats de location.
* **Paiement** : gestion des paiements liés aux contrats.
* **Maintenance** : suivi des opérations de maintenance des véhicules.

Le projet met en œuvre plusieurs associations **JPA/Hibernate** :

* `@OneToMany`
* `@ManyToOne`
* `@OneToOne`
* `@ManyToMany`

avec différentes stratégies de **Cascade** et de **Fetch** (`LAZY`) selon les besoins métier.

## 🛠️ Technologies utilisées

| Technologie        | Utilisation                         |
| ------------------ | ----------------------------------- |
| Java 17            | Langage de programmation            |
| Spring Boot        | Framework principal                 |
| Spring Data JPA    | Persistance et ORM                  |
| Spring MVC         | API REST                            |
| Spring AOP         | Gestion des aspects transversaux    |
| Spring Scheduler   | Tâches planifiées                   |
| MySQL / PostgreSQL | Base de données                     |
| H2                 | Tests en mémoire                    |
| Maven              | Gestion des dépendances et du build |
| Lombok             | Réduction du code répétitif         |
| MapStruct          | Mapping DTO, si utilisé             |
| Swagger / OpenAPI  | Documentation des API               |
| Git / GitHub       | Gestion de versions                 |
| Postman            | Tests des API                       |

## 🎯 Objectifs du projet

L'objectif principal d'AutoLoc est de mettre en pratique les concepts de **Spring Boot, JPA/Hibernate et architecture logicielle** à travers une application concrète de gestion de location de véhicules.

Le projet permet notamment de travailler sur :

* La conception d'un modèle de données relationnel.
* La création et la gestion des entités JPA.
* La gestion des associations entre entités.
* Les stratégies de cascade et de chargement des données.
* La création d'API REST.
* La séparation entre entités et DTO.
* L'implémentation de services métier.
* La gestion de la persistance avec Spring Data JPA.
* La documentation des API avec Swagger/OpenAPI.
* La mise en place d'une architecture propre et évolutive.

## 📂 Structure du projet

```text
src/
└── main/
    └── java/
        └── com.example.demo/
            ├── domain/
            ├── repository/
            ├── service/
            ├── web/
            │   ├── controller/
            │   └── dto/
            └── aspect/
```

## 🚀 Installation et exécution

### Prérequis

* Java 17 ou supérieur
* Maven
* MySQL ou PostgreSQL
* Git

### Cloner le projet

```bash
git clone https://github.com/CodeWithHoussem/projetSpringboot.git
cd projetSpringboot
```

### Configurer la base de données

Modifier le fichier :

```text
src/main/resources/application.properties
```

avec les informations de connexion à votre base de données.

### Lancer l'application

Avec Maven :

```bash
mvn spring-boot:run
```

Ou lancer directement la classe principale Spring Boot depuis l'IDE.

## 📖 Documentation API

Une documentation interactive des API peut être intégrée avec **Swagger UI / OpenAPI**, permettant de consulter et tester les différents endpoints de l'application.

## 👨‍💻 Auteur

**Houssem Kanzari**

Projet réalisé dans le cadre de la formation en informatique et du module **ASI — Architecture des Systèmes d'Information**.
