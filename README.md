# Git Beginner Guide

## Was ist Git?
Git ist ein kostenloses, verteiltes Versionskontrollsystem. Es speichert die Historie eines Projektes und ermöglicht, Änderungen nachzuvollziehen, frühere Versionen wiederherzustellen und parallel an neuen Funktionen zu arbeiten.

### Vorteile von Git
- Versionskontrolle: Änderungen bleiben nachvollziehbar.
- Sicherheit: Frühere Projektstände können wiederhergestellt werden.
- Zusammenarbeit: Mehrere Entwickler können gleichzeitig am selben Projekt arbeiten.
- Parallele Entwicklung: Neue Funktionen lassen sich in separaten Branches entwickeln.
- Dokumentation: Commits zeigen, was geändert wurde und warum.
- Flexibilität: Git funktioniert auch ohne Internetverbindung mit einem lokalen Repository.

## Grundlagen
| Begriff | Bedeutung |
|---|---|
| Repository (Repo) | Projekt mit Dateien und Versionsgeschichte |
| Working Directory | Arbeitsverzeichnis, in dem du Dateien bearbeitest |
| Staging Area | Bereich, in dem du Änderungen für den nächsten Commit auswählst |
| Commit | Gespeicherter Projektstand mit einer Beschreibung |
| Branch | Separater Entwicklungszweig |
| Merge | Zusammenführen von Änderungen aus verschiedenen Branches |
| Remote Repository | Entferntes Repository, beispielsweise auf GitHub |
| Push | Lokale Commits zum Remote Repository übertragen |
| Pull | Änderungen aus einem Remote Repository abrufen und integrieren |
| Clone | Eine vorhandene Repository-Kopie erstellen |

## Grundlegende Git-Befehle

Die folgenden Befehle gehören zu den wichtigsten Git-Kommandos:

- `git init`  
  Erstellt ein neues lokales Git-Repository.

- `git clone <repository-url>`  
  Erstellt eine lokale Kopie eines vorhandenen Remote-Repositories.

- `git status`  
  Zeigt an, welche Dateien verändert wurden und welchen Status sie haben.

- `git add <datei>`  
  Fügt eine neue oder geänderte Datei zur Staging Area hinzu.

- `git commit -m "Nachricht"`  
  Speichert die vorgemerkten Änderungen als neuen Commit im lokalen Repository.

- `git push`  
  Überträgt lokale Commits in das Remote-Repository.

- `git pull`  
  Lädt Änderungen aus dem Remote-Repository und integriert sie in den aktuellen Branch.

### Branches und ihre Nutzung

Ein Branch ist ein separater Entwicklungszweig innerhalb eines Git-Repositories. Branches ermöglichen es, unabhängig vom Haupt-Branch (`main`) an neuen Funktionen oder Änderungen zu arbeiten. Dadurch können mehrere Teammitglieder gleichzeitig entwickeln, ohne sich gegenseitig zu beeinflussen.

Wichtige Befehle für die Arbeit mit Branches:

- `git branch` – Zeigt alle lokalen Branches an.
- `git switch -c feature-name` – Erstellt einen neuen Branch und wechselt direkt dorthin.
- `git switch main` – Wechselt zurück zum Haupt-Branch.
- `git push -u origin feature-name` – Überträgt den neuen Branch zum Remote-Repository.

**Typischer Workflow:**

1. Den aktuellen Stand des `main`-Branches mit `git pull` abrufen.
2. Einen neuen Branch für die geplante Änderung erstellen.
3. Änderungen durchführen und mit `git add` und `git commit` speichern.
4. Den Branch mit `git push` zu GitHub übertragen.
5. Einen Pull Request erstellen, um die Änderungen überprüfen und anschließend zusammenführen zu lassen.

### Merge

Beim Merge werden Änderungen aus verschiedenen Branches zusammengeführt. Beispielsweise kann eine fertig entwickelte Funktion aus einem Feature-Branch in den `main`-Branch übernommen werden.

Ein Merge kann lokal mit `git merge <branch-name>` oder direkt über einen Pull Request auf GitHub durchgeführt werden.

In unserem Projekt verwenden wir Pull Requests. Dadurch können Änderungen vor dem Zusammenführen von einem anderen Teammitglied überprüft werden. Nach erfolgreichem Review wird der Branch in den Haupt-Branch gemerged.

### Umgang mit Merge-Konflikten

Ein Merge-Konflikt entsteht, wenn Git Änderungen aus zwei Branches nicht automatisch zusammenführen kann. Dies passiert beispielsweise, wenn zwei Teammitglieder dieselbe Zeile einer Datei unterschiedlich bearbeiten.

**So kann ein Merge-Konflikt gelöst werden:**

1. Mit `git status` prüfen, welche Dateien betroffen sind.
2. Die betroffenen Dateien öffnen und die widersprüchlichen Änderungen vergleichen.
3. Entscheiden, welche Änderungen übernommen werden sollen, und die Konfliktmarkierungen entfernen.
4. Die korrigierten Dateien mit `git add` zur Staging Area hinzufügen.
5. Den Merge mit `git commit` abschließen, sofern Git nicht bereits einen anderen Abschlussschritt vorgibt.

Merge-Konflikte lassen sich reduzieren, indem Teammitglieder regelmäßig Änderungen abrufen, in eigenen Branches arbeiten und Änderungen frühzeitig über Pull Requests zusammenführen.

##  Local Repository vs. Remote Repository
Ein **Local Repository** befindet sich auf dem eigenen Rechner. Änderungen können dort bearbeitet, zur Staging Area hinzugefügt und anschließend mit einem Commit gespeichert werden. Dafür ist keine dauerhafte Internetverbindung notwendig.

Ein **Remote Repository** liegt auf einem entfernten Server, beispielsweise bei GitHub. Es dient dazu, den aktuellen Projektstand mit anderen Teammitgliedern zu teilen und gemeinsam am Projekt zu arbeiten.

Die wichtigsten Vorgänge zwischen beiden Repositorys sind:

- `git clone`: Erstellt eine lokale Kopie eines Remote-Repositories.
- `git push`: Überträgt lokale Commits zum Remote-Repository.
- `git pull`: Ruft Änderungen aus dem Remote-Repository ab und integriert sie in den lokalen Branch.

### Git mit IntelliJ/PyCharm

IntelliJ IDEA und PyCharm bieten eine grafische Git-Integration. Dadurch können viele typische Git-Aufgaben direkt über die Entwicklungsumgebung ausgeführt werden.

Zum Beispiel können:

- Remote-Repositories geklont werden,
- Änderungen an Dateien angezeigt werden,
- Commits erstellt werden,
- Branches angelegt und gewechselt werden,
- Änderungen mit `push` zu GitHub übertragen werden,
- neue Änderungen mit `pull` aus dem Remote-Repository geladen werden.

Die eigentliche Arbeit an den Dateien findet dabei im lokalen Repository statt. GitHub dient als Remote-Repository für den Austausch und die Zusammenarbeit im Team.

## Plattformen und Tools

Für die Arbeit mit Git gibt es verschiedene Plattformen und Werkzeuge, welche die Versionsverwaltung und Zusammenarbeit erleichtern.

- **GitHub:** Eine Plattform zum Speichern und gemeinsamen Bearbeiten von Git-Repositories. GitHub ermöglicht unter anderem die Verwaltung von Branches, Pull Requests, Code Reviews und Issues.
- **GitLab:** Eine Alternative zu GitHub mit ähnlichen Funktionen zur Zusammenarbeit und Versionsverwaltung. Zusätzlich bietet GitLab integrierte Möglichkeiten für CI/CD.
- **Git Bash:** Eine Kommandozeilenumgebung, mit der Git-Befehle insbesondere unter Windows ausgeführt werden können.
- **GitHub Desktop:** Eine grafische Benutzeroberfläche, über die typische Git-Aktionen wie Commits, Branch-Wechsel und Push/Pull ohne Kommandozeile durchgeführt werden können.
- **IntelliJ IDEA / PyCharm:** Entwicklungsumgebungen mit integrierter Git-Unterstützung. Sie ermöglichen beispielsweise das Erstellen von Commits, die Verwaltung von Branches und das Synchronisieren mit Remote-Repositories.

Für unser Projekt **CityFeedback** verwenden wir GitHub als zentrale Plattform zur Versionsverwaltung. Die Entwicklung erfolgt in eigenen Branches. Änderungen werden anschließend über Pull Requests überprüft und in den `main`-Branch integriert.

---------------------------------------------------------------------------------------------------------------------------------------------------------------
# Übung 2 – CI/CD

## Grundlagen und Nutzen von CI/CD

**Continuous Integration (CI)** bedeutet, dass Änderungen am Quellcode regelmäßig in ein gemeinsames Repository integriert und automatisch überprüft werden. Beispielsweise kann bei jedem Push oder Pull Request ein automatischer Build gestartet werden. Dadurch lassen sich Integrationsprobleme frühzeitig erkennen.

**Continuous Deployment (CD)** geht einen Schritt weiter. Erfolgreich geprüfte Änderungen können automatisch auf einer Hosting-Plattform bereitgestellt werden, sodass die aktuelle Version der Anwendung verfügbar ist.

### Nutzen für CityFeedback

Für unser Projekt CityFeedback bietet CI/CD folgende Vorteile:

- **Frühzeitige Fehlererkennung:** Probleme beim Zusammenführen von Änderungen können schneller erkannt werden.
- **Einfachere Zusammenarbeit:** Änderungen der Teammitglieder werden über GitHub und Pull Requests nachvollziehbar integriert.
- **Automatisierung:** Wiederkehrende Schritte wie das Bauen der Anwendung müssen nicht jedes Mal manuell ausgeführt werden.
- **Nachvollziehbarkeit:** GitHub Actions dokumentiert die Ergebnisse der Pipeline.
- **Spätere Erweiterbarkeit:** Die Pipeline kann zukünftig um automatisierte Tests und ein Deployment ergänzt werden.

Aktuell verwenden wir GitHub Actions zur Ausführung unserer CI-Pipeline. Der automatische Maven-Build ist erfolgreich in GitHub Actions integriert. Automatisierte Tests und ein Deployment sind für spätere Projektphasen vorgesehen.

## Verwendete CI/CD-Plattform

Für die Continuous-Integration-Pipeline verwenden wir **GitHub Actions**.

Die Wahl fiel auf GitHub Actions, da sich unser Repository bereits auf GitHub befindet und die CI-Pipeline dadurch direkt in die bestehende Entwicklungsumgebung integriert werden kann. Ein zusätzlicher externer CI-Server muss dadurch nicht eingerichtet oder verwaltet werden.

Die Konfiguration der Pipeline befindet sich im Repository unter:

`.github/workflows/ci.yml`

Status: ![Java CI](https://github.com/janniswa/moderneSoftwareentwicklung/actions/workflows/ci.yml/badge.svg)

## Aktueller Ablauf der CI-Pipeline

Für die automatisierte Continuous Integration verwenden wir GitHub Actions in Verbindung mit Java 17 und Maven.

Die Pipeline wird automatisch gestartet, wenn Änderungen in das Repository gepusht werden oder ein Pull Request erstellt beziehungsweise aktualisiert wird.

Der Ablauf besteht aus folgenden Schritten:

1. **Workflow starten:** GitHub Actions erkennt einen Push oder Pull Request und startet automatisch die CI-Pipeline.
2. **Repository auschecken:** Der aktuelle Quellcode wird mit `actions/checkout` geladen.
3. **Java einrichten:** Die Entwicklungsumgebung wird mit Java 17 vorbereitet.
4. **Maven-Build ausführen:** Maven führt im Projektordner `cityfeedback` automatisch den Build-Prozess aus.
5. **Ergebnis anzeigen:** GitHub Actions zeigt an, ob die Pipeline erfolgreich durchgelaufen ist.

Der Maven-Build wird mit folgendem Befehl ausgeführt:

`mvn -B -Dmaven.test.skip=true package`

Da automatisierte Tests zu diesem Zeitpunkt noch nicht Bestandteil der Übung sind, werden diese beim Build vollständig übersprungen.

Die erfolgreiche Ausführung des automatischen Maven-Builds wurde über GitHub Actions überprüft. Der Workflow wurde erfolgreich abgeschlossen.

Aktuell wird damit der Maven-Build-Prozess automatisiert. Die vollständige Implementierung der CityFeedback-Anwendung erfolgt im weiteren Projektverlauf.

## Geplante Erweiterungen der CI-Pipeline

Im weiteren Projektverlauf soll die Pipeline schrittweise erweitert werden.

Geplant sind insbesondere:

- Integration automatisierter Tests zur Überprüfung der Anwendungslogik,
- gegebenenfalls zusätzliche Qualitätssicherungsmaßnahmen wie Linting oder Code-Coverage-Auswertungen,
- langfristig die Verknüpfung der CI-Pipeline mit einem automatisierten Deployment.

Ein automatisches Deployment ist zum aktuellen Zeitpunkt noch nicht umgesetzt. Das geplante Deployment-Konzept wird im folgenden Abschnitt beschrieben.

## Geplantes Deployment-Konzept

Für die spätere Bereitstellung von CityFeedback wird aktuell ein cloudbasiertes Deployment in Betracht gezogen.

Als mögliche Plattform wird derzeit **Render** betrachtet. Die Anwendung soll weiterhin über GitHub verwaltet und der bestehende GitHub-Actions-Workflow für die Continuous Integration genutzt werden.

Ein möglicher zukünftiger Deployment-Ablauf könnte wie folgt aussehen:

1. Änderungen werden zunächst in einem eigenen Git-Branch entwickelt.
2. Die Änderungen werden über einen Pull Request in das Repository eingebracht.
3. GitHub Actions führt die konfigurierte CI-Pipeline aus.
4. Nach erfolgreicher Prüfung wird der Pull Request in den `main`-Branch gemerged.
5. Änderungen am `main`-Branch könnten anschließend ein automatisches Deployment auf Render auslösen.
6. Render stellt die aktuelle Version der CityFeedback-Anwendung als Webanwendung bereit.
7. Für die persistente Speicherung der Anwendungsdaten könnte eine PostgreSQL-Datenbank verwendet werden.

Der geplante Ablauf lässt sich vereinfacht folgendermaßen darstellen:

`Entwicklung → GitHub Branch → Pull Request → GitHub Actions → main → Render → CityFeedback → PostgreSQL`

### Vorteile

Die Verwendung von Render bietet für das Projekt mehrere mögliche Vorteile:

- direkte Anbindung an ein GitHub-Repository,
- Unterstützung automatischer Deployments,
- geeignet für die Bereitstellung von Webanwendungen,
- Möglichkeit, eine PostgreSQL-Datenbank für die persistente Speicherung einzusetzen,
- überschaubare Infrastruktur für ein Hochschulprojekt.

### Nachteile und offene Punkte

Gleichzeitig müssen einige Aspekte berücksichtigt werden:

- Abhängigkeit von einem externen Cloud-Anbieter,
- die konkrete Deployment-Konfiguration hängt vom später eingesetzten Technologie-Stack ab,
- Kosten, Skalierbarkeit und Plattformbedingungen müssten bei einer langfristigen Nutzung erneut bewertet werden.

### Vorläufige Entscheidung

Das beschriebene Deployment mit **Render und PostgreSQL stellt aktuell lediglich eine mögliche Lösung dar und ist noch keine endgültige Technologieentscheidung**.

Im weiteren Projektverlauf soll geprüft werden, ob diese Variante zum tatsächlich verwendeten Technologie-Stack und zu den Anforderungen von CityFeedback passt. Bei Bedarf kann die Deployment-Plattform entsprechend angepasst werden.

---------------------------------------------------------------------------------------------------------------------------------------------------------------
# Übung 3 – Systemarchitektur

## 01 Event Storming

Die Event-Storming-Session dient dazu, die wichtigsten Abläufe im Projekt sichtbar zu machen. Dabei wurden folgende Events identifiziert und den notwendigen Interaktionen bzw. Triggern zugeordnet:

### 1. UserRegistered
Dieses Event wird ausgelöst, wenn sich ein neuer Bürger erfolgreich bei CityFeedback registriert hat. Der Benutzer wird im System angelegt und kann anschließend die Funktionen der Anwendung nutzen.

### 2. UserLoggedIn
Dieses Event wird ausgelöst, wenn sich ein Benutzer erfolgreich im System anmeldet. Der Benutzer erhält Zugriff auf die für seine Rolle verfügbaren Funktionen.

### 3. ComplaintCreated
Dieses Event wird ausgelöst, wenn ein Bürger eine neue Beschwerde erstellt und diese erfolgreich im System gespeichert wurde. Die Beschwerde kann anschließend von der Verwaltung eingesehen und bearbeitet werden.

### 4. CommentAdded
Dieses Event wird ausgelöst, wenn ein Verwaltungsmitarbeiter einen Kommentar zu einer bestehenden Beschwerde hinzufügt. Dadurch können Details zur Schließung oder weiteren Bearbeitung festgehalten werden. Der Bürger kann die Kommentare anschließend einsehen.

### 5. ComplaintStatusChanged
Dieses Event wird ausgelöst, wenn sich der Bearbeitungsstatus einer Beschwerde ändert. Dies kann beispielsweise der Wechsel von „Offen“ (`OPEN`) zu „In Bearbeitung“ (`IN_PROGRESS`) sein. Das Event dokumentiert den aktuellen Fortschritt der Bearbeitung, ohne dass die Beschwerde bereits abgeschlossen sein muss.

### 6. ComplaintResolved
Dieses Event wird ausgelöst, wenn die Verwaltung eine Beschwerde erfolgreich bearbeitet und das Anliegen des Bürgers als gelöst betrachtet. Damit ist der Bearbeitungsprozess der Beschwerde abgeschlossen. Das Event kennzeichnet den erfolgreichen Abschluss und kann beispielsweise dazu genutzt werden, den Bürger über die Lösung seines Anliegens zu informieren.

Die Events zeigen den Lebenszyklus einer Beschwerde von der Erstellung bis zur Lösung:

[Abbildung: Event Storming Eventübersicht](docs/exercise-03/01-event-stroming/Event-Storming_Events.png)

Im weiteren Verlauf haben wir die Events den fachlichen Verantwortungsbereichen zugeordnet. Dabei wurde deutlich, dass die Benutzerverwaltung zum Identity Context gehört, während die Erstellung und Bearbeitung von Beschwerden einschließlich der Kommentare dem Complaint Management Context zugeordnet wird.

Das entstandene Event-Storming-Diagramm veranschaulicht die wichtigsten Abläufe und bildet die Grundlage für unser Domänenmodell sowie die Identifikation der Bounded Contexts.

[Abbildung: Event Storming Prozess](docs/exercise-03/01-event-stroming/Event-Storming_Prozess.png)

## 02 Domänenmodell

Das Domänenmodell für CityFeedback wurde als UML-Klassendiagramm
mit PlantUML erstellt.

Es umfasst die Entities User, Complaint und Comment sowie die
Enums UserRole und ComplaintStatus.

Das Diagramm stellt die Attribute, Methoden, Beziehungen und
Kardinalitäten der wichtigsten Domänenobjekte dar.

- [UML-Klassendiagramm](docs/exercise-03/02-domain-model/DomainModel.png)
- [PlantUML-Quelldatei](docs/exercise-03/02-domain-model/domain-model.puml)


## 03 Bounded Contexts

Auf Grundlage des erstellten Domänenmodells wird CityFeedback in zwei Bounded Contexts unterteilt. Dadurch werden die fachlichen Verantwortlichkeiten klar voneinander abgegrenzt und die Komplexität der Anwendung reduziert. Die Aufteilung orientiert sich an den im Domänenmodell definierten Entitäten, Aggregates und deren Beziehungen.

### Identity Context

Der Identity Context ist für die Verwaltung der Benutzer zuständig. Die zentrale Entity und gleichzeitig der Aggregate Root ist User. Ein Benutzer besitzt Attribute wie eine ID, einen Namen, eine E-Mail-Adresse, einen Passwort-Hash und eine Benutzerrolle. Die Rollen werden durch das Enum UserRole mit den Ausprägungen CITIZEN und EMPLOYEE festgelegt. Der Context ist für die Registrierung und Anmeldung sowie die Verwaltung der Benutzerrollen verantwortlich.

### Complaint Management Context

Der Complaint Management Context bildet den zentralen fachlichen Bereich von CityFeedback. Die Entity Complaint ist gleichzeitig der Aggregate Root und verwaltet die wesentlichen Informationen einer Beschwerde. Dazu gehören unter anderem Titel, Beschreibung, Kategorie, Status und Erstellungszeitpunkt. Die Entity Comment ist dem Complaint Aggregate untergeordnet und wird direkt einer Beschwerde zugeordnet. Dadurch liegen sowohl die Verwaltung der Beschwerden als auch die zugehörigen Kommentare und Statusänderungen vollständig in diesem Context. Der Beschwerdestatus wird durch das Enum ComplaintStatus mit den Werten OPEN, IN_PROGRESS und RESOLVED abgebildet.

### Beziehungen zwischen den Bounded Contexts

Der Identity Context stellt die Benutzerinformationen und Rollen bereit, die im Complaint Management Context benötigt werden. So kann beispielsweise geprüft werden, ob ein Benutzer als Bürger eine Beschwerde erstellen oder als Mitarbeiter einen Kommentar verfassen darf. Die beiden Contexts besitzen damit klar getrennte Verantwortlichkeiten, arbeiten bei der Zuordnung von Benutzern zu Beschwerden und Kommentaren jedoch zusammen.

### Darstellung der Bounded Contexts

Die folgende Abbildung zeigt die Aufteilung von CityFeedback in den Identity Context und den Complaint Management Context sowie deren fachliche Beziehungen. Die Zuordnung der Kommentare zum Complaint Management Context entspricht dabei der Struktur des erstellten Domänenmodells.

[Abbildung: Bounded Contexts](docs/exercise-03/03-bounded-context/Bounded_Contexts.png)

## 04 Entitäten und Aggregate

Für CityFeedback wurden zwei zentrale Aggregate definiert:

- User Aggregate mit User als Aggregate Root
- Complaint Aggregate mit Complaint als Aggregate Root und
  Comment als untergeordneter Entity

Die detaillierte Dokumentation beschreibt die Attribute,
Methoden, Beziehungen, fachlichen Regeln und
Konsistenzgrenzen der Aggregate.

[Dokumentation: Entities und Aggregates](docs/exercise-03/04-entities-aggregates/entities-aggregates.md)


## 05 Domain Services und Repositories

### 05.1 Services (Anwendungsservices)

Für CityFeedback werden zwei Anwendungsservices vorgesehen,
die die Abläufe zwischen den Domänenobjekten und Repositories
koordinieren.

Die eigentlichen fachlichen Regeln werden möglichst direkt
in den Aggregates umgesetzt. Ein zusätzlicher Domain Service
ist für die bisherigen Anwendungsfälle nicht erforderlich.

#### AuthenticationService (Identity Context)

Der AuthenticationService gehört zum Identity Context und
ist für die Registrierung und Anmeldung von Benutzern zuständig.

- `register(name, email, password, role)` – Registriert einen neuen Benutzer und speichert dessen Passwort ausschließlich als sicheren Hash.
- `login(email, password)` – Überprüft die Anmeldedaten.

#### ComplaintService (Complaint Management Context)

Der ComplaintService gehört zum Complaint Management Context
und koordiniert die Erstellung und Bearbeitung von Beschwerden.

- `createComplaint(userId, title, description, category)` – Erstellt und speichert eine neue Beschwerde.
- `getComplaintById(id)` – Ruft eine Beschwerde anhand ihrer ID ab.
- `getComplaintsByUserId(userId)` – Ruft die Beschwerden eines Benutzers ab.
- `changeStatus(complaintId, newStatus)` – Ändert den Bearbeitungsstatus einer Beschwerde.
- `resolveComplaint(complaintId)` – Markiert eine Beschwerde als erledigt.
- `addComment(complaintId, employeeId, text)` – Fügt einer bestehenden Beschwerde einen Kommentar eines Verwaltungsmitarbeiters hinzu.

Der ComplaintService prüft die erforderlichen Berechtigungen,
lädt die betreffende Beschwerde über das ComplaintRepository,
ruft die entsprechenden Methoden der Aggregate Root
`Complaint` auf und speichert anschließend die Änderungen.

Die fachlichen Methoden `changeStatus()`, `resolve()` und
`addComment()` bleiben dabei innerhalb des Complaint Aggregates.


### 05.2 Repositories

Repositories übernehmen die Speicherung und Abfrage der Domänenobjekte.

#### UserRepository (Identity Context)
Das UserRepository gehört zum Identity Context und verwaltet die Benutzer.

- save(user) – Speichert einen Benutzer.
- findById(id) – Sucht einen Benutzer anhand seiner ID.
- findByEmail(email) – Sucht einen Benutzer anhand seiner E-Mail-Adresse.

#### ComplaintRepository (Complaint Management Context)
Das ComplaintRepository gehört zum Complaint Management Context und verwaltet die Beschwerden.

- save(complaint) – Speichert eine Beschwerde.
- findById(id) – Sucht eine Beschwerde anhand ihrer ID.
- findAll() – Ruft alle Beschwerden ab.
- findByUserId(userId) – Ruft die Beschwerden eines Benutzers ab.

Ein separates CommentRepository ist nicht erforderlich, da Kommentare als untergeordnete Entities zum Complaint-Aggregate gehören und gemeinsam mit der Beschwerde verwaltet werden.

## 06  Implementierungsstrategie

### 06.1 Geplante Projektstruktur
Die Anwendung wird mit Java und Maven umgesetzt. Die beiden Bounded Contexts Identity und Complaint Management werden jeweils in drei Bereiche unterteilt:

- domain: Entities, Enums, Domain Events und Repository-Schnittstellen.
- application: Services zur Koordination der Anwendungsfälle.
- infrastructure: Technische Implementierungen, beispielsweise für die Datenspeicherung.

Vorgeschlagene Package-Struktur:


```text
cityfeedback/
├── identity/
│   ├── domain/
│   ├── application/
│   └── infrastructure/
│
└── complaint/
    ├── domain/
    ├── application/
    └── infrastructure/
```


### 06.2 Beispiel einer Domain-Klasse

Die Klasse Complaint bildet den Aggregate Root des Complaint-Aggregates. Sie verwaltet die Beschwerdedaten und die zugehörigen Kommentare.

Beispielhafte Java-Implementierung:

```java
public class Complaint {

    private final String id;
    private final String citizenId;
    private final String title;
    private final String description;
    private final String category;
    private ComplaintStatus status;
    private final LocalDateTime createdAt;

    private final List<Comment> comments = new ArrayList<>();

    public Complaint(String id, String citizenId,
                     String title, String description,
                     String category) {

        if (title == null || title.trim().length() < 3) {
            throw new IllegalArgumentException(
                "Titel muss mindestens 3 Zeichen enthalten.");
        }

        if (description == null || description.trim().length() < 10) {
            throw new IllegalArgumentException(
                "Beschreibung muss mindestens 10 Zeichen enthalten.");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException(
                "Kategorie muss angegeben werden.");
        }

        this.id = id;
        this.citizenId = citizenId;
        this.title = title.trim();
        this.description = description.trim();
        this.category = category;
        this.status = ComplaintStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    public void changeStatus(ComplaintStatus newStatus) {
        this.status = newStatus;
    }

    public void resolve() {
        this.status = ComplaintStatus.RESOLVED;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
<CodeBlock language="markdown" editable>
```

Das Beispiel zeigt, dass die Validierung direkt bei der Erstellung erfolgt und Statusänderungen sowie Kommentare über Methoden des Complaint-Aggregates verwaltet werden.

### 06.3 Testbarkeit und Modularität

Für die Umsetzung werden Maven als Build-Tool und JUnit 5 als Testframework verwendet. Die Entwicklung erfolgt nach dem Test-Driven-Development-Prinzip: Zuerst werden Tests geschrieben, anschließend wird die passende Implementierung erstellt und abschließend refaktorisiert. Durch die Trennung der fachlichen Logik von technischen Komponenten können die einzelnen Bereiche unabhängig getestet werden. GitHub Actions führt die automatisierten Tests bei jedem Push und Pull Request aus.


### 06.4 Geplante Implementierungsreihenfolge

Die Implementierung von CityFeedback erfolgt schrittweise auf
Grundlage der definierten Domain Events und Bounded Contexts.

Wir beginnen mit der zentralen Domänenlogik und erweitern
anschließend die Anwendung um weitere Funktionen.

1. **ComplaintCreated – Beschwerde erstellen**
   - Complaint-Entity mit Validierung implementieren.
   - Titel, Beschreibung und Kategorie überprüfen.
   - Automatisierte JUnit-Tests erstellen.
   - Neue Beschwerden erhalten den Status OPEN.

2. **UserRegistered und UserLoggedIn – Benutzerverwaltung**
   - User-Entity und UserRole implementieren.
   - Registrierung und Anmeldung vorbereiten.
   - Passwörter ausschließlich als sichere Hashes speichern.
   - Benutzerrollen bei der Anmeldung berücksichtigen.

3. **ComplaintStatusChanged – Statusänderung**
   - Die Methode changeStatus() implementieren.
   - Gültige Statuswerte und Statuswechsel überprüfen.
   - Berechtigungen für Verwaltungsmitarbeiter berücksichtigen.
   - Passende Unit-Tests erstellen.

4. **CommentAdded – Kommentare hinzufügen**
   - Comment-Entity implementieren.
   - Kommentare über Complaint.addComment() hinzufügen.
   - Sicherstellen, dass nur Verwaltungsmitarbeiter
     Kommentare erstellen dürfen.
   - Die Kommentarfunktion mit Unit-Tests absichern.

5. **ComplaintResolved – Beschwerde abschließen**
   - Die Methode resolve() implementieren.
   - Den Status auf RESOLVED setzen.
   - Den fachlichen Abschluss einer Beschwerde abbilden.
   - Den Abschluss mit Unit-Tests überprüfen.

Die Implementierung erfolgt nach dem TDD-Prinzip:
Zuerst werden Tests geschrieben, anschließend wird die
Domänenlogik implementiert und danach bei Bedarf refaktorisiert.

Die Aggregate Roots User und Complaint bilden die Grundlage.
Repositories und Anwendungsservices werden schrittweise ergänzt,
um die fachlichen Abläufe miteinander zu verbinden.

Die Benutzeroberfläche wird in einer späteren Projektphase
entwickelt.

### 06.5 Zusammenfassung

Die gewählte Architektur trennt die fachlichen Verantwortlichkeiten und ermöglicht eine modulare Entwicklung. Das Complaint-Aggregate bildet den Kern der Anwendung. Durch Java, Maven, JUnit 5 und die CI-Pipeline soll CityFeedback wartbar, testbar und erweiterbar bleiben.

---------------------------------------------------------------------------------------------------------------------------------------------------------------
# Übung 4 –  Advanced Java, Test-Driven Design (TDD) und LLM-gestütztes Entwickeln

## 01 LLM Review der Implementierungsstrategie

Für das Review unserer Implementierungsstrategie aus Übung 3 haben wir Claude die beiden Bounded Contexts Identity und Complaint Management sowie das geplante Complaint-Aggregate vorgestellt. Claude schlug fünf Verbesserungen zu Factory-Methode, Validierung, Domain Events, Testbarkeit und Entwicklungsreihenfolge vor, die wir kritisch bewertet haben. Als wichtigste Domain Events priorisieren wir `ComplaintCreated` (Beschwerde erstellt) und `ComplaintStatusChanged` (Beschwerdestatus geändert), weil sie den zentralen Ablauf unserer Anwendung abbilden. Auf Basis des Reviews setzen wir zunächst `ComplaintCreated` testgetrieben um, prüfen anschließend die Statuswechsel und ergänzen Benutzerverwaltung sowie Kommentare schrittweise. Übernommen haben wir die statische Factory-Methode `Complaint.create()`, die Validierung im Aggregate, das `ComplaintCreated`-Event als Java-Record sowie JUnit-Tests vor der eigentlichen Implementierung. Zusätzliche Value Objects, Fakes und eine komplexe Event-Veröffentlichung haben wir wegen des geringen Mehrwerts für den aktuellen Projektumfang zunächst zurückgestellt.

Folgenden Prompt haben wir verwendet

> Wir entwickeln im Rahmen unseres Projekts „CityFeedback“ eine Anwendung, mit der Bürger Beschwerden einreichen und Mitarbeiter der Stadt diese bearbeiten können.
> Unsere bisherige Architektur basiert auf zwei Bounded Contexts:
> - Identity Context: Verwaltung von Benutzern und Rollen.
> - Complaint Management Context: Verwaltung von Beschwerden und deren Status.
> 
> Das Aggregate Root im Complaint Management Context ist Complaint. Eine Beschwerde besitzt folgende Attribute: ID, CitizenId, Titel, Beschreibung, Kategorie, Status und Erstellungsdatum. Der initiale Status ist OPEN.
> Bei der Erstellung einer Beschwerde soll das Domain Event ComplaintCreated entstehen. Die Eingaben Titel, Beschreibung und Kategorie müssen validiert werden.
> Wir möchten die Anwendung mit Java entwickeln und JUnit 5 für Tests verwenden. Dabei wollen wir testgetrieben vorgehen (TDD).
> Bitte überprüfe unsere bisherige Implementierungsstrategie kritisch und mache mindestens drei konkrete Verbesserungsvorschläge. Berücksichtige dabei insbesondere:
> 1. Aufbau und Verantwortlichkeiten des Complaint-Aggregates
> 2. Validierung der Eingaben
> 3. Erstellung und Behandlung des Domain Events
> 4. Testbarkeit und sinnvolle JUnit-Tests
> 5. Eine geeignete Reihenfolge der Implementierung
> Begründe deine Vorschläge und erkläre jeweils, welche Vor- und Nachteile sie haben. Berücksichtige, dass wir zunächst nur die Beschwerdeerstellung umsetzen und die Lösung nicht unnötig komplex gestalten möchten.

Als Antwort erhielten wir 5 Verbesserungsvorschläge [vollständige Antwort siehe hier](docs/exercise-04/01-llm-analyse/01_Claude_Analyse.pdf)

### Kritische Bewertung der fünf Claude-Vorschläge

| Claude-Vorschlag | Bewertung und Entscheidung | Begründung |
|---|---|---|
| **1. Factory & Value Objects** | Teilweise übernehmen | Eine einfache Factory-Methode zur Erstellung einer Beschwerde ist sinnvoll. Auf zusätzliche Value Objects und einen aufwendigen Aufbau verzichten wir, da diese für die aktuelle Funktionalität keinen ausreichenden Mehrwert bieten. |
| **2. Domänenvalidierung** | Übernehmen, aber einfach halten | Die wichtigsten Eingaben wie Titel, Beschreibung und Kategorie werden direkt bei der Erstellung geprüft. Eine aufwendige Validierungslogik oder das gleichzeitige Sammeln mehrerer Fehler ist für unser Projekt nicht notwendig. |
| **3. Domain Events** | Vereinfachte Umsetzung | Das Event `ComplaintCreated` wird bei erfolgreicher Erstellung berücksichtigt. Auf eine komplexe Event-Veröffentlichung oder ein eigenes Event-System verzichten wir, da zunächst nur die Entstehung des Events gezeigt werden soll. |
| **4. TDD & Fakes** | TDD übernehmen, Fakes zunächst weglassen | Wir schreiben unsere JUnit-Tests vor der Implementierung und prüfen gültige sowie ungültige Eingaben. Auf Fakes verzichten wir zunächst, da wir bei der Beschwerdeerstellung keine aufwendigen Abhängigkeiten simulieren müssen. |
| **5. Inside-out-Entwicklung** | Übernehmen | Wir beginnen mit der Domänenlogik und den zugehörigen Tests. Danach ergänzen wir den Application Service. Eine frühzeitige Umsetzung der Infrastruktur ist nicht notwendig. |


## 02 Testfälle mit LLM

### Bewertung der Testfälle mit Claude (JW)

Für die Entwicklung der ersten Domänenlogik haben wir Claude verwendet, um geeignete JUnit-5-Testfälle für die Erstellung einer Beschwerde zu erarbeiten. Dabei haben wir Happy-Path-, Edge-Case- und Negativtests für die Pflichtfelder, Mindestlängen, den initialen Status, das Erstellungsdatum und das Domain Event `ComplaintCreated` erstellen lassen. Die vorgeschlagenen Testfälle haben wir einzeln bewertet und größtenteils übernommen; auf einen separaten Test für das Ausbleiben eines Events bei ungültigen Eingaben haben wir zunächst verzichtet, da dieser in unserer aktuellen Implementierung keinen eigenständigen Mehrwert bietet. Zusätzlich haben wir mit ChatGPT den regulären Ausdruck `(?s)^.*\S.*$` für die Kategorievalidierung geprüft, der normale Texte akzeptiert und Eingaben aus gewöhnlichen Leerzeichen ablehnt. Bei der Überprüfung zeigte sich jedoch, dass bestimmte Unicode-Leerzeichen wie `U+2003` ohne zusätzliche Regex-Optionen fälschlicherweise akzeptiert werden. Deshalb haben wir uns gegen eine Regex-Implementierung entschieden, da `isBlank()`, `strip()` und einfache Längenvergleiche für unsere Anforderungen verständlicher und zuverlässiger sind. Anschließend haben wir die ausgewählten Tests vor der Implementierung der Beschwerdeerstellung geschrieben und im weiteren Verlauf erweitert, sodass zuletzt alle 30 JUnit-Tests erfolgreich mit `mvn test` ausgeführt wurden.

| Nr. | Testfall | Testart | Bewertung | Entscheidung und Begründung |
|---|---|---|---|---|
| 1 | `erstelltBeschwerde_mitGueltigenEingaben` | Happy Path | Sehr sinnvoll | Übernehmen. Prüft die grundlegende erfolgreiche Erstellung einer Beschwerde. |
| 2 | `neueBeschwerde_hatStatusOpen` | Happy Path | Sehr sinnvoll | Übernehmen. Stellt sicher, dass neue Beschwerden automatisch den Status `OPEN` erhalten. |
| 3 | `neueBeschwerde_setztErstellungsdatumAutomatisch` | Happy Path | Sinnvoll | Übernehmen. Prüft, ob das Erstellungsdatum automatisch gesetzt wird. |
| 4 | `erfolgreicheErstellung_erzeugtComplaintCreatedEvent` | Happy Path | Sehr sinnvoll | Übernehmen. Prüft, ob bei erfolgreicher Erstellung das Domain Event `ComplaintCreated` entsteht. |
| 5 | `titel_mitGenau3Zeichen_istGueltig` | Edge Case | Sehr sinnvoll | Übernehmen. Prüft die untere Grenze der erlaubten Titellänge. |
| 6 | `beschreibung_mitGenau10Zeichen_istGueltig` | Edge Case | Sehr sinnvoll | Übernehmen. Prüft die untere Grenze der erlaubten Beschreibungslänge. |
| 7 | `kategorie_mitEinemZeichen_istGueltig` | Edge Case | Sinnvoll | Übernehmen. Da keine feste Kategorienliste oder Mindestlänge definiert ist, reicht ein nicht leerer Wert. |
| 8 | `titel_mitNurLeerzeichenAberLaenge3_istUngueltig` | Edge Case | Sehr sinnvoll | Übernehmen. Verhindert, dass ein Titel trotz ausreichender Länge akzeptiert wird, obwohl er nur aus Leerzeichen besteht. |
| 9 | `beschreibung_mitNurLeerzeichenAberLaenge10_istUngueltig` | Edge Case | Sehr sinnvoll | Übernehmen. Prüft dieselbe Problematik für die Beschreibung. |
| 10 | `titel_mitZuWenigZeichen_wirdAbgelehnt` | Negative Test | Sehr sinnvoll | Übernehmen. Prüft, ob Titel unterhalb der Mindestlänge abgelehnt werden. |
| 11 | `titel_istNullLeerOderBlank_wirdAbgelehnt` | Negative Test | Sehr sinnvoll | Übernehmen. Die verschiedenen Eingaben (`null`, leer, Leerzeichen) werden mit einem parametrisierten Test geprüft. |
| 12 | `beschreibung_mitZuWenigZeichen_wirdAbgelehnt` | Negative Test | Sehr sinnvoll | Übernehmen. Prüft, ob Beschreibungen unterhalb der Mindestlänge abgelehnt werden. |
| 13 | `beschreibung_istNullLeerOderBlank_wirdAbgelehnt` | Negative Test | Sehr sinnvoll | Übernehmen. Die verschiedenen ungültigen Eingaben werden parametrisiert getestet. |
| 14 | `kategorie_istNullLeerOderBlank_wirdAbgelehnt` | Negative Test | Sehr sinnvoll | Übernehmen. Prüft die Pflichtangabe der Kategorie. Eine Prüfung auf bestimmte Kategorien entfällt, da keine Kategorienliste existiert. |
| 15 | `ungueltigeEingabe_erzeugtKeinEvent` | Negative Test | Teilweise redundant | Zunächst weglassen. Bei einer fehlgeschlagenen Erstellung entsteht kein gültiges Complaint-Objekt. Ein separater Test wäre erst bei einer eigenständigen Event-Verarbeitung sinnvoll. |
| **16 🆕** | `buergerId_istNull_wirdAbgelehnt` | Negative Test | Sinnvoll | Im weiteren Verlauf ergänzt. Prüft, ob eine Beschwerde ohne Bürger-ID abgelehnt wird. |
| **17 🆕** | `buergerId_istBlank_wirdAbgelehnt` | Negative Test | Sinnvoll | Im weiteren Verlauf ergänzt. Prüft, ob eine Bürger-ID, die nur aus Leerzeichen besteht, abgelehnt wird. |
| **18 🆕** | `titel_mitLeerzeichenUndZuWenigZeichen_wirdAbgelehnt` | Edge Case | Sehr sinnvoll | Im weiteren Verlauf ergänzt. Prüft, ob die Mindestlänge auch nach dem Entfernen führender und abschließender Leerzeichen eingehalten wird. |
| **19 🆕** | `beschreibung_mitLeerzeichenUndZuWenigZeichen_wirdAbgelehnt` | Edge Case | Sehr sinnvoll | Im weiteren Verlauf ergänzt. Prüft dieselbe Problematik für die Beschreibung. |
| **20 🆕** | `offeneBeschwerde_kannInBearbeitungGenommenWerden` | Happy Path | Sehr sinnvoll | Im weiteren Verlauf ergänzt. Prüft den erlaubten Statusübergang von `OPEN` zu `IN_PROGRESS`. |
| **21 🆕** | `offeneBeschwerde_kannDirektGeloestWerden` | Happy Path | Sehr sinnvoll | Im weiteren Verlauf ergänzt. Prüft den erlaubten Statusübergang von `OPEN` zu `RESOLVED`. |
| **22 🆕** | `beschwerdeInBearbeitung_kannGeloestWerden` | Happy Path | Sehr sinnvoll | Im weiteren Verlauf ergänzt. Prüft den erlaubten Statusübergang von `IN_PROGRESS` zu `RESOLVED`. |
| **23 🆕** | `geloesteBeschwerde_kannNichtErneutGeloestWerden` | Negative Test | Sehr sinnvoll | Im weiteren Verlauf ergänzt. Stellt sicher, dass eine bereits gelöste Beschwerde nicht erneut gelöst werden kann. |
| **24 🆕** | `neueBeschwerde_hatEineID` | Happy Path | Sinnvoll | Im weiteren Verlauf ergänzt. Prüft, ob bei der Erstellung automatisch eine ID vergeben wird. |
| **25 🆕** | `zweiBeschwerden_habenUnterschiedlicheIDs` | Edge Case | Sehr sinnvoll | Im weiteren Verlauf ergänzt. Stellt sicher, dass die automatisch generierten IDs eindeutig sind. |


## 03 Implementierung Domänenlogik
Für die Implementierung der Domänenlogik haben wir zunächst die JUnit-Tests erstellt und anschließend die eigentliche Implementierung vorgenommen. Dadurch haben wir den Test-Driven-Development-Ansatz (TDD) angewendet.

Als erstes haben wir die Enum-Klasse `ComplaintStatus` mit den drei möglichen Statuswerten `OPEN`, `IN_PROGRESS` und `RESOLVED` erstellt. Anschließend wurde die Klasse `Complaint` als Aggregate Root implementiert. Die Erstellung einer neuen Beschwerde erfolgt über die statische Factory-Methode `Complaint.create()`.

Bei der Erstellung werden die Eingaben Titel, Beschreibung und Kategorie validiert. Der Titel muss mindestens drei Zeichen und die Beschreibung mindestens zehn Zeichen enthalten. Die Kategorie darf nicht leer sein. Zusätzlich erhält jede Beschwerde automatisch eine eindeutige UUID, den initialen Status `OPEN` und einen Erstellungszeitpunkt.

Für die Erstellung einer Beschwerde wird außerdem das Domain Event `ComplaintCreated` erzeugt. Dieses enthält die ID der erstellten Beschwerde sowie den Zeitpunkt des Ereignisses. Das Event wird zunächst direkt im Complaint-Aggregat erzeugt und kann über `getDomainEvents()` abgerufen werden.

Die Implementierung wurde anschließend mit Maven und JUnit 5 überprüft. Dabei waren zunächst alle 21 vorhandenen Tests erfolgreich.


## 04 Test Erweiterung und Refaktorisierung

Nach der ersten erfolgreichen Implementierung haben wir die vier Java-Dateien (`Complaint.java`, `ComplaintStatus.java`, `ComplaintCreated.java` und `ComplaintTest.java`) erneut an Claude übergeben, um eine kritische Code-Review durchführen zu lassen. Claude identifizierte dabei unter anderem fehlende Validierungen für die Bürger-ID, ungeschützte Statusübergänge und eine mögliche Umgehung der Mindestlängen durch zusätzliche Leerzeichen.

Diese Verbesserungsvorschläge haben wir bewertet und die für unser Projekt sinnvollen Änderungen übernommen. Die Bürger-ID wird nun ebenfalls validiert und führende sowie abschließende Leerzeichen werden vor der Speicherung entfernt. Außerdem wurden die Statusänderungen durch konkrete Methoden abgesichert: Eine offene Beschwerde kann in Bearbeitung genommen oder direkt gelöst werden, während eine bereits gelöste Beschwerde nicht erneut verändert werden kann.

Zusätzlich haben wir die Klasse `ComplaintCreated` in einen Java-Record umgewandelt, da das Event ausschließlich unveränderliche Daten enthält. Die Tests wurden um die neuen Validierungen und Statusübergänge erweitert. Außerdem haben wir die Teststruktur durch `assertAll()` verbessert und zusätzliche Tests für die automatische Vergabe eindeutiger Beschwerde-IDs ergänzt.

Auf Vorschläge wie die Einführung eines `Clock`-Objekts, zusätzlicher Value Objects oder einer komplexeren Event-Architektur haben wir bewusst verzichtet, da diese für den aktuellen Umfang des Projekts keinen ausreichenden Mehrwert bieten würden.

Nach den Änderungen haben wir die Tests erneut mit `mvn test` ausgeführt. Insgesamt wurden nun 30 Tests erfolgreich ausgeführt, ohne Fehler oder fehlgeschlagene Tests. Damit konnten wir die ursprüngliche Implementierung gezielt verbessern und die wichtigsten fachlichen Anforderungen durch automatisierte Tests absichern.


## 05 Modularität und Testbarkeit via CI/CD sicherstellen

----insert Content here----


## 06 Kritische Reflexion

----insert Content here----
