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

### 6. Beziehungen zwischen den Aggregates

Die Beziehungen und Konsistenzregeln werden im weiteren Verlauf dokumentiert.
