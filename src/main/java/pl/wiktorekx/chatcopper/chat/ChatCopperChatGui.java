package pl.wiktorekx.chatcopper.chat;

import net.labymod.ingamechat.GuiChatCustom;
import net.minecraft.util.IChatComponent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class ChatCopperChatGui extends GuiChatCustom {
    @Override
    protected boolean handleComponentClick(IChatComponent chatComponent) {
        if(chatComponent != null){
            if(chatComponent.getChatStyle() instanceof CustomChatStyle){
                CustomChatStyle customChatStyle = (CustomChatStyle) chatComponent.getChatStyle();
                if(customChatStyle.getProperties().containsKey("copy")){
                    Object obj = customChatStyle.getProperties().get("copy");
                    if(obj instanceof IChatComponent){
                        IChatComponent copyComponent = (IChatComponent) obj;
                        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(copyComponent.getUnformattedText()), null);
                    }
                }
            }
            return super.handleComponentClick(chatComponent);
        }
        return false;
    }
}
