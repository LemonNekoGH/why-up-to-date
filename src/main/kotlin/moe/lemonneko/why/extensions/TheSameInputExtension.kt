package moe.lemonneko.why.extensions

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import java.io.File
import javax.inject.Inject

/**
 * 2020-12-11
 * 这是第一个例子需要用到的扩展，这里定义了TheSameInputTask的输入
 * @see moe.lemonneko.why.tasks.TheSameInputTask
 */
open class TheSameInputExtension @Inject constructor(objectFactory: ObjectFactory) {
    var data: ExtensionData = objectFactory.newInstance(ExtensionData::class.java)

    fun input(action: Action<in ExtensionData>) {
        action.execute(data)
    }

    /**
     * 听说这样写可以支持Kotlin DSL
     */
    fun input(action: ExtensionData.() -> Unit) {
        action(data)
    }

    open class ExtensionData {
        @Input
        var input = "this is input"

        @OutputFile
        lateinit var output: File
    }

    companion object {
        const val name = "theSameInput"
        const val taskName = name
    }
}