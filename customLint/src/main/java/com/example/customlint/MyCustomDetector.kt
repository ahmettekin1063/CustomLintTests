package com.example.customlint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.google.common.annotations.Beta
import com.intellij.psi.JavaElementVisitor
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import org.jetbrains.uast.*
import org.w3c.dom.Element
import java.util.*

class MyCustomDetector : Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>>?
        = listOf(UElement::class.java)

    override fun createUastHandler(context: JavaContext) =
        NamingPatternHandler(context)

    class NamingPatternHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitElement(node: UElement) {
            when (node){
                is UMethod -> {
                    if (node.name?.contains("ahmet") == true){
                        report(node)
                    }
                }

                is UClass -> {
                    if (node.name?.contains("MainActivity") == true){
                        report(node)
                    }
                }
            }
        }

        private fun report(node: UElement){
            context.report(
                ISSUE,
                node,
                context.getNameLocation(node),
                "TEST TEST TEST"
            )
        }

    }

    companion object {
        private val IMPLEMENTATION = Implementation(
            MyCustomDetector::class.java,
            EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
        )

        val ISSUE: Issue = Issue
            .create("NamingPattern",
                "TEST TEST TEST TEST TEST",
                "TEST TEST TEST TEST TEST ",
                Category.CORRECTNESS,
                5,
                Severity.FATAL,
                IMPLEMENTATION
            )

    }

}