package au.org.trogdor.gradle.plugins.calabashtest

import org.gradle.api.Project
import org.gradle.api.Plugin

class CalabashTestPlugin implements Plugin<Project> {
    public static String EXTENSION_NAME = "calabash"
    public static String TEST_PREFIX = "calabash"
    public static String TASK_GROUP = "Calabash Tests"

	void apply(Project project) {
        def extension = project.extensions.create(EXTENSION_NAME, CalabashTestExtension, project)

        project.afterEvaluate {
            extension.tests.each { addTestTask(project, extension, it) }
            addTestTask(project, extension, new TestCase(project, "AllTests"))
        }
    }

    def addTestTask(project, extension, testCase) {
        project.task("$TEST_PREFIX$testCase.name", description: "Run the calabash test $testCase.name", type: CalabashRunTask, group: TASK_GROUP) {
            appBundlePath = extension.appBundlePath
            featuresDir = extension.featuresDir
            format = extension.format
            outDir = extension.outDir
            test = testCase
        }
    }
}