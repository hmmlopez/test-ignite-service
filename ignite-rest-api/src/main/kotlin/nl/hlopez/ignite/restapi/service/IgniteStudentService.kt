package nl.hlopez.ignite.restapi.service

import nl.hlopez.domain.Student
import org.apache.ignite.Ignite
import org.apache.ignite.IgniteQueue
import org.apache.ignite.configuration.CollectionConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StudentService(val ignite: Ignite) {

    val logger: Logger = LoggerFactory.getLogger(StudentService::class.java)
//    var queue: IgniteQueue<Student>? = null

    fun addStudent(student: Student): Boolean {
//        logger.info("Added $student to the queue.")
//        if (queue == null) {
//            queue = ignite.queue(Student::class.simpleName, 0, CollectionConfiguration())
//        }
//        val retValue = queue!!.add(student)
//        logger.info("The size of the queue is ${queue!!.size}")
//        return retValue
    }

    private fun getStudentService() : nl.hlopez.ignite.service.StudentService {
        ignite.services().serviceProxy(nl.hlopez.ignite.service.StudentService::class.simpleName, nl.hlopez.ignite.service.StudentService::class.java, false)
    }

}