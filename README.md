# ClickAndPlay

## Lancement du projet

Un .jar auto-exécutable se trouve dans le dossier jar/ à la racine, pour l'exécuter: ```java -jar jar/ClickAndPlay-1.0-SNAPSHOT.jar```

Un swagger est accessible à l'adresse suivante: http://localhost:8080/swagger-ui/index.html

Un jeu de test est disponible avec 2 sessions, 4 joueurs, 1 table.

Pour recompiler si besoin: ```mvn clean package```

## Précision sur les permissions

### Gestion des séances

Seul un admin peut créer, supprimer, modifier des séances.

Tous les rôles peuvent récuperer les séances créées.

### Gestion des tables

Les animateurs peuvent créer, supprimer, modifier des tables (un admin est aussi un animateur).

Tous les rôles peuvent récuperer les tables créées.

### Gestion des joueurs

Seul un admin peut créer un joueur, les joueurs peuvent cotiser eux-mêmes.

Par défaut lors de sa création, un joueur ne cotise pas. On considère qu'il est possible pour un joueur de payer sa cotisation lors de son inscription ou plus tard via l'endpoint PATCH "/api/players/{playerId}/cotisation"

On considère que n'importe quel joueur peut payer la cotisation de n'importe quel autre joueur.