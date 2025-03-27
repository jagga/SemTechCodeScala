package org.semtech

import org.scalatestplus.junit.AssertionsForJUnit
import org.junit.Test
import org.junit.Assert.{assertEquals, assertNotEquals}
import org.scalatestplus.junit
import scala.collection.immutable.List

class  PopulationMainTest  extends AssertionsForJUnit {
  var lines : List[String] = null
  var lineStrmObj : LineStreamProc  = new LineStreamProc()

  def setUpLines(file: String): List[String] = {
    val lines = scala.io.Source.fromFile(file).getLines().toList
    lines
  }

@Test def totalPopulationByDepartment(): Unit = {
  var lines = lineStrmObj.fileLines(s"src\\main\\resources\\population_2019.csv").drop(1)
  val totalPopByDept = lineStrmObj.getTotalPopulationByDept(lines)
  
  lines = setUpLines("src/test/resources/totpopbydept.csv").drop(1)
  val totalPopByDeptT = lineStrmObj.getTotalPopulationByDept(lines)

  assertEquals(totalPopByDept.get("MANCHE"), totalPopByDeptT.get("MANCHE"))
  assertEquals(totalPopByDept.get("GIRONDE"), totalPopByDeptT.get("GIRONDE"))
  assertEquals(totalPopByDept.get("COTE-D'OR"), totalPopByDeptT.get("COTE-D'OR"))
  assertEquals(totalPopByDept.get("COTES-D'ARMOR"), totalPopByDeptT.get("COTES-D'ARMOR"))
  assertNotEquals(totalPopByDept.get("CALVADOS"), totalPopByDeptT.get("CALVADOS"))
  assertNotEquals(totalPopByDept.get("CANTAL"), totalPopByDeptT.get("CANTAL"))
  assertNotEquals(totalPopByDept.get("CHARENTE"), totalPopByDeptT.get("CHARENTE"))
  assertNotEquals(totalPopByDept.get("CHARENTE-MARITIME"), totalPopByDeptT.get("CHARENTE-MARITIME"))
}

@Test def maxPopulationByDepartment(): Unit = {
  var lines = lineStrmObj.fileLines(s"src\\main\\resources\\population_2019.csv").drop(1)
  val maxPopByDept = lineStrmObj.getMaxPopulationByDept(lines)
  
  lines = setUpLines("src/test/resources/maxpopbydept.csv").drop(1)
  val maxPopByDeptT = lineStrmObj.getMaxPopulationByDept(lines)

  assertEquals(maxPopByDept.get("MANCHE"), maxPopByDeptT.get("MANCHE"))
  assertEquals(maxPopByDept.get("GIRONDE"), maxPopByDeptT.get("GIRONDE"))
  assertEquals(maxPopByDept.get("COTE-D'OR"), maxPopByDeptT.get("COTE-D'OR"))
  assertEquals(maxPopByDept.get("COTES-D'ARMOR"), maxPopByDeptT.get("COTES-D'ARMOR"))
}

@Test def minPopulationByAllDepartments(): Unit = {
        var lines = lineStrmObj.fileLines(s"src\\main\\resources\\population_2019.csv").drop(1)
        val minPopAllDept = lineStrmObj.getMinPopulationForAllDepts(lines)

        lines = setUpLines(s"src\\test\\resources\\minpopbyalldept.csv").drop(1)
        val minPopAllDeptT = lineStrmObj.getMinPopulationForAllDepts(lines)

        val minCities: Int = minPopAllDept.keys.min;
        val minCitiesT: Int = minPopAllDeptT.keys.min;
        assertNotEquals(minCities, minCitiesT)
    }
}




