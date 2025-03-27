package org.semtech

import org.scalatestplus.junit.AssertionsForJUnit
import scala.collection.mutable.ListBuffer
import org.junit.Assert._
import org.junit.Test
import org.junit.Before


import scala.collection.mutable.ListBuffer

class ExampleSuite extends AssertionsForJUnit {

    var sb: StringBuilder = _
    var lb: ListBuffer[String] = _

    @Before def initialize() : Unit = {
        sb = new StringBuilder("ScalaTest is ")
        lb = new ListBuffer[String]
    }

    @Test def verifyEasy() : Unit = { // Uses JUnit-style assertions
        sb.append("easy!")
        assertEquals("ScalaTest is easy!", sb.toString)
        assertTrue(lb.isEmpty)
        lb += "sweet"
        try {
            "verbose".charAt(-1)
            fail()
        }
        catch {
            case e: StringIndexOutOfBoundsException => // Expected
        }
    }

    @Test  def verifyFun(): Unit = { // Uses ScalaTest assertions
        sb.append("fun!")
        assert(sb.toString === "ScalaTest is fun!")
        assert(lb.isEmpty)
        lb += "sweeter"
        intercept[StringIndexOutOfBoundsException] {
            "concise".charAt(-1)
        }
    }
}













