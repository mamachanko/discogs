package com.example

import org.hibernate.validator.constraints.NotBlank
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

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
    fun ingestRecord(@Valid record: Record, bindingResult: BindingResult): String {
        return if (bindingResult.hasErrors()) {
            "records"
        } else {
            "results"
        }
    }

}

data class Record(@get:NotBlank var artistName: String = "")

