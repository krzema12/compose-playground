#!/usr/bin/env kotlin
@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.15.0")

import it.krzeminski.githubactions.actions.jamesives.GithubPagesDeployActionV4
import it.krzeminski.githubactions.domain.RunnerType.UbuntuLatest
import it.krzeminski.githubactions.domain.triggers.Push
import it.krzeminski.githubactions.dsl.workflow
import it.krzeminski.githubactions.yaml.writeToFile
import java.nio.file.Paths

workflow(
    name = "Deploy",
    on = listOf(Push()),
    sourceFile = Paths.get(".github/workflows/deploy.main.kts"),
    targetFile = Paths.get(".github/workflows/deploy.yaml"),
) {
    job("deploy", runsOn = UbuntuLatest) {
        run("./gradlew build")
        uses(
            GithubPagesDeployActionV4(
                branch = "gh-pages",
                folder = "build/distributions",
            )
        )
    }
}.writeToFile()
