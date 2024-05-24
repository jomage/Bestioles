# Application Spring Boot API Rest + Authentification JWT

# But

Fournir une API de type REST pour accéder à des entités (Person, Animal, Species). La base de données est éphémère
(en mémoire) et est recréée à neuf à chaque lancement avec des données pré-remplies.

# Modèle de données

Il y a 3 entités représentées par le __domain model__ : Person, Animal et Species

## Entité Species
Représente une espèce animale (chat, chien, ...).  
Ses champs sont :
- id : numéro d'identification, nombre (requis),
- commonName : nom commun de l'espèce, chaine de caractères longueur max 50 (requis),
- latinName : nom scientifique de l'espèce, chaine de caractères de longueur max 200 (requis)

## Entité Animal
Représente un spécimen d'animal.  
Ses champs sont :
- id : numéro d'identification, nombre (requis),
- name : nom du spécimen, chaine de caractères longueur max 50 (requis),
- color : couleur du spécimen, chaine de caractères longueur max 50 (requis),
- sex : sexe biologique du spécimen, énumération de chaines de caractères ("M", "F") (requis),
- species : l'espèce de l'animal, lien vers une entité Species précise (requis),
- persons : les personnes "propriétaires" de l'animal, tableau de Person (peut être vide)

## Entité Person
Représente une personne.  
Ses champs sont :
- id : numéro d'identification, nombre (requis),
- firstName : le prénom de la personne, chaine de caractères longueur max 50 (requis),
- lastName : le nom de famille de la personne, chaine de caractères longueur max 50 (requis),
- age : l'âge de la personne, nombre, min 0 max 120 (peut être null)
- animals : les animaux "possédés" par la personne, tableau d'Animal (peut être vide)

# Lancer le serveur

Dans une invite de commande à la racine du projet, taper la commande suivante 
> omettre le `./` si vous n'êtes pas sur Linux ou une fenêtre Git Bash
```shell
./mvnw spring-boot:run

# avec un port autre que 8080 :
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments='-Dserver.port=8085'
```

Cela lancera le serveur sur le port http 8080 ou celui spécifié dans l'option.

# Authentification

Toutes les requêtes (sauf /auth) requièrent d'être authentifié. 
Il y a 2 utilisateurs par défaut :
- Utilisateur normal (ROLE_USER) : username=user | password=user
- Utilisateur admin (ROLE_ADMIN) : username=admin | password=admin

## Comment s'authentifier

### Appel endpoint `/auth`

Utiliser l'endpoint `/auth`. Exemple avec l'utilisateur user :
```
Method : POST
URL : http://localhost:8080/auth
Body : 
{
    "username": "user",
    "password": "user"
}
Retourne :
{
    "id_token": "<le JWT>"
}
```

### Joindre le JWT aux requêtes
Les autres requêtes requièrent de joindre un JWT valide pour réussir. C'est un Bearer Token. Il doit être joint dans le
header `Authorization` et sa valeur doit être précédée du préfixe `Bearer `.

Exemple :
```
Authorization: "Bearer eyJhb....."
```

# API Rest "Bestioles"

Le projet expose différents endpoints pour accéder aux données. Tous les endpoints commencent par `<nom-domaine>/api/`,
ceux-ci requièrent d'être authentifié.  
Pour chaque entité, il y a 
- `GET localhost:8080/<nom-entité>` : liste d'entités avec possibilité de filtrer. Pour filtrer, accepte un paramètre
`contains` avec comme valeur le fragment à trouver dans l'entité (cherche généralement sur les noms). Besoin du role
ROLE_USER ou ROLE_ADMIN.
- `GET localhost:8080/<nom-entité>/<id>` : ramène les infos complètes d'une entité, par son ID. Besoin du role ROLE_USER
ou ROLE_ADMIN.
- `POST localhost:8080/<nom-entité>` : crée l'entité dans le body. Besoin du role ROLE_ADMIN.
- `PUT localhost:8080/<nom-entité>/<id>` : modifie l'entité désignée par son ID par la version donnée dans le body. Besoin
du role ROLE_ADMIN.
- `DELETE localhost:8080/<nom-entité>/<id>` : tente de supprimer l'entité désignée par l'ID fournie. Besoin du role
ROLE_ADMIN.

# Swagger & OpenAPI

Pour plus d'informations concernant les endpoints exposés, il y a :
- une page OpenAPI qui décrit les endpoints en JSON : http://localhost:8080/api-docs
- une page SwaggerUI qui décrit les endpoints avec une interface : http://localhost:8080/api-docs/ui (ou
http://localhost:8080/api-docs/swagger-ui/index.html)
