package org.semtech

object Population {
  val linesStream = new LineStreamProc();

  // Creating a list of Strings
  def main(args: Array[String]) = {
    try {
      //C:\\Users\\19082\\SemtechCodeScalaExercise\\
      val filepath: String = s"src\\main\\resources\\${args(0)}"
      val lines = linesStream.fileLines(filepath).drop(1);
      //lines = lines.drop(1);
      val totalPopByDept = linesStream.getTotalPopulationByDept(lines);
      val maxPopByDept = linesStream.getMaxPopulationByDept(lines);
      val minPopAllDept = linesStream.getMinPopulationForAllDepts(lines);

      for (s: String <- maxPopByDept.keys) {
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