package net.dovtech.lightbridges;

import net.dovtech.lightbridges.commands.LBCreate;
import org.bukkit.plugin.java.JavaPlugin;

public class LightBridges extends JavaPlugin {
	
  private static LightBridges plugin;
  private String NAME = "LightBridges";
  private String VERSION = "0.1.0";
  private String AUTHOR = "TheDerpGamerX";
  private String pluginFile = NAME + "v" + VERSION;
  
  public static String pluginDataFolder = getPlugin().getDataFolder() + "";

  @Override
  public void onEnable() {
	plugin = getPlugin();
    System.out.println("Loading " + pluginFile + "...");
    
    //Events
    //getServer().getPluginManager().registerEvents(new <EventClass>(), this);
    
    //Commands
    getCommand("lb create").setExecutor(new LBCreate());
  }
  
  public static LightBridges getPlugin() {
	  return plugin;
  }
}