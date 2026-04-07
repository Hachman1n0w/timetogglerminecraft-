# TimeToggler Mod – Minecraft 1.21.1 (Fabric)

Wechsle mit einem konfigurierbaren Hotkey **visuell** zwischen Tag und Nacht.  
Nur du siehst den Wechsel – der Server und andere Spieler werden nicht beeinflusst.

---

## Features
- **Hotkey** zum Umschalten (Standard: **N**)
- Hotkey in den **Tastenbelegungs-Einstellungen** änderbar
- Bestätigung per **Action-Bar-Nachricht** (über der Hotbar)
- **Rein clientseitig** – funktioniert auf jedem Server

---

## Voraussetzungen
- Java 21 (z. B. [Adoptium Temurin 21](https://adoptium.net/))
- [Fabric Loader ≥ 0.16.5](https://fabricmc.net/use/installer/)
- [Fabric API 0.102.0+1.21.1](https://modrinth.com/mod/fabric-api)
- Minecraft 1.21.1

---

## Mod bauen

### 1. Repository klonen / entpacken
Entpacke die Mod-Dateien in einen Ordner.

### 2. Gradle Wrapper einrichten (einmalig)
```bash
# Windows:
gradlew.bat wrapper

# Linux/macOS:
chmod +x gradlew
./gradlew wrapper
```

### 3. Mod kompilieren
```bash
# Windows:
gradlew.bat build

# Linux/macOS:
./gradlew build
```

Die fertige `.jar`-Datei liegt dann unter:
```
build/libs/timetoggler-1.0.0.jar
```

---

## Installation
1. Fabric Loader für Minecraft 1.21.1 installieren
2. [Fabric API](https://modrinth.com/mod/fabric-api) herunterladen
3. Beide `.jar`-Dateien in den `mods`-Ordner kopieren:
   - Windows: `%appdata%\.minecraft\mods\`
   - Linux: `~/.minecraft/mods/`
   - macOS: `~/Library/Application Support/minecraft/mods/`

---

## Verwendung
| Taste | Aktion |
|-------|--------|
| **N** (Standard) | Tag/Nacht umschalten |

Den Hotkey ändern:  
**Minecraft → Optionen → Steuerung → Tastenbelegung → TimeToggler**

---

## Projektstruktur
```
src/
└── main/
    ├── java/com/timetoggler/
    │   ├── TimeTogglerMod.java          ← Haupt-Klasse (Hotkey-Logik)
    │   └── mixin/
    │       └── ClientWorldMixin.java    ← Mixin (visuelle Zeitüberschreibung)
    └── resources/
        ├── fabric.mod.json
        ├── timetoggler.mixins.json
        └── assets/timetoggler/lang/
            ├── de_de.json
            └── en_us.json
```
