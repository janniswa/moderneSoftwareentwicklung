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

## Verwendete CI/CD-Plattform

Für die Continuous-Integration-Pipeline verwenden wir **GitHub Actions**.

Die Wahl fiel auf GitHub Actions, da sich unser Repository bereits auf GitHub befindet und die CI-Pipeline dadurch direkt in die bestehende Entwicklungsumgebung integriert werden kann. Ein zusätzlicher externer CI-Server muss dadurch nicht eingerichtet oder verwaltet werden.

Die Konfiguration der Pipeline befindet sich im Repository unter:

`.github/workflows/ci.yml`

Status: ![Java CI](https://github.com/janniswa/moderneSoftwareentwicklung/actions/workflows/ci.yml/badge.svg)

## Aktueller Ablauf der CI-Pipeline

Die Pipeline wird automatisch ausgeführt, wenn Änderungen in das Repository gepusht werden oder ein Pull Request erstellt beziehungsweise aktualisiert wird.

Der aktuelle Ablauf besteht aus folgenden Schritten:

1. GitHub Actions startet automatisch den konfigurierten Workflow.
2. Das Repository wird auf einem GitHub-Runner ausgecheckt.
3. Die aktuell konfigurierten CI-Schritte werden ausgeführt.
4. Das Ergebnis des Workflows kann anschließend direkt über GitHub Actions eingesehen werden.

Der automatische Build der CityFeedback-Anwendung ist aktuell noch nicht integriert. Dieser soll ergänzt werden, sobald die konkrete Projekt- und Build-Struktur feststeht.

## Geplante Erweiterungen der CI-Pipeline

Im weiteren Projektverlauf soll die Pipeline schrittweise erweitert werden.

Geplant sind insbesondere:

- automatisches Bauen der CityFeedback-Anwendung,
- Integration automatisierter Tests,
- gegebenenfalls weitere Qualitätssicherungsmaßnahmen wie Linting oder Code-Coverage-Auswertungen,
- langfristig die Verknüpfung der CI-Pipeline mit einem automatisierten Deployment.

Die automatisierten Tests werden zum aktuellen Stand noch nicht umgesetzt und sollen zu einem späteren Zeitpunkt in die Pipeline integriert werden.

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
Dieses Event wird ausgelöst, wenn ein ein Verwaltungsmitarbeiter einen Kommentar zu einer bestehenden Beschwerde hinzufügt. Dadurch können Details zur Schließung oder weiteren Bearbeitungen festgehalten werden. Der Bürger kann die Kommentare anschließend einsehen.

### 5. ComplaintStatusChanged
Dieses Event wird ausgelöst, wenn sich der Bearbeitungsstatus einer Beschwerde ändert. Dies kann beispielsweise der Wechsel von „Neu“ zu „In Bearbeitung“ sein. Das Event dokumentiert den aktuellen Fortschritt der Bearbeitung, ohne dass die Beschwerde bereits abgeschlossen sein muss.

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
### 05.1 Domain Services
Für CityFeedback werden zwei Services vorgesehen, die zum Koordinieren der Anwendungsfälle dienen.

#### AuthenticationService (Identity Context)
Der AuthenticationService gehört zum Identity Context und ist für die Registrierung und Anmeldung von Benutzern zuständig.

- register(name, email, password, role) – Registriert einen neuen Benutzer.
- login(email, password) – Überprüft die Anmeldedaten.

#### ComplaintService (Complaint Management Context)
Der ComplaintService gehört zum Complaint Management Context und koordiniert die Erstellung und Bearbeitung von Beschwerden.

- createComplaint(userId, title, description, category) – Erstellt und speichert eine neue Beschwerde.
- getComplaintById(id) – Ruft eine Beschwerde anhand ihrer ID ab.
- getComplaintsByUserId(userId) – Ruft die Beschwerden eines Benutzers ab.
- changeStatus(complaintId, newStatus) – Ändert den Bearbeitungsstatus.
- resolveComplaint(complaintId) – Markiert eine Beschwerde als gelöst.

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

### 06.2 Beispiel einer Domain-Klasse

Die Klasse Complaint bildet den Aggregate Root des Complaint-Aggregates. Sie verwaltet die Beschwerdedaten und die zugehörigen Kommentare.

Beispielhafte Java-Implementierung:

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

Das Beispiel zeigt, dass die Validierung direkt bei der Erstellung erfolgt und Statusänderungen sowie Kommentare über Methoden des Complaint-Aggregates verwaltet werden.

### 06.3 Testbarkeit und Modularität

Für die Umsetzung werden Maven als Build-Tool und JUnit 5 als Testframework verwendet. Die Entwicklung erfolgt nach dem Test-Driven-Development-Prinzip: Zuerst werden Tests geschrieben, anschließend wird die passende Implementierung erstellt und abschließend refaktorisiert. Durch die Trennung der fachlichen Logik von technischen Komponenten können die einzelnen Bereiche unabhängig getestet werden. GitHub Actions führt die automatisierten Tests bei jedem Push und Pull Request aus.

### 06.4 Zusammenfassung

Die gewählte Architektur trennt die fachlichen Verantwortlichkeiten und ermöglicht eine modulare Entwicklung. Das Complaint-Aggregate bildet den Kern der Anwendung. Durch Java, Maven, JUnit 5 und die CI-Pipeline soll CityFeedback wartbar, testbar und erweiterbar bleiben.

---------------------------------------------------------------------------------------------------------------------------------------------------------------
# Übung 4 –  Advanced Java, Test-Driven Design (TDD) und LLM-gestütztes Entwickeln

## 01 LLM Review der Implementierungsstrategie

Für die Überprüfung unserer ursprünglichen Implementierungsstrategie haben wir Claude von Anthropic verwendet. Ziel war es, unsere bisherige Strategie für die Entwicklung der Beschwerdeerstellung kritisch prüfen zu lassen und konkrete Verbesserungsvorschläge im Hinblick auf Domain-Driven Design, Validierung, Domain Events und testgetriebene Entwicklung zu erhalten.

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


## 03 Implementierung Domänenlogik

----insert Content here----


## 04 Test Erweiterung und Refaktorisierung

----insert Content here----


## 05 Modularität und Testbarkeit via CI/CD sicherstellen

----insert Content here----


## 06 Kritische Reflexion

----insert Content here----
