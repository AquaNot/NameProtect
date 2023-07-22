# NameProtect
- Changes your name client-side. Useful to prevent stream sniping and alt leaks.

## Commands
- This mod uses client-side commands to function. These commands are:

- /nameprotect toggle (on/off)
- /nameprotect change <newname>

## Building
You will need:

- Java JDK 17 or higher. (e.g. [Temurin](https://adoptium.net/))
- 3 GB of available RAM.
- A bit of storage.

How to:
- Ensure your JDK is set up properly. (i.e. JDK path is in `JAVA_HOME` environment variable)
- Clone this repo or download it. (e.g. via `git clone https://github.com/AquaNot/NameProtect.git`)
- Open the terminal (command prompt) there.
- Run `./gradlew build`.
- Grab the jar file from `/build/libs`
- Put the jar file inside your mods folder
## Dependencies
- [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api/files)
- Minecraft ofc 💀
