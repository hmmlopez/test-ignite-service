package nl.hlopez.ignite.server.service

import nl.hlopez.domain.Student
import nl.hlopez.ignite.service.StudentService
import org.apache.ignite.Ignite
import org.apache.ignite.IgniteQueue
import org.apache.ignite.configuration.CollectionConfiguration
import org.apache.ignite.resources.IgniteInstanceResource
import org.apache.ignite.resources.ServiceContextResource
import org.apache.ignite.services.ServiceContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class StudentServiceImpl : StudentService {

    @IgniteInstanceResource
    lateinit var ignite: Ignite

    @ServiceContextResource
    lateinit var ctx: ServiceContext

    private val logger: Logger = LoggerFactory.getLogger(StudentServiceImpl::class.java)
    override fun addToQueue(student: Student): Boolean {
        val studentQueue = getStudentQueue()
        val retValue = studentQueue.add(student)
        logger.info("Added $student to the queue. The size of the queue is ${studentQueue.size}")
        return retValue
    }

    override fun execute() {
        while (ctx.isCancelled.not()) {
            val studentQueue = getStudentQueue()
            val student = studentQueue.poll(10, TimeUnit.SECONDS)
            if (student != null) {
                logger.info("Consumed $student from queue, size is ${studentQueue.size}")
                doSomeProcessing()
            }
        }
    }

    private fun getStudentQueue(): IgniteQueue<Student> {
        return ignite.queue(Student::class.simpleName, 0, CollectionConfiguration())
    }

    private fun doSomeProcessing() {
        TimeUnit.MILLISECONDS.sleep(5)
    }
}