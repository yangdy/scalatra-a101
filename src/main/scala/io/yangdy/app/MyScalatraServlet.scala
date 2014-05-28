package io.yangdy.app

import org.scalatra._
import scalate.ScalateSupport

class MyScalatraServlet extends MyScalatraServletStack {

  before() {
    contentType="text/html"
  }

  get("/") {
    jade("hello-scalate")
  }

}
