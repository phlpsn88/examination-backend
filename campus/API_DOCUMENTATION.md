# API-testing

Dit document bevat de documentatie voor API-testing.

---

>**Endpoints CAMPUS**

### Create Campus

* **URL:** [http://localhost:8080/campus](http://localhost:8080/campus)
* **Methode:** POST
* **Beschrijving:** Maakt een nieuwe campus aan in het systeem.

#### Request

```json
{
  "name": "PROXIMUS",
  "address": "Geldenaaksebaan 335, 3001 Leuven",
  "parkingSpots": 400
}
```

#### Response

```json
{
  "name": "PROXIMUS",
  "address": "Geldenaaksebaan 335, 3001 Leuven",
  "parkingSpots": 400,
  "amountLocals": 0,
  "locals": []
}
```

---

### Get All Campuses

* **URL:** [http://localhost:8080/campus](http://localhost:8080/campus)
* **Methode:** GET
* **Beschrijving:** Haalt alle campussen op.

#### Response

````json
[
  {
    "name": "PROXIMUS",
    "address": "Geldenaaksebaan 335, 3001 Leuven",
    "parkingSpots": 400,
    "amountLocals": 0,
    "locals": []
  },
  {
    "name": "DIEST",
    "address": "Geldenaaksebaan 205, 3001 Leuven",
    "parkingSpots": 400,
    "amountLocals": 0,
    "locals": []
  }
]
````

---

### Update Campus

- **URL:** http://localhost:8080/campus/PROXIMUS
- **Methode:** PUT
- **Beschrijving:** Werkt een bestaande campus bij op basis van de naam.

#### Request

```json
{
  "name": "PROXIMUS UPDATE",
  "address": "Geldenaaksebaan 335, 3001 Leuven",
  "parkingSpots": 400
}
````

#### Response

```json
{
  "id": 1,
  "name": "PROXIMUS UPDATE",
  "address": "Geldenaaksebaan 335, 3001 Leuven",
  "parkingSpots": 400
}
```

---

### Delete Campus

* **URL:** [http://localhost:8080/campus/PROXIMUS](http://localhost:8080/campus/PROXIMUS)
* **Methode:** DELETE
* **Beschrijving:** Verwijdert een campus op basis van de naam.

#### Response

``Status: 200 OK``

---

>**Endpoints ROOMS**

### Add room to Campus

* **URL:** [http://localhost:8080/campus/PROXIMUS/local](http://localhost:8080/campus/PROXIMUS/local)
* **Methode:** POST
* **Beschrijving:** Maakt een nieuw lokaal aan bij de opgegeven campus in de url.

#### Request

```json
{
  "name": "B14",
  "type": "Sciense Class",
  "capacity": 15,
  "floor": 2
}
```

#### Response

```json
{
  "name": "B14",
  "type": "Sciense Class",
  "capacity": 15,
  "floor": 2
}
```

---

### Get all rooms in campus

* **URL:** [http://localhost:8080/campus/PROXIMUS/rooms](http://localhost:8080/campus/PROXIMUS/rooms)
* **Methode:** GET
* **Beschrijving:** Haal alle lokalen op die bij een campus horen.

#### Response

```json
[
  {
    "name": "B14",
    "type": "Sciense Class",
    "capacity": 15,
    "floor": 2
  },
  {
    "name": "B12",
    "type": "Gym",
    "capacity": 50,
    "floor": 1
  }
]
```

---

### Count all rooms in campus

* **URL:** [http://localhost:8080/campus/PROXIMUS/amount-locals](http://localhost:8080/campus/PROXIMUS/amount-locals)
* **Methode:** GET
* **Beschrijving:** Tel alle lokalen die bij een campus horen.

#### Response

```json
{
  "Amount locals in campus PROXIMUS": 2
}
```

---

### Find Room by Campus name and Room name

* **URL:** [http://localhost:8080/campus/PROXIMUS/rooms/B12](http://localhost:8080/campus/PROXIMUS/rooms/B12)
* **Methode:** GET
* **Beschrijving:** Zoek een room door campus- en roomname mee te geven

#### Response

```json
{
  "name": "B12",
  "type": "Gym",
  "capacity": 50,
  "floor": 1
}
```

---

## Endpoints USER

### Create USER

* **URL:** [http://localhost:8080/user](http://localhost:8080/user)
* **Methode:** POST
* **Beschrijving:** Maakt een nieuwe User aan in het systeem.

#### Request

```json
{
  "firstName": "Annebeth",
  "lastName": "Philipsen",
  "birthDate": "1993-10-15",
  "email": "philipsenannebeth@gmail.com"
}
```

#### Response

```json
{
  "firstName": "Annebeth",
  "lastName": "Philipsen",
  "birthDate": "1993-10-15",
  "email": "philipsenannebeth@gmail.com"
}
```

---

### Get USER by id

* **URL:** [http://localhost:8080/user/1](http://localhost:8080/user/1)
* **Methode:** GET
* **Beschrijving:** Zoekt een User op basis van meegegeven id

#### Response

```json
{
  "firstName": "Annebeth",
  "lastName": "Philipsen",
  "birthDate": "1993-10-15",
  "email": "philipsenannebeth@gmail.com"
}
```

---