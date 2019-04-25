package com.newsapp.core

interface BirdsFlyAndFindFood{
    public fun setPrintForBird()
}


class Eagle() : BirdsFlyAndFindFood {
    override fun setPrintForBird() {
        print("Eagle find food")
    }
}

class Cookoo(birds : BirdsFlyAndFindFood) : BirdsFlyAndFindFood by birds{
    override fun setPrintForBird() {
        print("Cookoo find food")
    }
}

interface Marks {
    fun printMarks()
}

class StdMarks() : Marks {
    override fun printMarks() { println("printed marks") }
}

class CsvMarks() : Marks {
    override fun printMarks() { println("printed csv marks") }
}

interface Totals {
    fun printTotals()
}

class StdTotals : Totals {
    override fun printTotals() { println("calculated and printed totals") }
}

class CheatTotals : Totals {
    override fun printTotals() { println("calculated and printed higher totals") }
}

class Student(val studentId: Int, marks: Marks, totals: Totals)
    : Marks by marks, Totals by totals

fun main(args:Array<String>) {

    var eagle : Eagle = Eagle()
    eagle.setPrintForBird()
    var cookoo : Cookoo = Cookoo(eagle)
    cookoo.setPrintForBird()


    val student = Student(1,StdMarks(), StdTotals())
    student.printMarks()
    student.printTotals()
    val cheater = Student(1,CsvMarks(), CheatTotals())
    cheater.printMarks()
    cheater.printTotals()
}