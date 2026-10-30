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

#### Branches und ihre Nutzung
### Merge
#### Umgang mit Merge-Konflikten
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
---------------------------------------------------------------------------------------------------------------------------------------------------------------
# Übung 2 – CI/CD

## Verwendete CI/CD-Plattform

Für die Continuous-Integration-Pipeline verwenden wir **GitHub Actions**.

Die Wahl fiel auf GitHub Actions, da sich unser Repository bereits auf GitHub befindet und die CI-Pipeline dadurch direkt in die bestehende Entwicklungsumgebung integriert werden kann. Ein zusätzlicher externer CI-Server muss dadurch nicht eingerichtet oder verwaltet werden.

Die Konfiguration der Pipeline befindet sich im Repository unter:

`.github/workflows/ci.yml`

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
