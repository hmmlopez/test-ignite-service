package nl.hlopez.ignite.restapi.service

import nl.hlopez.domain.Student
import nl.hlopez.ignite.service.StudentService
import org.apache.ignite.Ignite
import org.springframework.stereotype.Service

@Service
class IgniteStudentService(val ignite: Ignite) {

    fun addStudent(student: Student): Boolean {
        return getStudentService().addToQueue(student)
    }

    private fun getStudentService(): StudentService {
        return ignite.services()
            .serviceProxy(StudentService::class.simpleName, StudentService::class.java, false)
    }

}