package io.yangdy.util

import scala.util.Random

object StringUtils {

  def md5(value: String): String = {
    val md = java.security.MessageDigest.getInstance("MD5")
    md.update(value.getBytes)
    md.digest.map(b => "%02x".format(b)).mkString
  }

  private val alphaNumberic = "0123456789abcdefghijklmnopqrstuvwxyz"

  def randomString(len: Int): String = {
    val s = new StringBuilder()
    for (i <- 0 to len) {
      s.append(alphaNumberic.charAt(Random.nextInt(alphaNumberic.length)))
    }
    s toString
  }

}
