package com.example.customlint

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

/**
 * This detector will report any usages of the android.util.Log.
 */
class AndroidLogDetector : Detector(), SourceCodeScanner {

    override fun getApplicableMethodNames(): List<String> =
        listOf("tag", "format", "v", "d", "i", "w", "e", "wtf")

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        val evaluator = context.evaluator
        if (evaluator.isMemberInClass(method, "android.util.Log")) {
            reportUsage(context, node)
        }
    }

    private fun reportUsage(context: JavaContext, node: UCallExpression) {
        context.report(
            issue = ISSUE,
            scope = node,
            location = context.getCallLocation(
                call = node,
                includeReceiver = true,
                includeArguments = true
            ),
            message = "android.util.Log usage is forbidden."
        )
    }

    companion object {
        private val IMPLEMENTATION = Implementation(
            AndroidLogDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )

        val ISSUE: Issue = Issue
            .create(
                id = "AndroidLogDetector",
                briefDescription = "The android Log should not be used",
                explanation = """
                For amazing showcasing purposes we should not use the Android Log. We should the
                AmazingLog instead.
            """.trimIndent(),
                category = Category.CORRECTNESS,
                priority = 10,
                severity = Severity.FATAL,
                androidSpecific = true,
                implementation = IMPLEMENTATION
            )

    }

}