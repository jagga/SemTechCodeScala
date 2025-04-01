package org.semtech

import scala.language.postfixOps

class PopObj(var indi:String) {
  var sdi = Array("", "", "", "");
  val di = indi.split(";");
  for (i <- 0 to di.length - 1) sdi(i) = di(i);
  val k = if (sdi(0) == null || sdi(0).isEmpty) ""; else sdi(0);
  val c = if (sdi(3) == null || sdi(3).isEmpty) ""; else sdi(3);
  val p = if (sdi(2) == null) 0; else if (sdi(2).isEmpty) 0; else sdi(2).toInt;
}
  
class LineStreamProc {
  def fileLines(file: String) = {
    val lines = scala.io.Source.fromFile(file).getLines().toList
    lines;
  }

  def getPopulationObjs(lines: List[String]): List[PopObj] = {
     val PopObjs = lines.map(line => new PopObj(line)).toList.sortWith(_.c < _.c);
     PopObjs;
  }

    // Employubg Stream of lines from input file get Max Population for Each Department
    def getMaxPopulationByDept(objs: List[PopObj]):  scala.collection.immutable.Map[String, Int] = {
       val maxPopByDept =  objs.groupMapReduce(e => e.c)(e => e.p)((a, b) => math.max(a, b));
       maxPopByDept;
    }

    // Employubg Stream of lines from input file get Total Population for Each Department
    def getTotalPopulationByDept(objs: List[PopObj]):  scala.collection.immutable.Map[String, Int] = {
      val totalPopByDept = objs.groupMapReduce(_.c)(e => e.p)((a, b) => math.addExact(a, b));
      return totalPopByDept
    }
  var minKey:Int = 0;
  // Employubg Stream of lines from input file get Minimum Population for All Departments
    def getMinPopulationForAllDepts(objs: List[PopObj]) : scala.collection.immutable.Map[Int, List[String]]  = {
      var minPopAllDept = objs.groupMapReduce(_.c)(e => e.p)((a, b) => math.min(a, b))
        .groupMap(e => (e._2))(e => e._1)
        .map(e => (e._1, e._2.toList));

      // println (minPopAllDept.toString());
       return minPopAllDept;
    }
}