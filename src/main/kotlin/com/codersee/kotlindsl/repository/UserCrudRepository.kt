package com.codersee.kotlindsl.repository

import com.codersee.kotlindsl.model.User
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class UserCrudRepository(
  private val dataSource: MutableMap<Long, User>
) : UserRepository {

  override fun create(user: User): User {
    val lastId = this.dataSource.keys.max()
    val incrementedId = lastId + 1
    val updatedUser = user.copy(id = incrementedId)

    this.dataSource[incrementedId] = updatedUser

    return updatedUser
  }

  override fun findAll(): List<User> =
    this.dataSource.values
      .toList()

  override fun findById(id: Long): User? =
    this.dataSource[id]

  override fun updateById(id: Long, user: User): User? =
    this.dataSource[id]
      ?.let {
        this.dataSource[id] = user
        user
      }

  override fun deleteById(id: Long) {
    this.dataSource.remove(id)
  }
}