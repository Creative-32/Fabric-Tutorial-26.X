package net.creative.tutorialmod.sound;

import net.creative.tutorialmod.TutorialMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    // Chisel Use Sound
    public static final SoundEvent CHISEL_USE = registerSoundEvent("chisel_use");

    // Register JukeBox Song
    public static final Holder.Reference<SoundEvent> BAR_BRAWL = registerJukeboxSong("bar_brawl");


    // Jukebox Helper Method
    private static Holder.Reference<SoundEvent> registerJukeboxSong(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    // Sound Helper Method
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds() {
        TutorialMod.LOGGER.info("Registering sounds for " + TutorialMod.MOD_ID);
    }
}