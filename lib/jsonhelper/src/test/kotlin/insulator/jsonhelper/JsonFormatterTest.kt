package insulator.jsonhelper

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class JsonFormatterTest : StringSpec({

    "format json return a right collection" {
        // arrange
        val sut = JsonFormatter()
        // act
        val res = sut.formatJsonString("This is not a json")
        // assert
        res.shouldBeLeft()
    }

    "format empty object" {
        // arrange
        val sut = JsonFormatter()
        // act
        val res = sut.formatJsonString("{}")
        // assert
        res.map { it -> it.map { it.text }.reduce { a, b -> a + b } } shouldBeRight "{\n  \n}"
    }

    "format empty object without indent" {
        // arrange
        val sut = JsonFormatter()
        // act
        val res = sut.formatJsonString("{}", false)
        // assert
        res.map { it -> it.map { it.text }.reduce { a, b -> a + b } } shouldBeRight "{  }"
    }

    "format generic object without indent" {
        // arrange
        val sut = JsonFormatter()
        // act
        val res = sut.formatJsonString(
            """{
"int": 1, "double": 1.1, "string": "A string",
"bool": false, "empty": null, "array": [ {}, "", null, false], 
"nested": {"nested2": {"nested3": {}}}
}""".trimMargin(),
            false
        )
        // assert
        res.isRight() shouldBe true
    }

    "format object containing an empty array" {
        // arrange
        val sut = JsonFormatter()
        // act
        val res = sut.formatJsonString(
            """{ "array": [] }""".trimMargin(),
            false
        )
        // assert
        res.isRight() shouldBe true
    }

    "format an empty object" {
        // arrange
        val sut = JsonFormatter()
        // act
        val res = sut.formatJsonString(
            """{ }""".trimMargin(),
            false
        )
        // assert
        res.isRight() shouldBe true
    }

    "format an empty object 2" {
        // arrange
        val sut = JsonFormatter()
        // act
        val res = sut.formatJsonString(
            """{ "test": {} }""".trimMargin(),
            false
        )
        // assert
        res.isRight() shouldBe true
    }
})
