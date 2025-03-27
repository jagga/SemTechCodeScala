package org.semtech

import scala.language.postfixOps

class PopObj(var indi:String) {
    var sdi =  Array("", "", "", "") ;
    val di = indi.split(";");
    for ( i <- 0 to di.length-1) sdi(i) = di(i);
    val k = if (sdi(0) == null || sdi(0).isEmpty) ""; else sdi(0);  //(sdi(0)===null||sdi(0).isEmpty)?"":sdi(0).trim;
    val c = if (sdi(3) == null || sdi(3).isEmpty) ""; else sdi(3);    //(sdi(3) == null || sdi(3).isEmpty) ? "" : (sdi(3));
    val p = if (sdi(2) == null) -1; else if (sdi(2).isEmpty) 0; else sdi(2).toInt;  // (sdi(2) == null) ? -1 : ((sdi(2).isEmpty) ? 0 : sdi(2).toInt);
}

class LineStreamProc {
  def fileLines(file: String) = {
    val lines = scala.io.Source.fromFile(file).getLines().toList
    lines;
  }
    // Employubg Stream of lines from input file get Max Population for Each Department
    def getMaxPopulationByDept(lines: List[String]): Predef.Map[String, Int] = {
      val maxPopByDept = lines
        .map(line => new PopObj(line))
        .filter(e => e.p > -1)
        .groupMapReduce(e => e.c)(e => e.p)((a, b) => math.max(a, b));
      return maxPopByDept;
    }
    // Employubg Stream of lines from input file get Total Population for Each Department
    def getTotalPopulationByDept(lines: List[String]): Predef.Map[String, Int] = {
      val totalPopByDept = lines
        .map(line => new PopObj(line))
        .filter(e => e.p > -1)
        .groupMapReduce(_.c)(e => e.p)((a, b) => math.addExact(a, b));

      return totalPopByDept
    }
  var minKey:Int = 0;
  // Employubg Stream of lines from input file get Minimum Population for All Departments
    def getMinPopulationForAllDepts(lines: List[String]) : scala.collection.immutable.Map[Int, List[String]]  = {
      var minPopAllDept = lines
        .map(line => new PopObj(line))
        .filter(e => e.p > 0)
        .groupMapReduce(_.c)(e => e.p)((a, b) => math.min(a, b))
        .groupMap(e => (e._2))(e => e._1)
        .map(e => (e._1, e._2.toList));

      // println (minPopAllDept.toString());
       return minPopAllDept;
    }
}


