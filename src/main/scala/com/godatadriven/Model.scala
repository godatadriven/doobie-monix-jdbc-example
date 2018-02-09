package com.godatadriven

trait Code {
  val code: String
  val column: String
}
object Code {
  case class TwoDigitCode private(code: String) extends Code {
    require(code.length == 2)
    val column = "code2"
  }
  case class ThreeDigitCode private(code: String) extends Code {
    require(code.length == 3)
    val column = "code"
  }
  def apply(input: String): Code = input.length match {
    case 2 => TwoDigitCode(input)
    case 3 => ThreeDigitCode(input)
    case _ => ???
  }
}

case class Country(
  code: Code.ThreeDigitCode,
  name: String,
  continent: String,
  surfaceArea: Float,
  independenceYear: Option[Short]
) {
  override val toString = s"$name ($code), $continent"
}

case class City(
  id: Int,
  name: String,
  country: Country
) {
  override val toString = s"$name, $country"
}
