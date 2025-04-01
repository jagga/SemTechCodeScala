package org.semtech

import org.scalatestplus.junit.AssertionsForJUnit
import org.junit.Test
import org.junit.Assert.{assertEquals, assertNotEquals}
import org.scalatestplus.junit
import scala.collection.immutable.List

class  PopulationMainTest  extends AssertionsForJUnit {
  var lineStrmObj = new LineStreamProc()
  var lines = lineStrmObj.fileLines(s"src\\main\\resources\\population_2019.csv").drop(1)
  var objs = lineStrmObj.getPopulationObjs(lines)
  val totalPopByDept = lineStrmObj.getTotalPopulationByDept(objs)
  val maxPopByDept = lineStrmObj.getMaxPopulationByDept(objs)
  val minPopAllDept = lineStrmObj.getMinPopulationForAllDepts(objs)

  def setUpLines(file: String): List[String] = {
    val lines = scala.io.Source.fromFile(file).getLines().toList
    lines
  }

@Test def totalPopulationByDepartment(): Unit = {
  lines = setUpLines("src/test/resources/totpopbydept.csv").drop(1)
  objs = lineStrmObj.getPopulationObjs(lines)
  val totalPopByDeptT = lineStrmObj.getTotalPopulationByDept(objs)

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
  lines = setUpLines("src/test/resources/maxpopbydept.csv").drop(1)
  objs = lineStrmObj.getPopulationObjs(lines)
  val maxPopByDeptT = lineStrmObj.getMaxPopulationByDept(objs)

  assertEquals(maxPopByDept.get("MANCHE"), maxPopByDeptT.get("MANCHE"))
  assertEquals(maxPopByDept.get("GIRONDE"), maxPopByDeptT.get("GIRONDE"))
  assertEquals(maxPopByDept.get("COTE-D'OR"), maxPopByDeptT.get("COTE-D'OR"))
  assertEquals(maxPopByDept.get("COTES-D'ARMOR"), maxPopByDeptT.get("COTES-D'ARMOR"))
}

@Test def minPopulationByAllDepartments(): Unit = {
        lines = setUpLines(s"src\\test\\resources\\minpopbyalldept.csv").drop(1)
        objs = lineStrmObj.getPopulationObjs(lines)
        val minPopAllDeptT = lineStrmObj.getMinPopulationForAllDepts(objs)

        val minCities: Int = minPopAllDept.keys.min;
        val minCitiesT: Int = minPopAllDeptT.keys.min;
        assertNotEquals(minCities, minCitiesT)
    }
}




