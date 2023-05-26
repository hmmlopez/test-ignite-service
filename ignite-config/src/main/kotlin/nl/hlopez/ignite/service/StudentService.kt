package nl.hlopez.ignite.service

import nl.hlopez.domain.Student
import org.apache.ignite.services.Service

interface StudentService : Service {

    fun addToQueue(student: Student) : Boolean

}