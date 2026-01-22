
# Système de Gestion de Restaurant - Architecture Microservices

Projet d’architecture **microservices** pour la gestion d’un restaurant.

Ce dépôt Git contient **l’ensemble du projet** réalisé dans le cadre du module *Service Architecture*. Il inclut :
- un **rapport** décrivant le fonctionnement global,
- **7 microservices**, dont l’architecture et les interactions sont détaillées dans le rapport.

---

## Pré-requis (réseau & base de données)

**Important :** la base de données utilisée par l’ensemble des microservices est **accessible uniquement depuis le réseau de l’INSA**.  

Sinon, les microservices pourront démarrer mais échoueront lors de l’accès à la BDD (erreurs de connexion/timeout).

---

## Ordre de démarrage recommandé

1. **Eureka Server** *(port 8761)*  
2. **Config Server** *(port 8888)*  
3. **Microservices métier + Gateway** *(ordre libre ensuite)*

> L’important est que **Config Server soit disponible avant le démarrage des services qui en dépendent**.

---

## Liste des microservices

| Service | Port | Description |
|---------|------|-------------|
| **Eureka Server** | 8761 | Registre des services (Service Discovery) |
| **Config Server** | 8888 | Configuration centralisée (via dépôt Git) |
| **Gateway** | 8080 | Point d’entrée unique (reverse proxy / routage) |
| **gestTable** | 8081 | Gestion des tables |
| **SecuriteRestaurant** | 8083 | Gestion des alertes de sécurité |
| **GestionFood** | 8084 | Gestion de la nourriture |
| **occupationTable** | 8086 | Gestion de l’occupation des tables |

---

## Pourquoi démarrer le Config Server en premier ?

Les microservices récupèrent leur configuration depuis le **Config Server** au démarrage.  
Si le Config Server n’est pas lancé (ou pas accessible), alors :

- les services ne peuvent pas charger leur configuration (port, base de données, paramètres, etc.),
- ils risquent de **ne pas démarrer correctement**,
- vous verrez typiquement des erreurs du type :

`Could not locate PropertySource`

---

## Ce qui se passe au démarrage d’un microservice

1. Le service contacte le **Config Server** -> récupère sa configuration (port, BDD, propriétés…)
2. Le service démarre avec cette configuration
3. Le service s’enregistre dans **Eureka**
4. Il devient **découvrable** par les autres services (et routable via la Gateway)

Message attendu côté Config Server :  
`Started ConfigServerApplication`
