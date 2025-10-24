package com.rivail.controllers

import com.rivail.models.VulnerabilityReport
import com.rivail.services.VulnerabilityScannerService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/api/scanner")
@ExecuteOn(TaskExecutors.IO)
class VulnerabilityScannerController {

    private final VulnerabilityScannerService scannerService

    VulnerabilityScannerController(VulnerabilityScannerService scannerService) {
        this.scannerService = scannerService
    }

    @Get("/")
    VulnerabilityReport scan(@QueryValue String packageName, @QueryValue String version) {
        def sonatype = scannerService.fetchSonatypeVulnerabilities(packageName, version)
        def github = scannerService.fetchGithubVulnerabilities(packageName)
        return scannerService.combineResults(sonatype, github, packageName, version)
    }
}
