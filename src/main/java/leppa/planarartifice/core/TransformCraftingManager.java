package leppa.planarartifice.core;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import static leppa.planarartifice.core.PATransformer.*;

public class TransformCraftingManager extends Transformer {
    void transform(ClassNode node) {
        PlanarArtificeCore.LOGGER.info("[PA, CORE] Patching in generateTagsFromRecipes");
        MethodNode m = findMethod(node, "generateTagsFromRecipes", "(Lnet/minecraft/item/ItemStack;Ljava/util/ArrayList;)Lthaumcraft/api/aspects/AspectList;");
        assert m != null;
        int offset = findMethodCall(m, 0, "generateTagsFromCraftingRecipes", "(Lnet/minecraft/item/ItemStack;Ljava/util/ArrayList;)Lthaumcraft/api/aspects/AspectList;", "thaumcraft/common/lib/crafting/ThaumcraftCraftingManager");
        if (offset != -1) offset = findOpcode(m, offset, Opcodes.ARETURN);
        AbstractInsnNode q = m.instructions.get(offset); // this should be areturn
        PlanarArtificeCore.LOGGER.info("[PA, CORE] Offset: " + offset + ", " + q.getOpcode() + " > " + q.getNext().getOpcode());
        // insert if (ret == null) return generateTagsFromRecipes(stack, history);
        m.instructions.insert(q, new InsnNode(Opcodes.ARETURN));
        m.instructions.insert(q, new MethodInsnNode(Opcodes.INVOKESTATIC, "leppa.planarartifice.util.AspectUtils", "generateTagsFromRecipes", "(Lnet/minecraft/item/ItemStack;Ljava/util/ArrayList;)Lthaumcraft/api/aspects/AspectList;"));
        m.instructions.insert(q, new VarInsnNode(Opcodes.ALOAD, 1));
        m.instructions.insert(q, new VarInsnNode(Opcodes.ALOAD, 0));
        LabelNode label = new LabelNode();
        m.instructions.insert(q, label);
        m.instructions.insertBefore(q.getPrevious(), new JumpInsnNode(Opcodes.IFNULL, label));
        PlanarArtificeCore.LOGGER.info("[PA, CORE] Successfully patched in generateTagsFromRecipes");
    }
}
