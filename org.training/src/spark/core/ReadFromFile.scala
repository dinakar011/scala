package spark.core

import scala.io.Source

object ReadFromFile {
  
  def main(args:Array[String]){
    
    val bs = Source.fromFile(args(0))
    
    val lines = bs.getLines()
    
    import SalesRecord._
    
    val salesObj = lines.toList.map(record=>{
      
      val obj = SalesRecord.parse(record)
      
      if (obj.isRight) (true,obj.right.get)
        
      else (false,record)
      
    })
    
    
    val goodRecs = salesObj.filter(_._1 == true).map(_._2).map(_.asInstanceOf[SalesRecord])
    
    val badRecs = salesObj.filter(_._1 == false).map(_._2)
    
    println("Good Records:")
    
    goodRecs.foreach(println)
    
    println("\nBad records:")
    
    badRecs.foreach(println)
    
    
    val allSal = goodRecs.map(_.sal).toList
    
    val totSal = allSal.sum
    
    val avgSal = totSal/allSal.size
    
    println(s"\nTotal Salary: $totSal")
    
    println(s"Average Salary: $avgSal\n")
    
    goodRecs.map(x=>(x.dept,x.sal)).groupBy(_._1) .map(x=>(x._1, x._2.size, x._2.map(_._2).sum/x._2.size ))/// x._2.size))

    .foreach(record => record  match {  case (x,y,z) => println(s"$x Department has $y members and Average Salary: $z")  } )  
    
    
  }
  
}