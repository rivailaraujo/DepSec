package com.rivail.controllers

import com.rivail.services.VulnerabilityScannerService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/api/scanner")
@ExecuteOn(TaskExecutors.IO) // resolve bloqueio do Event Loop
class ScannerController {

    private final VulnerabilityScannerService vulnerabilityScannerService

    ScannerController(VulnerabilityScannerService vulnerabilityScannerService) {
        this.vulnerabilityScannerService = vulnerabilityScannerService
    }

    @Get("/{packageName}/{version}")
    Map<String, Object> scanPackage(String packageName, String version) {
        println "🔍 Analisando pacote: ${packageName}@${version}"
        def result = vulnerabilityScannerService.scan(packageName, version)

        return [
                package: "${packageName}@${version}".toString(),
                results: result
        ]
    }
}
