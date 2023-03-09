package com.codersee.kotlindsl.handler

import com.codersee.kotlindsl.model.ErrorResponse
import com.codersee.kotlindsl.model.UserDTO
import com.codersee.kotlindsl.model.User
import com.codersee.kotlindsl.repository.UserRepository
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

class UserHandler(
  private val userRepository: UserRepository
) {

  fun createUser(
    request: ServerRequest
  ): ServerResponse {
    val userRequest = request.body(UserDTO::class.java)

    val createdUserResponse = userRepository.create(
      user = userRequest.toModel()
    )
      .toDTO()

    return ServerResponse.ok()
      .body(createdUserResponse)
  }

  fun findAllUsers(
    request: ServerRequest
  ): ServerResponse {
    val usersResponses = userRepository.findAll()
      .map(User::toDTO)

    return ServerResponse.ok()
      .body(usersResponses)
  }

  fun findUserById(
    request: ServerRequest
  ): ServerResponse {
    val id = request.pathVariable("id")
      .toLongOrNull()
      ?: return badRequestResponse("Invalid id")

    val userResponse = userRepository.findById(id)
      ?.toDTO()

    return userResponse
      ?.let { response ->
        ServerResponse.ok()
          .body(response)
      }
      ?: notFoundResponse(id)
  }

  fun updateUserById(
    request: ServerRequest
  ): ServerResponse {
    val id = request.pathVariable("id")
      .toLongOrNull()
      ?: return badRequestResponse("Invalid id")

    val userRequest = request.body(UserDTO::class.java)

    val updatedUser = userRepository.updateById(
      id = id,
      user = userRequest.toModel()
    )

    return updatedUser
      ?.let { response ->
        ServerResponse.ok()
          .body(response)
      }
      ?: notFoundResponse(id)
  }

  fun deleteUserById(
    request: ServerRequest
  ): ServerResponse {
    val id = request.pathVariable("id")
      .toLongOrNull()
      ?: return badRequestResponse("Invalid id")

    userRepository.deleteById(id)

    return ServerResponse.noContent()
      .build()
  }

  private fun badRequestResponse(reason: String): ServerResponse =
    ServerResponse.badRequest()
      .body(
        ErrorResponse(reason)
      )

  private fun notFoundResponse(id: Long): ServerResponse =
    ServerResponse.badRequest()
      .body(
        ErrorResponse("User with id: $id was not found.")
      )

}

private fun UserDTO.toModel(): User =
  User(
    email = this.email,
    name = this.name,
    age = this.age
  )

private fun User.toDTO(): UserDTO =
  UserDTO(
    email = this.email,
    name = this.name,
    age = this.age
  )