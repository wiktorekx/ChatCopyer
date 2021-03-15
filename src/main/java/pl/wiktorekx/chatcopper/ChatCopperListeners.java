package pl.wiktorekx.chatcopper;

import net.labymod.api.events.MessageModifyChatEvent;
import net.labymod.core.LabyModCore;
import net.labymod.ingamechat.GuiChatCustom;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pl.wiktorekx.chatcopper.chat.ChatCopperChatGui;
import pl.wiktorekx.chatcopper.chat.CustomChatStyle;
import pl.wiktorekx.chatcopper.config.ConfigManager;

public class ChatCopperListeners implements MessageModifyChatEvent {
    private ConfigManager chatCopper;

    public ChatCopperListeners(ConfigManager chatCopper) {
        this.chatCopper = chatCopper;
    }

    private boolean hasEnable(){
        return (Boolean) chatCopper.getObject("enable").getValue();
    }

    private void addCopyText(IChatComponent chatComponent){
        IChatComponent prefixComponent = createChatComponent((String) chatCopper.getObject("prefix").getValue());
        IChatComponent suffrixComponent = createChatComponent((String) chatCopper.getObject("suffrix").getValue());
        IChatComponent contentComponent = createChatComponent((String) chatCopper.getObject("content").getValue());
        CustomChatStyle customChatStyle = new CustomChatStyle(contentComponent);
        customChatStyle.setProperty("copy", chatComponent.createCopy());
//        if((Boolean) chatCopper.getObject("hoverEnable").getValue()) {
//            customChatStyle.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, createChatComponent((String) chatCopper.getObject("hoverText").getValue())));
//        }
        chatComponent.appendSibling(prefixComponent);
        chatComponent.appendSibling(contentComponent);
        chatComponent.appendSibling(suffrixComponent);
    }

    private IChatComponent createChatComponent(String text){
        return new ChatComponentText(text.replace("&", "\u00A7"));
    }

    @Override
    public Object onModifyChatMessage(Object o) {
        if(hasEnable()){
            if(o instanceof IChatComponent){
                addCopyText((IChatComponent) o);
            }
        }
        return o;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onOpenGui(GuiOpenEvent event){
        if(hasEnable()) {
            GuiScreen guiScreen = LabyModCore.getForge().getGuiOpenEventGui(event);
            if (guiScreen != null) {
                if (guiScreen.getClass().equals(GuiChatCustom.class)) {
                    LabyModCore.getForge().setGuiOpenEventGui(event, new ChatCopperChatGui());
                }
            }
        }
    }
}
