package com.example

import org.hibernate.validator.constraints.NotBlank
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid
import javax.validation.constraints.Pattern

@SpringBootApplication
class DiscogsApplication

fun main(args: Array<String>) {
    SpringApplication.run(DiscogsApplication::class.java, *args)
}

@Controller
class Web {

    @GetMapping("/")
    fun getRecordForm(record: Record, model: ModelMap): String {
        model["possibleRecordConditions"] = RecordCondition.values().map { it.name }
        return "records"
    }

    @PostMapping("/")
    fun ingestRecord(@Valid record: Record, bindingResult: BindingResult, model: ModelMap): String {
        return if (bindingResult.hasErrors()) {
            model["possibleRecordConditions"] = RecordCondition.values().map { it.name }
            "records"
        } else {
            "results"
        }
    }

}

data class Record(
        @get:NotBlank(message = "The artist's name may not be empty.")
        var artistName: String = "",

        @get:Pattern(regexp = "[a-zA-Z0-9]+")
        var matrixNumber: String = "",

        var recordCondition: RecordCondition = RecordCondition.VeryGood
)

enum class RecordCondition {
    Mint, NearMint, VeryGood, Good, Worn
}

