package blue.lhf.vipu_test_plugin;

import blue.lhf.vipu.escaping.*;
import xyz.jpenilla.reflectionremapper.ReflectionRemapper;

import static xyz.jpenilla.reflectionremapper.ReflectionRemapper.forReobfMappingsInPaperJar;

@Libraries({"net.bytebuddy:byte-buddy:1.14.2", "net.bytebuddy:byte-buddy-agent:1.14.2"})
public class Plugin implements VipuPlugin {
    public static final ReflectionRemapper REMAPPER = forReobfMappingsInPaperJar();

    @Override
    public void onEnable() {
        ZombieAdvice.inject(REMAPPER);
    }


    @Override
    public void onDisable() {
    }
}
