package net.kuko.fisch.client.screen;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.SpruceTexts;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import net.kuko.fisch.Fisch;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class UltimateMachineScreen extends SpruceScreen {

    public UltimateMachineScreen() {
        super(Component.literal("ultimate machine menu"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void renderTitle(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.renderTitle(graphics, mouseX, mouseY, delta);
    }
}
