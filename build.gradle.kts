import net.fabricmc.loom.configuration.ide.RunConfigSettings

plugins {
	kotlin("jvm")
	id("fabric-loom")
	`maven-publish`
	java
}

group = property("maven_group")!!
version = property("mod_version")!!

base {
	archivesName.set(property("archives_base_name") as String)
}

repositories {
	mavenCentral()
	// Add repositories to retrieve artifacts from in here.
	maven { url = uri("https://maven.shedaniel.me/") }
	maven { url = uri("https://maven.terraformersmc.com/releases/") }
	maven(uri("https://maven.isxander.dev/releases"))
	maven("https://maven.terraformersmc.com/") { name = "Terraformers" }
	maven("https://maven.midnightdust.eu/releases")

	exclusiveContent {
		forRepository {
			maven { name = "Modrinth"; url = uri("https://api.modrinth.com/maven") }
		}
		filter { includeGroup("maven.modrinth") }
	}
	/* TeamReborn Energy */
	maven { name = "ModMaven"; url = uri("https://modmaven.dev") }

	/* owo-lib */
	maven { url = uri("https://maven.wispforest.io/releases/") }

	/* Satin */
	maven {
		name = "Ladysnake Mods"
		url = uri("https://maven.ladysnake.org/releases")
		content {
			includeGroup("io.github.ladysnake")
			includeGroup("org.ladysnake")
			includeGroupByRegex("dev\\.onyxstudios.*")
		}
	}

	/* CC:T */
	maven {
		url = uri("https://maven.squiddev.cc")
		content {
			includeGroup("cc.tweaked")
		}
	}
}


val lib = file(".lib/1201")

dependencies {
	minecraft("com.mojang:minecraft:${property("minecraft_version")}")
	mappings(loom.officialMojangMappings())
	modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

	// Utility
	modRuntimeOnly("maven.modrinth:emi:1.1.22+1.20.1+fabric")
	modRuntimeOnly("com.terraformersmc:modmenu:${property("modmenu_version")}")

	/* CC:T */
	modCompileOnly("cc.tweaked:cc-tweaked-${property("minecraft_version")}-fabric-api:${property("cc_version")}")
	modRuntimeOnly("cc.tweaked:cc-tweaked-${property("minecraft_version")}-fabric:${property("cc_version")}")

	/* TeamReborn Energy */
	modImplementation("teamreborn:energy:3.0.0")
	modApi("teamreborn:energy:3.0.0")
	include("teamreborn:energy:3.0.0")

	//
	/* oωo */
	modImplementation("io.wispforest:owo-lib:${property("owo_version")}")
	include("io.wispforest:owo-sentinel:${property("owo_version")}")

	/* Lua */
	implementation("org.luaj:luaj-jse:3.0.1")
	modImplementation("org.luaj:luaj-jse:3.0.1")
	include("org.luaj:luaj-jse:3.0.1")

	/* Config */
	modImplementation("dev.isxander:yet-another-config-lib:${property("yacl_version")}+1.20.1-fabric")

	/* Runtime funsies */
//	modRuntimeOnly("maven.modrinth:isometric-renders:0.4.5+1.20")



	/* Decompiler and Mapper for .lib folder */
	if (lib.exists() && lib.isDirectory) {
		lib.listFiles { file ->
			file.isFile && file.extension == "jar"
		}?.forEach { jar ->
			modImplementation(files(jar))
		}
	}
}

loom {
	runs {
		// 1. Configure the existing "client" run
		val clientSettings = getByName("client") as RunConfigSettings

		// 2. Create the secondary client in two steps
		// This avoids the "No type arguments expected" error on the create() method
		val secondary = create("clientKuko")
		(secondary as RunConfigSettings).apply {
			inherit(clientSettings)
			programArg("--username=kuko")
			programArg("--uuid=f3147754-7646-4687-8804-7acdf233c159")
		}
	}
}

tasks {
	// (Optional) you can also add other args/flags like gameDir, etc.

	processResources {
		inputs.property("version", project.version)
		filesMatching("fabric.mod.json") {
			expand(mapOf("version" to project.version))
		}
	}

	jar {
		from("LICENSE")
	}

	withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		compilerOptions {
			jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
		}
	}
}

java {
	withSourcesJar()
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

tasks.named<Jar>("sourcesJar") {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
}


publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			artifactId = project.property("archives_base_name") as String
			from(components["java"])
		}
	}

	repositories {
		// Add your repositories here
	}
}