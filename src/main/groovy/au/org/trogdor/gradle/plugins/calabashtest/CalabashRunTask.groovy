package au.org.trogdor.gradle.plugins.calabashtest

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

/**
 * Created by chrisfraser on 25/07/2014.
 */
class CalabashRunTask extends DefaultTask {
    String appBundlePath
    String featuresDir
    String format
    String outDir
    TestCase test

    @TaskAction
    def run() {
        project.file(outDir).mkdir()

        List appEnv = appBundlePath ? ["APP_BUNDLE_PATH=$appBundlePath"] : []
        def env = System.getenv().collect {k,v -> "$k=$v"}
        def process = generateExecCommand().execute(env+appEnv, project.projectDir)
        process.consumeProcessOutput(System.out, System.err)
        process.waitFor()

        if (!process.exitValue())
            throw new TaskExecutionException("Calabash failed");
    }

    def generateExecCommand() {
        def commandLine = [
            'cucumber',
            '--format', format,
            '--out', "${project.file(outDir)}/${test.name}.html"
        ]

        if (test.exclude)
            commandLine += ['--exclude', test.exclude]

        test.tags.each { commandLine += ['--tags', it] }
        test.nameMatches.each { commandLine += ['--name', it]}

        commandLine += [ project.file(featuresDir) ]

        return commandLine
    }
}
