package moe.lemonneko.why.tasks

import moe.lemonneko.why.extensions.TheSameInputExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

/**
 * 2020-12-11
 * 这是第一个例子，使用TheSameInputExtension来控制输入
 * 经测试，运行符合预期，输入相同，输出也相同即为UP-TO-DATE
 * @see moe.lemonneko.why.extensions.TheSameInputExtension
 */
open class TheSameInputTask : DefaultTask() {
    @Nested
    lateinit var data: TheSameInputExtension.ExtensionData

    /**
     * 非常简单，就是把用户设置的内容输出一下就行了
     */
    @TaskAction
    fun action() {
        if (!this::data.isInitialized) {
            return
        }
        if (!data.output.exists()) {
            val parent = data.output.parentFile
            if (!parent.exists()) {
                parent.mkdirs()
            }
            data.output.createNewFile()
        }
        data.output.writeText(data.input)
    }
}