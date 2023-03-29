package blue.lhf.vipu_test_plugin;

import blue.lhf.vipu.escaping.*;
import xyz.jpenilla.reflectionremapper.ReflectionRemapper;

import static xyz.jpenilla.reflectionremapper.ReflectionRemapper.forReobfMappingsInPaperJar;

@Name("Vipu Test Plugin")
@Libraries({"net.bytebuddy:byte-buddy:1.14.2", "net.bytebuddy:byte-buddy-agent:1.14.2"})
public class Plugin implements VipuPlugin {
    public static final ReflectionRemapper REMAPPER = forReobfMappingsInPaperJar();

    static {
        System.err.println("Hello from the Vipu Test Plugin!");
        ZombieAdvice.inject(REMAPPER);
    }
}