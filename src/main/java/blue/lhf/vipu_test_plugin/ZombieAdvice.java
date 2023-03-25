package blue.lhf.vipu_test_plugin;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.Advice.*;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatcher;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import xyz.jpenilla.reflectionremapper.ReflectionRemapper;

import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class ZombieAdvice {
    // region Injection Utility
    public static void inject(final Class<?> toRedefine, ElementMatcher<? super MethodDescription> matcher) {
        ByteBuddyAgent.install();
        try (final var unloaded = new ByteBuddy().redefine(toRedefine).visit(Advice.to(
            ZombieAdvice.class).on(matcher)).make()) {
            unloaded.load(Plugin.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    public static void inject(final ReflectionRemapper remapper) {
        inject(Zombie.class, named(remapper.remapMethodName(Zombie.class, "registerGoals"))
            .and(takesNoArguments()).and(returns(void.class)));
    }

    @OnMethodEnter
    public static void onEnter(@This Zombie zombie) {
        zombie.goalSelector.addGoal(0, new PanicGoal(zombie, 2));
        zombie.goalSelector.addGoal(4, new TemptGoal(zombie, 1.2, Ingredient.of(Items.BRAIN_CORAL), true));
    }
}
