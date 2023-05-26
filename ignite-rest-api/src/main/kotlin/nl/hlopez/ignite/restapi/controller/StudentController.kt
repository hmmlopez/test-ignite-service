package nl.hlopez.ignite.restapi.controller

import nl.hlopez.domain.Student
import nl.hlopez.ignite.restapi.service.IgniteStudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/student")
class StudentController(val studentService: IgniteStudentService) {

    @PostMapping
    fun addStudent(@RequestBody student: Student) : ResponseEntity<Boolean> {
        val added = studentService.addStudent(student)
        return ResponseEntity(added, HttpStatus.OK)
    }

}