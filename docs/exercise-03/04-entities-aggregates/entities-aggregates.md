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

#### 5.2 Complaint – Complaint Management Context

**Typ:** Entity und Aggregate Root

**Bounded Context:** Complaint Management Context

**Beschreibung:**

Die Entity `Complaint` repräsentiert eine Beschwerde, die von einem Bürger im CityFeedback-System eingereicht wurde.

Sie bildet die Aggregate Root des Complaint Aggregates und ist für die Verwaltung des Bearbeitungsstatus sowie der zugehörigen Kommentare verantwortlich.

Eine Beschwerde wird von einem Bürger erstellt und anschließend durch Mitarbeiter der Stadtverwaltung bearbeitet.

**Attribute:**

| Attribut | Datentyp | Beschreibung |
|---|---|---|
| id | String | Eindeutige Beschwerde-ID |
| citizenId | String | ID des Bürgers, der die Beschwerde erstellt hat |
| title | String | Titel der Beschwerde |
| description | String | Ausführliche Beschreibung des Anliegens |
| category | String | Kategorie der Beschwerde, z. B. Beleuchtung oder Verkehr |
| status | ComplaintStatus | Aktueller Bearbeitungsstatus |
| createdAt | LocalDateTime | Zeitpunkt der Erstellung |

**Methoden:**

| Methode | Rückgabetyp | Beschreibung |
|---|---|---|
| changeStatus(newStatus: ComplaintStatus) | void | Ändert den Bearbeitungsstatus einer Beschwerde |
| addComment(comment: Comment) | void | Fügt der Beschwerde einen Kommentar hinzu |
| resolve() | void | Markiert die Beschwerde als erledigt |

**Fachliche Regeln:**

- Jede Beschwerde besitzt eine eindeutige ID.
- Jede Beschwerde gehört genau einem Bürger.
- Eine neu erstellte Beschwerde erhält den Status `OPEN`.
- Eine Beschwerde kann die Status `OPEN`, `IN_PROGRESS` oder `RESOLVED` besitzen.
- Die Änderung des Bearbeitungsstatus ist ausschließlich Verwaltungsmitarbeitern erlaubt.
- Eine Beschwerde kann mehrere Kommentare enthalten.
- Kommentare dürfen ausschließlich von Verwaltungsmitarbeitern hinzugefügt werden.
- Bürger können ihre eigenen Beschwerden einschließlich des aktuellen Status und der Kommentare einsehen.
- Die Methode `resolve()` setzt den Bearbeitungsstatus auf `RESOLVED`.
- Die Berechtigungen werden vor dem Aufruf der fachlichen Methoden durch die zuständige Anwendungslogik überprüft.

**Beziehungen:**

- Jede Beschwerde gehört genau einem Benutzer mit der Rolle `CITIZEN`.
- Ein Bürger kann mehrere Beschwerden erstellen.
- Eine Beschwerde kann null bis beliebig viele Kommentare enthalten.
- Jeder Kommentar gehört genau einer Beschwerde.
- Die Entity `Comment` ist Bestandteil des Complaint Aggregates und wird über die Aggregate Root `Complaint` verwaltet.
- Die Aggregate Root stellt sicher, dass Änderungen innerhalb des Aggregates kontrolliert durchgeführt werden.

**Zugehörige Domain Events:**

- `ComplaintCreated`: Eine neue Beschwerde wurde erfolgreich erstellt.
- `ComplaintStatusChanged`: Der Bearbeitungsstatus einer Beschwerde wurde geändert.
- `CommentAdded`: Ein Verwaltungsmitarbeiter hat einen Kommentar hinzugefügt.
- `ComplaintResolved`: Eine Beschwerde wurde als erledigt markiert.

`ComplaintStatusChanged` beschreibt allgemeine Statusänderungen. `ComplaintResolved` kennzeichnet zusätzlich den fachlichen Abschluss einer Beschwerde.

Die tatsächliche technische Erzeugung und Verarbeitung dieser Events wird in der späteren Implementierung berücksichtigt.

**DDD-Einordnung:**

`Complaint` ist die Aggregate Root und kontrolliert Änderungen am Bearbeitungsstatus sowie das Hinzufügen von Kommentaren.

Die Entity `Comment` wird als untergeordneter Bestandteil des Complaint Aggregates betrachtet.

Andere Komponenten greifen für Änderungen an der Beschwerde und ihren Kommentaren über die Aggregate Root zu. Dadurch bleiben die fachlichen Regeln innerhalb des Aggregates nachvollziehbar und konsistent.

#### 5.3 Comment – Complaint Management Context

**Typ:** Entity innerhalb des Complaint Aggregates

**Bounded Context:** Complaint Management Context

**Aggregate Root:** Complaint

**Beschreibung:**

Die Entity `Comment` repräsentiert einen Kommentar, den ein Mitarbeiter der Stadtverwaltung zu einer bestehenden Beschwerde hinzufügt.

Kommentare dienen dazu, Bürger über die Bearbeitung ihrer Anliegen zu informieren oder zusätzliche Informationen zur Beschwerde festzuhalten.

Jeder Kommentar besitzt eine eindeutige Identität, gehört genau einer Beschwerde und wird von einem Verwaltungsmitarbeiter verfasst.

`Comment` ist keine eigenständige Aggregate Root, sondern wird innerhalb des Complaint Aggregates verwaltet.

**Attribute:**

| Attribut | Datentyp | Beschreibung |
|---|---|---|
| id | String | Eindeutige Kommentar-ID |
| complaintId | String | ID der zugehörigen Beschwerde |
| authorId | String | ID des Verwaltungsmitarbeiters, der den Kommentar verfasst hat |
| text | String | Inhalt des Kommentars |
| createdAt | LocalDateTime | Zeitpunkt der Erstellung |

**Methoden:**

Die Entity `Comment` benötigt im aktuellen Domänenmodell keine eigenen fachlichen Methoden.

Das Hinzufügen eines Kommentars erfolgt über die Aggregate Root `Complaint`:

`Complaint.addComment(comment: Comment)`

Dadurch wird sichergestellt, dass Kommentare ausschließlich im Zusammenhang mit einer bestehenden Beschwerde verwaltet werden.

**Fachliche Regeln:**

- Jeder Kommentar besitzt eine eindeutige ID.
- Jeder Kommentar gehört genau einer Beschwerde.
- Ein Kommentar wird von genau einem Verwaltungsmitarbeiter verfasst.
- Nur Benutzer mit der Rolle `EMPLOYEE` dürfen Kommentare erstellen.
- Bürger dürfen Kommentare zu ihren eigenen Beschwerden einsehen, aber keine Kommentare hinzufügen.
- Der Kommentartext darf nicht leer sein.
- Der Erstellungszeitpunkt wird bei der Erstellung festgelegt.
- Kommentare werden über die Aggregate Root `Complaint` hinzugefügt.
- Die Prüfung der Benutzerberechtigung erfolgt vor dem Hinzufügen durch die zuständige Anwendungslogik.

**Beziehungen:**

- Eine `Complaint` kann null bis beliebig viele `Comment`-Objekte enthalten.
- Jeder `Comment` gehört genau einer `Complaint`.
- Jeder `Comment` besitzt mit `authorId` eine Referenz auf einen `User` mit der Rolle `EMPLOYEE`.
- Da `User` ein eigenständiges Aggregate ist, wird der Verfasser über seine ID referenziert.

**Zugehöriges Domain Event:**

`CommentAdded`: Ein Verwaltungsmitarbeiter hat einer bestehenden Beschwerde erfolgreich einen Kommentar hinzugefügt.

Das Event wird fachlich durch das Hinzufügen des Kommentars zum Complaint Aggregate ausgelöst.

Die konkrete technische Erzeugung und Verarbeitung des Events erfolgt in der späteren Implementierung.

**DDD-Einordnung:**

`Comment` ist eine untergeordnete Entity innerhalb des Complaint Aggregates.

Die Aggregate Root `Complaint` kontrolliert das Hinzufügen von Kommentaren und schützt damit die fachlichen Konsistenzregeln.

Ein direkter, unabhängiger Zugriff auf die Entity `Comment` zur Änderung ihres Zustands ist im aktuellen Modell nicht vorgesehen.

Die Zuordnung zu einer Beschwerde erfolgt innerhalb des Aggregates. Die `complaintId` dient dabei der eindeutigen Referenzierung und kann später für die Persistenz verwendet werden.

### 6. Beziehungen zwischen den Aggregates

Das CityFeedback-System besteht im aktuellen Domänenmodell aus zwei zentralen Aggregates: dem User Aggregate und dem Complaint Aggregate.

Die Aggregate sind fachlich miteinander verbunden, besitzen jedoch jeweils eigene Verantwortlichkeiten und Konsistenzgrenzen.

#### 6.1 Beziehung zwischen User und Complaint

Ein Benutzer mit der Rolle `CITIZEN` kann mehrere Beschwerden erstellen. Jede Beschwerde gehört genau einem Bürger.

Die Beziehung wird über das Attribut `citizenId` innerhalb der Entity `Complaint` hergestellt.

**Kardinalität:**
- Ein User (CITIZEN) kann 0 bis beliebig viele Complaints erstellen.
- Jede Complaint gehört genau einem User (CITIZEN).

Da `User` und `Complaint` eigenständige Aggregate Roots sind, wird der Bürger innerhalb der Beschwerde über seine ID referenziert.

#### 6.2 Beziehung zwischen Complaint und Comment

Eine Beschwerde kann mehrere Kommentare enthalten.

Jeder Kommentar gehört genau einer Beschwerde und ist Teil des Complaint Aggregates.

**Kardinalität:**
- Eine Complaint kann 0 bis beliebig viele Comments enthalten.
- Jeder Comment gehört genau einer Complaint.

Die Beziehung wird im UML-Klassendiagramm als Komposition dargestellt.

Kommentare werden über die Methode `Complaint.addComment()` zur Beschwerde hinzugefügt.

Damit kontrolliert die Aggregate Root die Änderungen an den zugehörigen Kommentaren.

#### 6.3 Beziehung zwischen User und Comment

Jeder Kommentar wird von genau einem Verwaltungsmitarbeiter verfasst.

Ein Verwaltungsmitarbeiter kann mehrere Kommentare erstellen.

**Kardinalität:**
- Ein User (EMPLOYEE) kann 0 bis beliebig viele Comments verfassen.
- Jeder Comment besitzt genau einen User (EMPLOYEE) als Verfasser.

Die Beziehung erfolgt über das Attribut `authorId` innerhalb der Entity `Comment`.

Da der Benutzer zu einem anderen Aggregate gehört, wird er über seine ID referenziert.

### 7. Konsistenzregeln und Aggregate-Grenzen

Aggregate definieren in Domain-Driven Design fachliche Konsistenzgrenzen.

#### 7.1 User Aggregate

Das User Aggregate verwaltet die Identität und Rolle eines Benutzers.

Folgende Regeln gelten:
- Jeder Benutzer besitzt eine eindeutige ID.
- Jeder Benutzer besitzt genau eine gültige Rolle.
- Passwörter werden nicht im Klartext, sondern als sicherer Hash (`passwordHash`) gespeichert.
- Die E-Mail-Adresse eines Benutzers muss eindeutig sein. Diese übergreifende Regel wird durch die Anwendungslogik und das Repository sichergestellt.

#### 7.2 Complaint Aggregate

Das Complaint Aggregate verwaltet eine Beschwerde und die zugehörigen Kommentare.

Folgende Regeln gelten:
- Jede Beschwerde besitzt eine eindeutige ID.
- Jede Beschwerde gehört genau einem Bürger.
- Neue Beschwerden beginnen mit dem Status `OPEN`.
- Es sind ausschließlich die Statuswerte `OPEN`, `IN_PROGRESS` und `RESOLVED` vorgesehen.
- Statusänderungen erfolgen kontrolliert über `changeStatus()` beziehungsweise `resolve()`.
- Kommentare werden ausschließlich über `addComment()` hinzugefügt.
- Ein Kommentar muss einen nicht leeren Text besitzen und einer bestehenden Beschwerde zugeordnet sein.

Die Berechtigungsprüfung erfolgt in der zuständigen Anwendungslogik. Dadurch wird sichergestellt, dass nur Verwaltungsmitarbeiter Statusänderungen durchführen und Kommentare hinzufügen dürfen.

#### 7.3 Kommunikation zwischen den Aggregates

Das User Aggregate und das Complaint Aggregate sollen möglichst unabhängig voneinander bleiben.

Daher werden Benutzer innerhalb der Beschwerden und Kommentare über ihre IDs referenziert.

Die Verknüpfung der Aggregate sowie notwendige Berechtigungsprüfungen erfolgen über die spätere Anwendungslogik.

Dadurch wird eine modulare und testbare Implementierung unterstützt.

### 8. Zusammenfassung

Das Domänenmodell von CityFeedback umfasst die Entities `User`, `Complaint` und `Comment`.

Es wurden zwei Aggregate definiert:

1. **User Aggregate:** Besteht aus der Aggregate Root `User` und gehört zum Identity Context.
2. **Complaint Aggregate:** Besteht aus der Aggregate Root `Complaint` sowie der untergeordneten Entity `Comment` und gehört zum Complaint Management Context.

Die Enums `UserRole` und `ComplaintStatus` definieren die vorgesehenen Rollen beziehungsweise Zustände.

Die Aggregate-Grenzen ermöglichen eine klare Trennung der Verantwortlichkeiten. Sie bilden die Grundlage für die spätere Definition von Domain Services, Repositories und die Implementierung der Anwendung.
