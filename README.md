# 🎓 Examen-opdracht Java Advanced

## 📝 Opdracht: Campus-applicatie bouwen

De opdracht voor het examen is een **applicatie** op te bouwen voor het van een beheren van een campus.
Doel is **Spring Boot Applicatie** te ontwikkelen die je toelaat om via een web-api:

*   Nieuwe **campus-gebouwen** te registreren 🏢
*   **Lokalen** toe te voegen 🚪
*   Users toe te voegen en **reservaties** voor deze **users** te maken 📅

## ⚙️ Functionele vereisten (+ Entiteiten/structuur)

De basis-entiteit is een **campus**, in de applicatie dienen we meerdere campus-gebouwen moeten kunnen onderhouden.

### Campus en Lokalen (gebouwbeheer) 🏛️

Het eerste onderdeel is het **gebouwbeheer**.
Een **Campus** bevat volgende onderdelen:

*   Een **naam** (als primary key) 🏷️
*   Een **adres** 📍
*   Aantal **parkeerplaatsen** 🅿️
*   **Aantal lokalen** (dynamisch berekend) 🔢

Binnen een campus kan je 1 of meerdere lokalen hebben.
Een **Lokaal** bevat de volgende onderdelen:

*   ID (numeriek/automatisch) 🆔
*   Een naam 📛
*   Type lokaal 🏫
*   Aantal personen dat binnen het lokaal kan 👥
*   Naam/Voornaam 👤
*   Verdieping 📶

> ℹ️ Nota: Een campus heeft een one-2-many verhouding met een lokaal...

Binnen een campus-gebouw mag je geen 2 lokalen hebben met dezelfde naam. 🚫👯‍♂️

### Users en Reservaties 👥📅

Een 2de onderdeel is het **reservatie-beheer** van het gebouw.
Deze bevat 2 entiteiten:

De hoofdentiteit is een User, deze heeft:

*   Een ID (numeriek/automatisch) 🆔
*   Een Naam/Voornaam 👤
*   Een geboortedatum 🎂
*   Een mail-adres 📧

Een User kan 1 of meerdere reservaties (numeriek/automatisch) aanmaken.
Een reservatie bevat:

*   Een ID (numeriek/automatisch) 🆔
*   Start-tijdstip 🕒
*   Einde 🕒
*   Eventueel commentaar van de gebruiker 💬
*   Aantal personen dat deze reservatie mogelijk kan onderbrengen (dynamisch berekend op basis lokalen) 🔢

**Belangrijke eigenschap:** een reservatie kan worden uitgevoerd voor 1 maar ook meerdere lokalen tegelijkertijd. 🔑
We betreft dus ook een many-2-many-relatie.

#### Beschikbaarheid van een lokaal ✅

Het is zeer belangrijk dat je - bij het aanmaken van een reservatie - de nodige controles uitvoert.

*   Een **startdatum** van een reservatie moet **voor** de **einddatum** liggen ⏳
*   Je mag niet in het verleden reserveren (einddatum mag niet voor de huidige datum liggen) ⏪
*   Een lokaal mag binnen je reservatie maar **1 maal** worden gereserveerd ☝️
*   Een lokaal mag niet **reeds gereserveerd** zijn door een **andere reservatie** ⛔
*   Kijk hier voor naar het **overlappen** van de **periodes** met andere reservaties op het zelfde lokaal (een lokaal kan niet 2 maal worden gereserveerd tijdens dezelfde periode) 🔄

## 🌐 REST-api

Vanzelfsprekend dient deze functionaliteit worden aangeboden via een REST-api.
Deze REST-api is gedocumenteerd (automatisch) via de technieken die we hebben gezien in de cursus.

De volgende endpoints dienen minimaal worden voorzien (met alle CRUD-mogelijkheden):

Het aanmaken en opvragen van de campus-locaties voor de school:

*   `/campus`
*   `/campus/{campus-id}`
*   (bijvoorbeeld `campus/PROXIMUS`)

Het aanmaken en opvragen van lokalen binnen een campus:

*   `/campus/{campus-id}/rooms/`
*   `/campus/{campus-id}/rooms/{room-id}`

Hier zou het leuk zijn query-parameters te voorzien om de opzoeking te vergemakkelijken 🔍:

*   `availableFrom` -> lokalen beschikbaar vanaf
*   `availableUntil` -> lokalen beschikbaar tot
*   `minNumberOfSeats` -> lokalen met minimum aantal Seats

Deze moet je individueel of tesamen kunnen gebruiken

*   `/campus/PROXIMUS/rooms/?availableFrom={date}&availableUntil={date}&minNumberOfSeats={number}`

Je moet een user kunnen aanmaken en kunnen opvragen op basis van id:

*   `/user` en `/user/{user-id}`

Waar mogelijk voeg ook een query toe om een user op te vragen via naam of een deel 🔍:

*   `/user` en `/user?nameMatches={partOfName}`

Een user kan zijn reservaties zien via:

*   `/user/{user-id}/reservations`
*   `/user/{user-id}/reservations/{reservation-id}`

Je kan na het aanmaken van een reservatie een room toevoegen (via een put-operatie) ➕:

*   `/user/{user-id}/reservations/{reservation-id}/rooms/{room-id}`
*   (zoals bijvoorbeeld `PUT /user/1/reservations/3/rooms/2`)

Voorzie ook (ter onderscheiding als het voorgaande lukt) ook nog de mogelijkheid reservaties te zien binnen een room... (read-only) 👀:

*   `campus/{campus-id}/rooms/{room-id}/reservations/`

### Belangrijk: gebruik status-codes!!! ⚠️

Voorzie toepasselijke HTTP-error-codes wanneer 1 van de eerder vermelde regels niet kan toegepast kan worden of als een resource niet bestaat.

## 💻 Onderscheiding: command-line (front-end)

Schrijf een command-line-app die je gebruikt om een user aan te maken en reservatie te kunnen maken.

Zorg daarin dat de gebruiker op voorhand kan zien welke lokalen binnen een campus beschikbaar zijn op een bepaald tijdstip.

Vanzelfsprekend moet deze applicatie gebruik maken van de REST-api via een Webclient (en niet via de DB). 🕸️

## 🛠️ Technische kwaliteit-vereisten

De volgende vereisten worden voorzien vanuit een technisch standpunt.

### Code-kwaliteit ✨

Er wordt gequoteerd op code-kwaliteit en stabiliteit van de code.
Voorzie waar nodig van het opvangen van excepties en probeer waar van toepassing gebruik te maken van de optional-API.

### Gebruik van een database 💾

Om deze data te bewaren gebruik je een **MySQL-database**.
De database wordt benaderd gebruik makende van **JPA** en **Spring Data**.

Zorg er ook voor dat je aandacht besteedt aan de relaties tussen de verschillende entiteiten en het cascading-level.

Mocht je er geraken (MySQL is de eerste prioriteit) voorzie via een **profile** de mogelijkheid ook een **H2-database** te gebruiken.

### Layering 🍰

Je applicatie heeft tenminste **3 layers**:

*   **Controller** -> Weet hoe je met REST moet omgaan
*   **Service** -> Bevat (samen met model) business-logica
*   **Repository** (of DAO) -> Weet hoe je de data moet fetchen

Daarnaast structureer je alles in **packages** en voorzie waar nodig documentatie (niet overdrijven vanzelfsprekend) 📦

### Testing 🧪

Schrijf een aantal unittesten om het reservatie-gedrag en bijhorende constraints te testen (wat als je bijvoorbeeld ).
Maak hiervoor gebruik van mocks (met Mockito) 🎭

Schrijf ook minstens 1 integratie test om een API testen (bijvoorbeeld het aanmaken van een campus).
In dien mogelijk probeer hier niet de echte (MySQL-database voor te gebruiken)

### Documentatie API via Swagger 📖

De API dient automatisch gedocumenteerd worden via Swagger

### Git 🌿

Maak gebruik van een git-repository.

Push je code naar Github, bij inlevering verwacht ik een zip-file met de code maar ook een link naar je Github-project.

> **Nota:**
> In geval je je code niet publiek wil stellen en een private repo wil gebruiken zorg dan dan mijn Github-account access heeft.
> (in dat geval stuur je een mail of een chatbericht en geef ik de naam van mijn github-account)

## 🏁 Minimum oplevering => gebouwbeheer

Gezien het een omvangrijke opdracht is, zorg dat je in fases werkt (werk bijvoorbeeld eerst het gebouwbeheer uit).

De mimimum oplevering om voor deze opdracht geslaagd te zijn (er vanuitgaande dat je kwaliteit oplevert en de techische vereisten volgt) is de REST-api voor het 1ste gedeelte, namelijk het gebouwbeheer.

Om in aanmerking te komen voor het maximum van de punten dien je wel het alle opdrachten uitwerken:

*   Gebouwbeheer (met REST-api) 🏢
*   Reservaties 📅
*   Command Line frontend 💻
