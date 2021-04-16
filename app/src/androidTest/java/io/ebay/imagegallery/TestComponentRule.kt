package io.ebay.imagegallery

import android.content.Context
import io.ebay.imagegallery.di.component.DaggerTestComponent
import io.ebay.imagegallery.di.component.TestComponent
import io.ebay.imagegallery.di.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context) : TestRule {

    var testComponent : TestComponent? = null

    fun getContext() = context

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement(){
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setDaggerTestComponentInApplication()
                    base.evaluate()
                }finally {
                    testComponent = null
                }
            }
        }
    }

    private fun setDaggerTestComponentInApplication(){
        val application = context.applicationContext as ImageGalleryApp
        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(
                ApplicationTestModule(
                    application
                )
            )
            .build()
        application.setComponent(testComponent!!)
    }
}