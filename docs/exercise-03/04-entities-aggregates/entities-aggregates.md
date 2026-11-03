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

### 6. Beziehungen zwischen den Aggregates

Die Beziehungen und Konsistenzregeln werden im weiteren Verlauf dokumentiert.
