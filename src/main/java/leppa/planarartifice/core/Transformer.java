package leppa.planarartifice.core;

import org.objectweb.asm.tree.ClassNode;

abstract class Transformer {
    void transform(ClassNode node) {}
}
