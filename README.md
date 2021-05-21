[![idea](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

![master](https://github.com/portlek/bukkit-location/workflows/build/badge.svg)
[![Release](https://jitpack.io/v/portlek/bukkit-location.svg)](https://jitpack.io/#portlek/bukkit-location)

## How to Use

### Maven

```xml
<builds>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.2.4</version>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
          <configuration>
            <minimizeJar>true</minimizeJar>
            <createDependencyReducedPom>false</createDependencyReducedPom>
            <!-- Relocations(Optional)
            <relocations>
              <relocation>
                  <pattern>io.github.portlek.bukkitlocation</pattern>
                  <shadedPattern>[YOUR_PLUGIN_PACKAGE].shade</shadedPattern>
              </relocation>
            </relocations>
            -->
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</builds>
```

```xml
<repositories>
  <repository>
    <id>jitpack</id>
    <url>https://jitpack.io/</url>
  </repository>
</repositories>
```

```xml
<dependencies>
  <dependency>
    <groupId>io.github.portlek</groupId>
    <artifactId>bukkit-location</artifactId>
    <version>${version}</version>
  </dependency>
</dependencies>
```

### Gradle

```groovy
plugins {
  id "com.github.johnrengelman.shadow" version "7.0.0"
}
```

```groovy
repositories {
  maven {
    url "https://jitpack.io"
  }
}
```

```groovy
dependencies {
  implementation("io.github.portlek:bukkit-location:${version}")
}
```
