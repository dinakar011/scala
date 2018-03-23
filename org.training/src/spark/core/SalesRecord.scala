package spark.core

case class SalesRecord (id:Int,fname:String,lname:String,sal:Double,dept:String){
  
}

object SalesRecord {
  
  def parse(x:String):Either [MalFormed,SalesRecord]= {
    
    val y =x.split(",")
    if (y.length!=5) Left(new MalFormed)
    else Right(SalesRecord(y(0).toInt,y(1),y(2),y(3).toDouble,y(4)))
    
  }
}

class MalFormed { }