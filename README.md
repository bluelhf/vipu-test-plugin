# vipu-test-plugin

This is a simple proof-of-concept plugin for
[Vipu](https://github.com/bluelhf/Vipu).

The plugin, when loaded by Vipu, adds two new behaviours for Zombies:
1. Zombies will be tempted by brain coral, like pigs are by carrots.
2. Zombies will panic when attacked.

It probably only works on Paper (because of how it handles obfuscation
with [reflection-remapper](https://github.com/jpenilla/reflection-remapper)).

## Building

I haven't published Vipu on my Maven repository yet, so for now it'll have
to be in your local Maven repository.

After that, you'll also have to run
`mvn paper-nms:init` to initialise the obfuscation mappings.

Then, use `mvn verify` to build the plugin, and add it to your server's `plugins/Vipu/` directory.


Restart your server, and Vipu should automatically download the necessary
dependencies and load the plugin.