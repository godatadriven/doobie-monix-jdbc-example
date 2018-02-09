package com.godatadriven

import doobie._
import doobie.implicits._
import doobie.Fragment.const

object Queries {
  implicit def stringToConst(str: String) = const(str)

  def selectCountryByName(pattern: String, limit: Long): Query0[Country] = sql"""
    SELECT code, name, continent, surfacearea, indepyear
    FROM country
    WHERE name ILIKE $pattern
    LIMIT $limit
  """.query[Country]

  def selectCitiesByCountry(countryCode: Code, limit: Long): Query0[City] = (s"""
    SELECT id, city.name AS city_name, code, country.name AS country_name, continent, surfacearea, indepyear
    FROM city JOIN country ON city.countrycode = country.code
    WHERE country.${countryCode.column} = """ ++ fr"""${countryCode.code}
    LIMIT $limit
  """).query[City]
}
