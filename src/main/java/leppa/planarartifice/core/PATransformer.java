package leppa.planarartifice.core;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

public class PATransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!PlanarArtificeCore.enabled || !transformedName.equals("thaumcraft.common.blocks.world.taint.TaintHelper")) return basicClass;
        PlanarArtificeCore.LOGGER.info("[PA, CORE] found TaintHelper");
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(basicClass);
        reader.accept(node, 0);
        // transform
        try {
            PlanarArtificeCore.LOGGER.info("[PA, CORE] Patching in EventFibreSpread event");
            MethodNode m = findMethod(node, "spreadFibres", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Z)V");
            assert m != null;
            int offset = findMethodCall(m, 0, "<init>", "(III)V", "net/minecraft/util/math/BlockPos");
            if (offset != -1) offset = findOpcode(m, offset, Opcodes.ASTORE);
            AbstractInsnNode q = m.instructions.get(offset); // Extreme hopium
            PlanarArtificeCore.LOGGER.info("[PA, CORE] Offset: " + offset + ", " + q.getOpcode() + " > " + q.getNext().getOpcode());
            // insert: MinecraftForge.EVENT_BUS.post(new EventFibreSpread(world(0), pos(1), t(7)));
            m.instructions.insert(q, new InsnNode(Opcodes.POP));
            m.instructions.insert(q, new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraftforge/fml/common/eventhandler/EventBus", "post", "(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", false));
            m.instructions.insert(q, new MethodInsnNode(Opcodes.INVOKESPECIAL, "leppa/planarartifice/api/event/EventFibreSpread", "<init>", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)V", false));
            m.instructions.insert(q, new VarInsnNode(Opcodes.ALOAD, 7));
            m.instructions.insert(q, new VarInsnNode(Opcodes.ALOAD, 1));
            m.instructions.insert(q, new VarInsnNode(Opcodes.ALOAD, 0));
            m.instructions.insert(q, new InsnNode(Opcodes.DUP));
            m.instructions.insert(q, new TypeInsnNode(Opcodes.NEW, "leppa/planarartifice/api/event/EventFibreSpread"));
            m.instructions.insert(q, new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/fml/common/eventhandler/EventBus;"));
            PlanarArtificeCore.LOGGER.info("[PA, CORE] Successfully patched in EventFibreSpread event");
        } catch (Throwable e) { throw new RuntimeException(e); }
        // end transform
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES) {
            @Override
            protected String getCommonSuperClass(String type1, String type2) {
                return "java/lang/Object";
            }
        };
        node.accept(writer);
        return writer.toByteArray();
    }

    private static MethodNode findMethod(ClassNode classNode, String name, String desc) {
        for (MethodNode m : classNode.methods) if (m.name.equals(name) && m.desc.equals(desc)) return m;
        return null;
    }
    public static int findMethodCall(MethodNode node, int startIndex, String name, String desc, String owningClass) {
        if (startIndex < 0 || startIndex >= node.instructions.size()) return -1;
        for (int i = startIndex; i < node.instructions.size(); ++i) {
            AbstractInsnNode insn = node.instructions.get(i);
            if (insn instanceof MethodInsnNode) {
                MethodInsnNode method = (MethodInsnNode) insn;
                if (method.name.equals(name) && method.desc.equals(desc) && method.owner.equals(owningClass)) return i;
            }
        }
        return -1;
    }
    public static int findOpcode(MethodNode node, int startIndex, int opcode) {
        if (startIndex < 0 || startIndex >= node.instructions.size()) return -1;
        for (int i = startIndex; i < node.instructions.size(); ++i) if (node.instructions.get(i).getOpcode() == opcode) return i;
        return -1;
    }
    public static String remapFieldName(String internalName, String fieldName) {
        String internal = FMLDeobfuscatingRemapper.INSTANCE.unmap(internalName);
        return FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(internal, fieldName, null);
    }
    public static String remapMethodName(String internalName, String methodName, Type returnType, Type... parameterTypes) {
        String internal = FMLDeobfuscatingRemapper.INSTANCE.unmap(internalName);
        String desc = Type.getMethodDescriptor(returnType, parameterTypes);
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(internal, methodName, desc);
    }
    private static void goal(World world, BlockPos pos) {
    }
    public static int findLabel(MethodNode node, int startIndex) {
        if (startIndex < 0 || startIndex >= node.instructions.size()) return -1;
        for (int i = startIndex; i < node.instructions.size(); ++i) {
            AbstractInsnNode insn = node.instructions.get(i);
            if (insn instanceof LabelNode) return i;
        }
        return -1;
    }
}
