package org.semtech

object Population {
  val linesStream = new LineStreamProc();

  // Creating a list of Strings
  def main(args: Array[String]) = {
    try {
      //C:\\Users\\19082\\SemtechCodeScalaExercise\\
      val filepath: String = s"src\\main\\resources\\${args(0)}"
      var lines = linesStream.fileLines(filepath).drop(1);
      val objs:List[PopObj] = linesStream.getPopulationObjs(lines)
      //lines = lines.drop(1);
      val totalPopByDept = linesStream.getTotalPopulationByDept(objs);
      val maxPopByDept = linesStream.getMaxPopulationByDept(objs);
      val minPopAllDept = linesStream.getMinPopulationForAllDepts(objs);

      for (s: String <- maxPopByDept.keys.toSeq.sorted()) {
        val mp: Int = maxPopByDept(s);
        val tp: Int = totalPopByDept(s);
        println("Department " + s + ", Total Population " + tp.toString() + ", Max Population " + mp.toString());
      };
      val minCities: Int = minPopAllDept.keys.min;
      println("\n" + " [ " + minPopAllDept(minCities) + "] found with " + minCities.toString + " Mininum Population " + " in all Departments");
    } catch {
      case ex: Exception =>
        println(ex.printStackTrace());
        println(ex.getMessage);
      //   throw new RuntimeException(ex);
    }
  }
}