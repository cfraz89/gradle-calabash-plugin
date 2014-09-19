package au.org.trogdor.gradle.plugins.calabashtest

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

/**
 * Created by chrisfraser on 7/07/2014.
 */
class CalabashTestExtension {
    Project project
    String appBundlePath
    String featuresDir = "features"
    String format = "html"
    String outDir = "reports"

    NamedDomainObjectContainer<TestCase> tests

    CalabashTestExtension(Project project) {
        this.project = project
        this.tests = project.container(TestCase) {
            new TestCase(project, it)
        }
    }

    def appBundlePath(String appBundlePath) {
        this.appBundlePath = project.file(appBundlePath).path
    }

    def appBundlePath(File appBundleFile) {
        this.appBundlePath = appBundleFile.path
    }

    def featuresDir(String featuresDir) {
        this.featuresDir = project.file(featuresDir)
    }

    def featuresDir(File featuresDir) {
        this.featuresDir = featuresDir.path
    }

    def format(String format) {
        this.format = format
    }

    def outDir(String outDir) {
        this.outDir = outDir
    }

    def tests(Closure closure) {
        this.tests.configure closure
    }
}
