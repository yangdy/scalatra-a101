package io.yangdy.app

import javax.servlet._
import org.slf4j.LoggerFactory
import javax.servlet.http.HttpServletRequest
import scala.slick.driver.H2Driver.simple._

class TransactionFilter(db: Database) extends Filter {

  private lazy val logger = LoggerFactory.getLogger(classOf[TransactionFilter])

  def init(config: FilterConfig) = {}

  def destroy(): Unit = {}

  def doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain): Unit = {
    if(req.asInstanceOf[HttpServletRequest].getRequestURI().startsWith("/assets/")) {
      chain.doFilter(req, res)
    } else {
      db withTransaction {
        logger.info("begin transacion")
        chain.doFilter(req, res)
        logger.info("end transacion")
      }
    }
  }

}