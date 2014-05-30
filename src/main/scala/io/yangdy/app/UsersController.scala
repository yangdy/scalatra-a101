package io.yangdy.app

import io.yangdy.service._

class UsersController extends MyScalatraServletStack with UserService {

  before() {
    contentType = "text/html"
  }

  get("/") {
    jade("users/index", "users" -> getAllUsers)
  }

  post("/") {
    createUser(params("username"), params("email"), params("password"), None) match {
      case Some(user) => jade("users/show", "user" -> user)
      case None => redirect("/new")
    }
  }

  get("/:id") {
    try {
      getUserById(params("id").toLong) match {
        case Some(user) => jade("users/show", "user" -> user)
        case _ => NotFound
      }
    } catch {
      case e: java.lang.NumberFormatException => NotFound
      case e: Throwable => NotFound
    }
  }

}
