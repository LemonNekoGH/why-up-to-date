import moe.lemonneko.why.extensions.TheSameInputExtension

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        // 把自己的插件加到classpath
        // why-up-to-date是artifactId，如果不自定义的话，默认是rootProject.name
        classpath("moe.lemonneko.why:why-up-to-date:1.0")
    }
}

apply(plugin = "moe.lemonneko.why.plugin")

// Kotlin DSL只能这样写
configure<TheSameInputExtension> {
    input { // 但是里面就可以用这种语法了
        input = "这儿是输出，如果不改这句话的话，运行两遍theSameInput时第二遍会被UP-TO-DATE"
        output = File(projectDir, "output")
    }
}