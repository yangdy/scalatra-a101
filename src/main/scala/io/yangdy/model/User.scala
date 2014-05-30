package io.yangdy.model

import scala.slick.driver.H2Driver.simple._

object Users extends Table[User]("USER") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def email = column[String]("EMAIL")
  def password_hash = column[String]("PASSWORD_HASH")
  def password_salt = column[String]("PASSWORD_SALT")
  def avatar_url = column[String]("AVATAR_URL", O.Nullable)
  def created_at = column[java.util.Date]("CREATED_AT")
  def updated_at = column[java.util.Date]("UPDATED_AT")
  def last_login = column[java.util.Date]("LAST_LOGIN", O.Nullable)

  def * = id.? ~ name ~ email ~ password_hash ~ password_salt ~ avatar_url.? ~ created_at ~ updated_at ~ last_login.? <> (User, User.unapply _)
}

case class User(
  id: Option[Long],
  name: String,
  email: String,
  password_hash: String,
  password_salt: String,
  avatar_url: Option[String],
  created_at: java.util.Date,
  updated_at: java.util.Date,
  last_login: Option[java.util.Date]
)
