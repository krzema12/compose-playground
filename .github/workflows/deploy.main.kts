#!/usr/bin/env kotlin
@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.15.0")

import it.krzeminski.githubactions.actions.actions.CheckoutV3
import it.krzeminski.githubactions.actions.actions.SetupJavaV3
import it.krzeminski.githubactions.actions.actions.SetupJavaV3.Distribution.Adopt
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
        uses(CheckoutV3())
        uses(SetupJavaV3(javaVersion = "17", distribution = Adopt))
        run("./gradlew build")
        uses(
            GithubPagesDeployActionV4(
                branch = "gh-pages",
                folder = "build/distributions",
            )
        )
    }
}.writeToFile()
