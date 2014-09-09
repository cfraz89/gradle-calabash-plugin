package au.org.trogdor.gradle.plugins.calabashtest

import org.gradle.api.Project

/**
 * Created by chrisfraser on 25/07/2014.
 */
class TestCase {
    Project project
    String name
    List<String> tags
    List<String> nameMatches
    String exclude

    TestCase(Project project, String name) {
        this.project = project
        this.name = name

        this.tags = []
        this.nameMatches = []
        this.exclude = ""
    }

    def tag(List<String> tag) {
        this.tags = tag
    }

    def tag(String tag) {
        this.tags = [ tag ]
    }

    def nameMatches(List<String> nameMatches) {
        this.nameMatches = nameMatches
    }

    def nameMatches(String nameMatches) {
        this.nameMatches = [ nameMatches ]
    }

    def exclude(String exclude) {
        this.exclude = exclude
    }
}
