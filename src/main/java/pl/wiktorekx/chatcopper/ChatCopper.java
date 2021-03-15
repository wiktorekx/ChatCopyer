package pl.wiktorekx.chatcopper;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import pl.wiktorekx.chatcopper.config.ConfigManager;
import pl.wiktorekx.chatcopper.config.ConfigObject;

import java.util.List;

public class ChatCopper extends LabyModAddon {
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        setConfigValues();
        ChatCopperListeners chatCopperListeners = new ChatCopperListeners(configManager);
        getApi().getEventManager().register(chatCopperListeners);
        getApi().registerForgeListener(chatCopperListeners);
    }

    private void setConfigValues(){
        configManager.addObject(new ConfigObject("enable", "Enable", new ControlElement.IconData(Material.LEVER), true));
        configManager.addObject(new ConfigObject("prefix", "Prefix", new ControlElement.IconData(Material.PAPER), "&r  &f["));
        configManager.addObject(new ConfigObject("suffrix", "Suffrix", new ControlElement.IconData(Material.PAPER), "&f]"));
        configManager.addObject(new ConfigObject("content", "Content", new ControlElement.IconData(Material.PAPER), "&acopy"));
//        configManager.addObject(new ConfigObject("hoverEnable", "HoverTextEnable", new ControlElement.IconData(Material.LEVER), true));
//        configManager.addObject(new ConfigObject("hoverText", "HoverText", new ControlElement.IconData(Material.PAPER), "&fCoppy Text"));
    }

    @Override
    public void loadConfig() {
        configManager.loadConfig();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        configManager.fillSettings(list);
    }
}
