package com.meneses.circuitbreaker.controller.v1

import com.meneses.circuitbreaker.service.v1.CatFactService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class CatFactsController(
    private val catFactService: CatFactService
) {
    @GetMapping("/cat_fact")
    suspend fun getCatFact() = catFactService.getCatFact()
}