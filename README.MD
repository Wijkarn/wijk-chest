### TO UPGRADE MINECRAFT VERSION
Go to https://fabricmc.net/develop/

Copy the following changes to gradle.properties
```
minecraft_version
yarn_mappings
loader_version
loom_version

fabric_version
```

Go to https://linkie.shedaniel.dev/ and choose the latest fabric version

Copy the following change to gradle.properties
```
Cloth Config -> cloth_config_version
Mod Menu -> modmenu_version
```

Don't forget to change the mod version
```
mod_version
```

Go to source/main/resources/fabric.mod.json and change these if necessary
```
fabricloader
minecraft
```

### Terminal Commands

```bash
./gradlew genSources
./gradlew build
```