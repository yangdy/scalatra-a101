import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.LoggerFactory
import scala.slick.session.Database
import org.scalatra._
import javax.servlet.ServletContext

import io.yangdy.app._

class ScalatraBootstrap extends LifeCycle {

  lazy val logger = LoggerFactory.getLogger(getClass)

  val cpds = new ComboPooledDataSource
  logger.info("c3p0 connection pool created")

  override def init(context: ServletContext) {
    val db = Database.forDataSource(cpds)
    context.mount(new MyScalatraServlet, "/*")
    context.mount(new PostController, "/posts/*")
  }

  private def closeDbConnection() {
    cpds.close
    logger.info("c3p0 connection pool closed")
  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
    closeDbConnection
  }

}