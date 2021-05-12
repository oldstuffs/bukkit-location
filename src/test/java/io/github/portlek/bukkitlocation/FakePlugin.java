package io.github.portlek.bukkitlocation;

import java.io.File;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public final class FakePlugin extends JavaPlugin {

  public FakePlugin() {
  }

  public FakePlugin(final JavaPluginLoader loader, final PluginDescriptionFile description,
                    final File dataFolder, final File file) {
    super(loader, description, dataFolder, file);
  }

  @Override
  public void onLoad() {
  }

  @Override
  public void onDisable() {
  }

  @Override
  public void onEnable() {
  }
}

