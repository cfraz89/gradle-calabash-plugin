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
        this.appBundlePath = appBundlePath
    }

    def featuresDir(String featuresDir) {
        this.featuresDir = featuresDir
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
