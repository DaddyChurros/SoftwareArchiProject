
# Service architecture

## Introduction

Dans le cadre du cours “service architecture”, il nous a été demandé de mettre en place une application Web (preuve de concept) basée sur une architecture microservices selon le contexte de notre choix.

Après avoir initialement considéré l'un des sujets proposés : la gestion des salles de l'INSA, nous avons finalement opté pour un projet original : un système de gestion intelligent d'un restaurant en self-service.

Nous allons donc vous présenter dans ce rapport notre application Web sur la gestion d’un restaurant en self-service comprenant 4 microservices métier gérant le menu, des tables du restaurant, de l’état de ces tables et de la sécurité du restaurant. L’idée est d’avoir des évènements périodiques qui vont influencer les paramètres liés aux plats et à l’état des tables pour déclencher diverses alertes qui seront ensuite gérées par le service de sécurité, qui va historiser les informations du problème.

Pour garantir le bon fonctionnement de cette architecture distribuée, nous avons mis en œuvre les principes fondamentaux des microservices : la collaboration, la découverte, la répartition des charges et enfin le service de configuration et son client. De plus, nous avons utilisé Maven pour ce projet, qui est un outil indispensable pour une architecture microservices au vu de sa capacité à gérer la cohérence des versions, l’intégration de framework et des dépendances pour plusieurs projets.

Dans ce rapport, nous allons tout d’abord présenter nos microservices principaux qui recevront les différentes requêtes. Ensuite, nous verrons comment nous avons intégré les principes clés des microservices cités précédemment. Enfin, nous conclurons sur le dénouement du projet, des compétences acquises et des ouvertures d’améliorations.

## Présentation des microservices

Nos microservices métier sont répartis comme suit :

### GestionNourriture

Service responsable de la gestion des plats disponibles dans le restaurant, du suivi des stocks et de la chaîne du froid. Il surveille la température des plats et envoie des alertes en cas de rupture de la chaîne du froid.

#### Responsabilités

- Permet de définir un mini catalogue de plat
- Permet de définir une liste de bacs à nourriture
- Permet de vérifier la température du plat pour respecter la chaîne du chaud/froid grâce à des mesures de capteurs régulière
- Permet de lever des alertes vers le microservice de sécurité s’il y a un problème

#### Endpoints

| Méthode | Endpoint | Description | Body |
|---------|----------|-------------|------|
| `GET` | `/plats` | Récupérer tous les plats | - |
| `GET` | `/plats/{id}` | Récupérer un plat par ID | - |
| `POST` | `/plats` | Créer un nouveau plat | `{nom, type, temperatureMin, temperatureMax}` |
| `PUT` | `/plats/{id}` | Modifier un plat | `{nom, disponible, temperatureActuelle}` |
| `GET` | `/plats/disponibles` | Récupérer les plats disponibles | - |
| `POST` | `/plats/{id}/temperature` | Mettre à jour la température | `{temperature}` |

### gestTable-Gestion des tables

Service responsable de la gestion du référentiel des tables du restaurant. Il stocke et gère les informations statiques concernant les tables : identifiant, capacité maximale, emplacement, etc.

#### Responsabilités
- Permet d’ajouter/supprimer des tables
- Définit la jauge max d’occupants par table
- Permet de modifier une table (ex : nombre de places, nom)
- Retourne les informations d’une table et listes les tables

#### Endpoints

| Méthode | Endpoint | Description | Body |
|---------|----------|-------------|------|
| `GET` | `/tables` | Récupérer toutes les tables | - |
| `GET` | `/tables/{id}` | Récupérer une table par ID | - |
| `POST` | `/tables` | Créer une nouvelle table | `{id, jaugeMax, nombrePlaces, nomReservation}` |
| `PUT` | `/tables/{id}` | Modifier une table existante | `{jaugeMax, nombrePlaces, nomReservation}` |
| `DELETE` | `/tables/{id}` | Supprimer une table | - |


### occupationTable - Gestion de l'occupation

Service responsable du suivi en temps réel de l'occupation des tables. Il gère l'état actuel de chaque table (libre, occupée, réservée) et le nombre de personnes présentes.

#### Responsabilités

- Incrémente / décrémente le nombre d’occupants de table
- Retourne la valeur actuelle d’occupation d’une table
- Alerte si une table est vide (3 états : occupé, à nettoyer, et libre)

#### Endpoints

| Méthode | Endpoint | Description | Body |
|---------|----------|-------------|------|
| `GET` | `/occupations` | Récupérer toutes les occupations | - |
| `GET` | `/occupations/{id}` | Récupérer l'occupation d'une table | - |
| `POST` | `/occupations` | Créer/Mettre à jour une occupation | `{tableId, nombrePersonnes, statut}` |
| `PUT` | `/occupations/{id}` | Modifier une occupation | `{nombrePersonnes, statut}` |
| `DELETE` | `/occupations/{id}` | Libérer une table | - |

### SecuritéRestaurant

Service central de sécurité qui reçoit, traite et historise toutes les alertes provenant des autres microservices. Il sert de point de centralisation pour tous les événements de sécurité du restaurant.

#### Responsabilités

- Recevoir un événement de dépassement de gestionTable
- Recevoir un événement de problème de température de GestionNourriture
- Recevoir des événement du microservice occupationTable
- Créations et déclenchement d’alertes
- Historiser les alertes

#### Endpoints

| Méthode | Endpoint | Description | Body |
|---------|----------|-------------|------|
| `GET` | `/securite/alertes` | Récupérer toutes les alertes | - |
| `GET` | `/securite/alertes/{id}` | Récupérer une alerte par ID | - |
| `POST` | `/securite/table_event` | Recevoir une alerte (occupation) | `{sourceService, message, severity}` |
| `POST` | `/securite/alertes` | Recevoir une alerte (générique) | `{sourceService, message, severity}` |
| `GET` | `/securite/alertes/service/{service}` | Alertes par service | - |
| `GET` | `/securite/alertes/severity/{level}` | Alertes par gravité | - |

Chaque microservice représente un projet Maven, assurant ainsi une structure homogène et une bonne gestion des dépendances pour chacun d’entre eux. À ce stade, chaque projet Maven contient évidemment un pom.xml comprenant toute la configuration requise du projet. Le dossier du microservice contient également un application.properties comprenant le port sur lequel ce microservice tourne en local, mais aussi une variable securite-url qui contient le chemin de mapping du service d’alerte du microservice SecuritéRestaurant, permettant ainsi à ce dernier de gérer l’alerte. Les échanges se font avec des requêtes REST à l’aide de RestTemplate et chaque microservice offre la possibilité d’effectuer des requêtes.

## Intégration des principes clés

Cette section présente comment nous avons intégré certains principes fondamentaux des architectures microservices dans notre application, à savoir la collaboration entre services, la découverte dynamique, la répartition des charges et la gestion automatique de la configuration.

### Collaboration

L’idée derrière ce principe est de pouvoir faire collaborer plusieurs microservices indépendants afin d’obtenir une fonctionnalité indépendante. La collaboration de services est assurée par un microservice orchestrateur qui va permettre de coordonner les différentes requêtes en les regroupant sous un même microservice. Dans notre cas, la gateway constitue un point d’entrée unique pour les clients et toutes les requêtes externes passent par lui.

### Découverte

Les paramètres tels que les adresses IP et les ports sont écrits en dur et sont donc volatiles. En augmentant le nombre de microservices, cela devient très vite problématique. De ce fait, ce principe de découverte de services permet de se délier des adresses et ports volatiles, et tout regrouper sous un seul service. Pour cela, nous utilisons un serveur Eureka dédié qui va jouer le rôle d’annuaire dynamique. Les clients et autres services n’utilisent plus les adresses physiques des microservices qu’ils veulent utiliser, ils interrogent plutôt Eureka qui va lui de son côté faire la résolution.

### Service de configuration centralisé et client

Afin d’éviter les duplications et de faciliter les changements, il est nécessaire d’avoir un service qui permet une gestion centralisée de la configuration des microservices. C’est le service Config dans notre schéma qui va jouer ce rôle et maintenir les configurations de ports, accès à la base de données. Les microservices qui récupèrent leur configuration sont considérés comme clients de ce service de configuration.

## Conclusion

Pour conclure, nous avons pu mettre en place une architecture microservice fonctionnelle dans un contexte de restauration en self-service. De plus, nous avons pu intégrer certains principes clés tels que la collaboration ou la configuration centralisée. Nous avons apprécié travailler sur ce projet, néanmoins les possibilités d’améliorations sont nombreuses pour une telle architecture dans ce contexte, avec par exemple l’ajout de plus de capteurs, ou en faisant évoluer l’établissement de self-service à service à table, et modifier l’architecture pour y inclure des serveurs (waiter) par exemple.
