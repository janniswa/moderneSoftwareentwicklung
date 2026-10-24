## CI/CD-Pipeline

Für unser Projekt verwenden wir GitHub Actions zur Automatisierung unseres Entwicklungsprozesses.

### Aktueller Funktionsumfang

- Der Workflow wird bei Pushes und Pull Requests gestartet.
- GitHub stellt eine virtuelle Linux-Umgebung zur Ausführung bereit.
- Der Workflow bestätigt durch eine Konsolenausgabe, dass die Pipeline ausgeführt wird.

### Geplante Erweiterungen

- Java-Projekt automatisch bauen
- Automatisierte Tests mit JUnit ausführen
- Pull Requests erst nach erfolgreichen Prüfungen zusammenführen
- Deployment für eine spätere Projektphase evaluieren

### Begründung der Plattformwahl

Wir haben GitHub Actions gewählt, weil unser Quellcode bereits auf GitHub
verwaltet wird. Die CI-Automatisierung lässt sich dadurch direkt in unser
Repository integrieren. Außerdem benötigen wir keinen eigenen CI-Server.
