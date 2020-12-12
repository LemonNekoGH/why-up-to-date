package moe.lemonneko.why

import moe.lemonneko.why.extensions.SimpleInputExtension
import moe.lemonneko.why.tasks.SimpleInputTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 一个插件中可以有许多任务（Task），而我们要重点研究的就是Task
 * 所以一个插件就够了（大概
 */
class ThePlugin : Plugin<Project> { // 实现Plugin接口之后你的类就是一个插件类了
    lateinit var simpleInputExtension: SimpleInputExtension
    override fun apply(project: Project) {
        createSimpleInputTaskAndExtension(project)
    }

    @Deprecated(
        "此方法无效",
        ReplaceWith(
            "configure<SimpleInputExtension>",
            imports = ["moe.lemonneko.why.extensions.SimpleInputExtension"]
        )
    )
    fun Project.simpleInput(block: SimpleInputExtension.() -> Unit) {
        block(simpleInputExtension)
    }

    private fun createSimpleInputTaskAndExtension(project: Project) {
        simpleInputExtension = project.extensions.create(SimpleInputExtension.name, SimpleInputExtension::class.java)
        project.tasks.register(SimpleInputExtension.taskName, SimpleInputTask::class.java) {
            it.group = whyUpToDateTaskGroup
            // configure the task
            it.data = simpleInputExtension.data
        }
    }

    companion object {
        const val whyUpToDateTaskGroup = "why-up-to-date"
    }
}