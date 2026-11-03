# Übung 3 – Aufgabe 4: Entities und Aggregates

## Projekt: CityFeedback

### 1. Zielsetzung

In diesem Dokument werden die zentralen Entities und Aggregates des CityFeedback-Systems beschrieben.

Grundlage ist das zuvor erstellte UML-Domänenmodell aus Aufgabe 2 sowie die identifizierten Domain Events aus dem Event Storming.

Ziel ist es, die Verantwortlichkeiten, Attribute, Methoden und Beziehungen der Domänenobjekte festzulegen.

### 2. Identifizierte Entities

Folgende Entities wurden identifiziert:

- **User:** Repräsentiert einen registrierten Benutzer des Systems.
- **Complaint:** Repräsentiert eine Beschwerde, die von einem Bürger eingereicht wurde.
- **Comment:** Repräsentiert einen Kommentar, den ein Mitarbeiter der Stadtverwaltung zu einer Beschwerde hinzugefügt hat.

### 3. Identifizierte Aggregate

#### 3.1 User Aggregate

- **Aggregate Root:** User
- **Bounded Context:** Identity Context
- **Verantwortung:** Verwaltung der Identitätsdaten und Benutzerrollen.

#### 3.2 Complaint Aggregate

- **Aggregate Root:** Complaint
- **Zugehörige Entity:** Comment
- **Bounded Context:** Complaint Management Context
- **Verantwortung:** Verwaltung von Beschwerden, Bearbeitungsstatus und zugehörigen Kommentaren.

### 4. Weitere Domänenwerte

**UserRole**
- CITIZEN
- EMPLOYEE

**ComplaintStatus**
- OPEN
- IN_PROGRESS
- RESOLVED

Diese Werte werden als Enums modelliert.

Die Kategorie einer Beschwerde wird zunächst als String gespeichert.

### 5. Detaillierte Beschreibung der Entities

Die Attribute, Methoden und fachlichen Regeln werden in den folgenden Abschnitten ergänzt.

#### 5.1 User – Identity Context

**Typ:** Entity und Aggregate Root

**Bounded Context:** Identity Context

**Beschreibung:**

Die Entity `User` repräsentiert einen registrierten Benutzer des CityFeedback-Systems. Es gibt zwei Benutzerrollen: Bürger (`CITIZEN`) und Verwaltungsmitarbeiter (`EMPLOYEE`).

`User` bildet die Aggregate Root des User Aggregates und verwaltet die Identität sowie die Rolle des Benutzers.

**Attribute:**

| Attribut | Datentyp | Beschreibung |
|---|---|---|
| id | String | Eindeutige Benutzer-ID |
| name | String | Name des Benutzers |
| email | String | E-Mail-Adresse des Benutzers |
| passwordHash | String | Passwortinformation; in der Implementierung als sicherer Hash zu speichern |
| role | UserRole | Rolle des Benutzers (CITIZEN oder EMPLOYEE) |

**Methoden:**

| Methode | Rückgabetyp | Beschreibung |
|---|---|---|
| getRole() | UserRole | Gibt die Rolle des Benutzers zurück |
| isCitizen() | boolean | Prüft, ob der Benutzer Bürger ist |
| isEmployee() | boolean | Prüft, ob der Benutzer Verwaltungsmitarbeiter ist |

**Fachliche Regeln:**

- Jeder Benutzer besitzt eine eindeutige ID.
- Jeder Benutzer besitzt genau eine Rolle.
- Bürger können Beschwerden erstellen und ihre eigenen Beschwerden einsehen.
- Verwaltungsmitarbeiter können Beschwerden bearbeiten und Kommentare hinzufügen.
- Die E-Mail-Adresse muss eindeutig sein. Die Prüfung erfolgt bei der Registrierung über die zuständige Anwendungslogik und das Repository.
- Registrierung und Anmeldung werden nicht direkt durch die User-Entity durchgeführt, sondern später durch entsprechende Services.

**Beziehungen:**

- Ein Benutzer mit Rolle `CITIZEN` kann mehrere Beschwerden erstellen.
- Ein Benutzer mit Rolle `EMPLOYEE` kann mehrere Kommentare verfassen.
- Die Verknüpfungen zu Beschwerden und Kommentaren erfolgen über die jeweilige Benutzer-ID.

**Zugehörige Domain Events:**

- `UserRegistered`: Ein neuer Benutzer wurde erfolgreich registriert.
- `UserLoggedIn`: Ein registrierter Benutzer hat sich erfolgreich angemeldet.

Diese Events stammen aus dem Event Storming. Ihre technische Erzeugung wird später in der Anwendungslogik berücksichtigt.

### 6. Beziehungen zwischen den Aggregates

Die Beziehungen und Konsistenzregeln werden im weiteren Verlauf dokumentiert.
