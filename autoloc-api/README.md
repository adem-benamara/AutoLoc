# AutoLoc API

Plateforme de gestion de location de véhicules multi-agences (ASI 26-27).

## Prérequis
- JDK 17+, Maven, MySQL démarré sur `localhost:3306`

## Lancement
```bash
export DB_PASSWORD=votre_mot_de_passe   # Windows PowerShell : $env:DB_PASSWORD="..."
mvn spring-boot:run
```
La base `autoloc_db` est créée automatiquement et les 9 tables sont générées par Hibernate (`ddl-auto=update`).

## Entités (package `domain`)
Vehicule, Agence, Client, Employe, Equipement, Reservation, Contrat, Paiement, Maintenance
+ énumérations : StatutVehicule, CategorieVehicule, RoleEmploye, StatutReservation, ModePaiement.

Les associations seront ajoutées à l'Atelier 2.
