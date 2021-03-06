package net.sssubtlety.automated_crafting;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;
import java.util.function.Supplier;

import static net.sssubtlety.automated_crafting.AutomatedCraftingInit.MOD_ID;

@Environment(EnvType.CLIENT)
public class AutomatedCraftingModMenuIntegration implements ModMenuApi {
    @Override
    public String getModId() {
        return MOD_ID;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            Optional<Supplier<Screen>> optionalScreen = getConfigScreen(parent);
            if(optionalScreen.isPresent()) {
                return optionalScreen.get().get();
            } else {
                return parent;
            }
        };
    }

//    @Override
    public Optional<Supplier<Screen>> getConfigScreen(Screen screen) {
        return Optional.of(AutoConfig.getConfigScreen(AutomatedCraftingConfig.class, screen));
    }
}
