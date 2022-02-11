import net.minecraftforge.gradle.common.util.RunConfig
import wtf.gofancy.fancygradle.script.extensions.deobf

buildscript {}
plugins {
    `java-library`
    `maven-publish`
    idea
    id("net.minecraftforge.gradle") version "5.1.+"
    id("de.undercouch.download") version "5.0.1"
    id("wtf.gofancy.fancygradle") version "1.1.1-6"
}

evaluationDependsOnChildren()
java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

val versionMcMajor: String by project
val versionMc: String by project
val versionForge: String by project
val mappingsChannel: String by project
val mappingsVersion: String by project
val versionThis: String by project
val versionJEI: String by project
val versionBaubles: String by project
val versionThaumcraft: String by project
val versionAugmentation: String by project
val versionHammerLib: String by project
val versionThaumAdds: String by project
val versionPatchouli: String by project
val versionBewitchment: String by project
val versionEmbers: String by project
val versionSoot: String by project
val versionForestry: String by project
val versionMagicBees: String by project
val versionMantle: String by project
val versionTConstruct: String by project
val versionCTM: String by project
val versionXercaPaint: String by project

version = versionThis
group = "leppa.planarartifice"
setProperty("archivesBaseName", "planarartifice")

minecraft {
    mappings(mappingsChannel, mappingsVersion)

    runs {
        val config = Action<RunConfig> {
            properties(
                mapOf(
                    "forge.logging.markers" to "SCAN,REGISTRIES,REGISTRYDUMP,COREMODLOG",
                    "forge.logging.console.level" to "debug"
                )
            )
            workingDirectory = project.file("run").canonicalPath
            source(sourceSets["main"])
        }

        create("client", config)
        create("server", config)
    }
}

fancyGradle {
    patches {
        resources
        coremods
        asm
    }
}

repositories {
    maven {
        name = "Cursemaven"
        url = uri("https://www.cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
    maven {
        name = "Progwml6 maven"
        url = uri("https://dvs1.progwml6.com/files/maven")
    }
    maven {
        name = "DragonForge"
        url = uri("https://raw.github.com/dragon-forge/maven/master")
    }
    maven {
        name = "thiakil"
        url = uri("https://maven.thiakil.com")
    }
}

dependencies {
    minecraft(group = "net.minecraftforge", name = "forge", version = "${versionMc}-${versionForge}")
    implementation(fg.deobf(group = "mezz.jei", name = "jei_${versionMc}", version = versionJEI))
    implementation(group = "com.azanor.baubles", name = "Baubles", version = "${versionMcMajor}-${versionBaubles}")
    implementation(fg.deobf(group = "curse.maven", name = "thaumcraft-223628", version = versionThaumcraft))
    implementation(group = "curse.maven", name = "thaumicaugmentation-319441", version = versionAugmentation)
    implementation("tk.zeitheron.HammerLib:HammerLib-${versionMc}:${versionHammerLib}:deobf")
    implementation("tk.zeitheron.ThaumicAdditions:ThaumicAdditions-${versionMc}:${versionThaumAdds}:deobf")
    implementation(group = "curse.maven", name = "patchouli-306770", version = versionPatchouli)
    implementation(group = "curse.maven", name = "bewitchment-285439", version = versionBewitchment)
    implementation(group = "curse.maven", name = "embers-rekindled-300777", version = versionEmbers)
    implementation(group = "curse.maven", name = "soot-281528", version = versionSoot)
    implementation(group = "curse.maven", name = "forestry-59751", version = versionForestry)
    implementation(group = "curse.maven", name = "magic-bees-65764", version = versionMagicBees)
    implementation(fg.deobf(group = "slimeknights.mantle", name = "Mantle", version = "${versionMcMajor}-${versionMantle}"))
    implementation(fg.deobf(group = "slimeknights", name = "TConstruct", version = "${versionMc}-${versionTConstruct}"))
    implementation(group = "curse.maven", name = "ctm-267602", version = versionCTM)
    implementation(group = "curse.maven", name = "joy-of-painting-350727", version = versionXercaPaint)
}

tasks {
    named<ProcessResources>("processResources") { 
        // this will ensure that this task is redone when the versions change.
        inputs.property("version", project.version)
        inputs.property("mcversion", versionMc)
    
        filesMatching("mcmod.info") {
            // replace version and mcversion
            expand(
                    "version" to project.version,
                    "mcversion" to versionMc
            )
        }
    }

    withType<JavaCompile> {
        options.compilerArgs.add("-Xlint:deprecation")
    }
}