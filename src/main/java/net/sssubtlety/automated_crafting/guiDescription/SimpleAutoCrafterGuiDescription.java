package net.sssubtlety.automated_crafting.guiDescription;

import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.TranslatableText;

import static net.sssubtlety.automated_crafting.AutoCrafterSharedData.*;
import static net.sssubtlety.automated_crafting.AutomatedCraftingInit.MOD_ID;

public class SimpleAutoCrafterGuiDescription extends AbstractAutoCrafterGuiDescription {

    public SimpleAutoCrafterGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(syncId, playerInventory, context);
    }

    @Override
    protected int getTemplateX() {
        return 0;
    }

    @Override
    protected int getInputX() {
        return 4 * GRID_PIXELS;
    }

    @Override
    protected int getOutputX() {
        return 8 * GRID_PIXELS - 5;
    }

    @Override
    protected void optionalAddition(WPlainPanel root) {
        WItemSlot inputSlot;
        inputSlot = WItemSlot.of(blockInventory, FIRST_INPUT_SLOT, GRID_WIDTH, GRID_HEIGHT);
        inputSlot.setInsertingAllowed(false);
        root.add(inputSlot, getInputX(), GRID_PIXELS + CRAFT_GRID_Y_OFFSET);

        WLabel templateLabel = new WLabel(new TranslatableText("label." + MOD_ID + ".template"));
        templateLabel.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(templateLabel, GRID_PIXELS, 4 * GRID_PIXELS);
        WLabel inputLabel = new WLabel(new TranslatableText("label." + MOD_ID + ".input"));
        inputLabel.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(inputLabel, 5 * GRID_PIXELS, 4 * GRID_PIXELS);
    }
}