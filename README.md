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
