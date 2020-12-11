package moe.lemonneko.why

import moe.lemonneko.why.extensions.TheSameInputExtension
import moe.lemonneko.why.tasks.TheSameInputTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 一个插件中可以有许多任务（Task），而我们要重点研究的就是Task
 * 所以一个插件就够了（大概
 */
class ThePlugin : Plugin<Project> { // 实现Plugin接口之后你的类就是一个插件类了
    override fun apply(project: Project) {
        createTheSameInputTaskAndExtension(project)
    }

    private fun createTheSameInputTaskAndExtension(project: Project) {
        val extension = project.extensions.create(TheSameInputExtension.name, TheSameInputExtension::class.java)
        project.tasks.register(TheSameInputExtension.taskName, TheSameInputTask::class.java) {
            it.group = whyUpToDateTaskGroup
            // configure the task
            it.data = extension.data
        }
    }

    companion object {
        const val whyUpToDateTaskGroup = "why-up-to-date"
    }
}