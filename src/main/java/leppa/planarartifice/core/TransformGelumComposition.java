package leppa.planarartifice.core;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodNode;

import static leppa.planarartifice.core.PATransformer.*;

public class TransformGelumComposition extends Transformer {
    public TransformGelumComposition() {}
    void transform(ClassNode node) {
        if (PlanarArtificeCore.config.getBoolean("disableGelumAspectChange", "overhaul", false, "")) return;
        PlanarArtificeCore.LOGGER.info("[PA, CORE] Patching in Gelum");
        MethodNode m = findMethod(node, "<clinit>", "()V");
        assert m != null;
        int offset = findField(m, 0, "MOTION", "Lthaumcraft/api/aspects/Aspect;", "thaumcraft/api/aspects/Aspect");
        if (offset != -1) offset = findOpcode(m, offset, Opcodes.GETSTATIC);
        int offset2 = -1;
        if (offset != -1) offset2 = findOpcode(m, offset + 1, Opcodes.GETSTATIC);

        AbstractInsnNode q = m.instructions.get(offset).getPrevious(); // this should be iconst_0
        AbstractInsnNode q2 = m.instructions.get(offset2).getPrevious(); // this should be iconst_1

        m.instructions.remove(q2.getNext());
        m.instructions.insert(q2, new FieldInsnNode(Opcodes.GETSTATIC, "thaumcraft/api/aspects/Aspect", "ORDER", "Lthaumcraft/api/aspects/Aspect;"));
        m.instructions.remove(q.getNext());
        m.instructions.insert(q, new FieldInsnNode(Opcodes.GETSTATIC, "thaumcraft/api/aspects/Aspect", "WATER", "Lthaumcraft/api/aspects/Aspect;"));

        PlanarArtificeCore.LOGGER.info("[PA, CORE] Justice is Served");
    }
}
