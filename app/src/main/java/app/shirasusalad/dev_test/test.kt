package app.shirasusalad.dev_test

//fun hoge(){
//    val school = arrayOf("shark", "salmon", "minnow")
//    for((index, elemenet) in school.withIndex()){
//        println("Item at $index is $elemenet")
//    }
//}
//

//fun main(){
//    val p: Person = Person("たろう",23)
//    println("pの名前 : ${p.name}")
//    println("pの名前 : ${p.age}")
//}
//
//class Person(val name: String, val age: Int){
////    val name: String
////    val age: Int
//}

//fun main(){
//    val car = Car("赤")
//    car.drive(100.0)
//}
//
//class Car(val color: String){
//    fun drive(distance: Double){
//        println("${color}色の車が${distance}km走りました")
//    }
//}

fun main(){
    val car = Car(company = "TOYOTA")
    car.showCar()
}

class Car(val color: String = "青", val type: String = "プリウス"){
    init{
        println("Carインスタンスが生成されました")
    }
    init{
        println("${color}色の車が生成されました")
    }
    constructor(company: String) : this(){
        println("${company}社の車が生成されました")
    }
    fun showCar(){
        println("これは、${type}の${color}色の車です.")
    }
}