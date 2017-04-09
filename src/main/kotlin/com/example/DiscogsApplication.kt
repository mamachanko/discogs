package com.example

import org.hibernate.validator.constraints.NotBlank
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@SpringBootApplication
class DiscogsApplication

fun main(args: Array<String>) {
    SpringApplication.run(DiscogsApplication::class.java, *args)
}

@Controller
class Web {

    @GetMapping("/")
    fun getRecordForm(record: Record): String {
        return "records"
    }

    @PostMapping("/")
    fun ingestRecord(record: Record, bindingResult: BindingResult, model: ModelMap): String {
        println("---")
        println("INGESTING $record")
        println("---")
        model["saved"] = !bindingResult.hasErrors()
        return "records"
    }

}

data class Record (@NotBlank var artistName: String = "")

