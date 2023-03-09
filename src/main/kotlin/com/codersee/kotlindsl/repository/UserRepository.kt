package com.codersee.kotlindsl.repository

import com.codersee.kotlindsl.model.User

interface UserRepository {
  fun create(user: User): User
  fun findAll(): List<User>
  fun findById(id: Long): User?
  fun updateById(id: Long, user: User): User?
  fun deleteById(id: Long)
}