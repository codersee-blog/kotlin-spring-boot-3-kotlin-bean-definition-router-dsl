package com.codersee.kotlindsl.config

import com.codersee.kotlindsl.model.User

object DataSource {

  val devDataSource: MutableMap<Long, User> = mutableMapOf(
    1L to User(1L, "email-1@gmail.com", "Name 1", 22),
    2L to User(2L, "email-2@gmail.com", "Name 2", 43),
    3L to User(3L, "email-3@gmail.com", "Name 3", 26),
    4L to User(4L, "email-4@gmail.com", "Name 4", 50)
  )

  val prodDataSource: MutableMap<Long, User> = mutableMapOf(
    1L to User(1L, "prod-email-1@gmail.com", "Name 1", 22),
    2L to User(2L, "prod-email-2@gmail.com", "Name 2", 43),
    3L to User(3L, "prod-email-3@gmail.com", "Name 3", 26),
    4L to User(4L, "prod-email-4@gmail.com", "Name 4", 50)
  )

}