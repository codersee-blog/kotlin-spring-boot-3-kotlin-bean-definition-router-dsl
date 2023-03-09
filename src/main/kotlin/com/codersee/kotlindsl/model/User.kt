package com.codersee.kotlindsl.model

data class User(
  val id: Long? = null,
  val email: String,
  val name: String,
  val age: Int
)