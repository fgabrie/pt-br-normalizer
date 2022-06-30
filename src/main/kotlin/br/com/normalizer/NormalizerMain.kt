package br.com.normalizer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NormalizerMain

fun main(args: Array<String>) {
    runApplication<NormalizerMain>(*args)
}
