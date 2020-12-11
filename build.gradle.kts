plugins {
    java
    `java-gradle-plugin` // 写插件必须应用的插件
    kotlin("jvm") version "1.3.72" // 如果用Kotlin写插件，目前只能用1.3.72版本
    `maven-publish` // 如果要发布到MavenCentral所需要的插件，这里我们只是需要发布到本地而已
}

group = "moe.lemonneko.why"
version = "1.0"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("First Plugin") {
            id = "moe.lemonneko.why.plugin"
            implementationClass = "moe.lemonneko.why.ThePlugin"
        }
    }
}

dependencies {
    implementation(gradleApi()) // 需要这个依赖不然你找不到Plugin类
    implementation(kotlin("stdlib"))
    testImplementation("junit", "junit", "4.12")
}
