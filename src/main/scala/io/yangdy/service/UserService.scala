package io.yangdy.service

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession
import io.yangdy.model._
import io.yangdy.util.StringUtils._


trait UserService {

  def createUserTable() {
    val ddl = Users.ddl
    ddl.create
    Users insert User(None, "test1", "test1@test.com", "N/A", "N/A", None, currentDate, currentDate, None)
    Users insert User(None, "test2", "test2@test.com", "N/A", "N/A", None, currentDate, currentDate, None)
    Users insert User(None, "test3", "test3@test.com", "N/A", "N/A", None, currentDate, currentDate, None)
    Users insert User(None, "test4", "test4@test.com", "N/A", "N/A", None, currentDate, currentDate, None)
  }

  def createUser(username: String, email: String, password: String, avatar_url: Option[String]): Option[User] = {
    val salt = randomString(8)
    val user_id = Users returning Users.id insert User(
      id = None,
      name = username,
      email = email,
      password_hash = md5(password + ":" + salt),
      password_salt = salt,
      avatar_url = avatar_url,
      created_at = currentDate,
      updated_at = currentDate,
      last_login = None
    )

    getUserById(user_id)
  }

  def getUserById(user_id: Long): Option[User] = {
    Query(Users) filter(_.id is user_id.bind) firstOption
  }

  def getAllUsers(): List[User] = {
    Query(Users) sortBy(_.id) list
  }
}

object UserService extends UserService
