package net.creative.tutorialmod.block.custom;

import net.creative.tutorialmod.item.ModItems;
import net.creative.tutorialmod.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        // Adds Particle for Clients Only
        // Needs to Change to SERVER Personally
        level.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 1, 0);
        // Adds Sound for Everyone, "playLocalSound" is Only for Client
        level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 2f, 1f);
        // .Success Makes it so that Right-Clicking w/ a Block Doesn't Place that Block
        return InteractionResult.SUCCESS;
    }


    @Override
    // stepOn is the Action Used to Trigger Effect
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        // Add Effect to player
        //      * Needs To disable particles
        if(entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300));
            //player.addEffect(new MobEffectInstance(effect, duration, amplifier, Particle, Effect Icon));
        }

        // Change Item(s) to other Items
        if(entity instanceof ItemEntity itemEntity) {
            if(isValidItem(itemEntity.getItem())) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
            }
        }

        super.stepOn(level, pos, onState, entity);
    }

    // Determines Items that Can Be Used
    //  * Both allow Multple way to Get the Same item
    //      * Map - Stores Relationship Between Items
    //      * isValidItem - Checks if the is Item Allowed
    private boolean isValidItem(ItemStack item) {
        // New Method w/ Tags
        return item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
        // Old Method
        //return item.is(ModItems.FLUORITE)
        //        || item.is(Items.IRON_INGOT)
        //        || item.is(Items.COAL);
    }
}

// To Use Map Instead
//
// 1. ADD
//      private static final Map<Item, Item> ITEM_CONVERSIONS = Map.of(
//          ModItems.FLUORITE, Items.DIAMOND,
//          Items.IRON_INGOT, Items.DIAMOND,
//          Items.COAL, Items.DIAMOND
//       );
//
// 2. Replace isValidItem
//          if (entity instanceof ItemEntity itemEntity) {
//              Item input = itemEntity.getItem().getItem();
//
//              if (ITEM_CONVERSIONS.containsKey(input)) {
//                  Item output = ITEM_CONVERSIONS.get(input);
//
//                  itemEntity.setItem(new ItemStack(output, itemEntity.getItem().getCount()));
//              }
//          }
//
// 3. Import
//      import java.util.Map;
//      import net.minecraft.world.item.Item;
//
// 4. Add Conversions
//      Items.GOLD_INGOT, Items.EMERALD
//
//
//