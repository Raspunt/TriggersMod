package org.giga.triggersmod2.client.Screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static org.giga.triggersmod2.client.TriggersMod2Client.MOD_ID;


@Environment(EnvType.CLIENT)
public class TriggerGuiScreen extends Screen {


    private static final Text wellcomeText = Text.translatable("wellcome to the jungle");
    private static final Identifier MANUAL_TEXTURE = Identifier.of(MOD_ID, "textures/manual.png");

    final int guiWidth = 207;
    final int guiHeight = 195;
    private ButtonWidget button1;
    private ButtonWidget button2;



    public TriggerGuiScreen(Text title) {
        super(Text.literal("TriggerGuiScreen"));
    }


    @Override
    protected void init() {

        button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {
                    System.out.println("You clicked button1!");
                })
                .dimensions(width / 2 - 205, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build();
        button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
                    System.out.println("You clicked button2!");
                })
                .dimensions(width / 2 + 5, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();

        addDrawableChild(button1);
        addDrawableChild(button2);
    }

    @Override
    public void renderBackground(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        super.renderBackground(drawContext, mouseX, mouseY, delta);
        int centerX = (width / 2) - guiWidth / 2;
        int centerY = (height / 2) - guiHeight / 2;
        drawContext.drawTexture(MANUAL_TEXTURE, centerX, centerY, 0, 0, guiWidth, guiHeight);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float partialTicks) {
        super.render(drawContext, mouseX, mouseY, partialTicks);

        int centerY = (height / 2) - guiHeight / 2;
        int color = 0xFFFFFF;


        drawContext.drawText(textRenderer, wellcomeText, (width / 2) - textRenderer.getWidth(wellcomeText) / 2, centerY + 40, color, false);

        }
    }




