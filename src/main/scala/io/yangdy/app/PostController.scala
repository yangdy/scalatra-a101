package io.yangdy.app

import org.scalatra._
import scalate.ScalateSupport

import io.yangdy.services.PostService

class PostController extends MyScalatraServletStack {
  
  before() {
    contentType = "text/html"
  }

  get("/") {
    jade("posts/index", "posts" -> PostService.all)
  }

}