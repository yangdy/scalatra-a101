package io.yangdy.app

import org.scalatra._
import scalate.ScalateSupport

import io.yangdy.models.Post
import io.yangdy.services.PostService

class PostController extends MyScalatraServletStack {
  
  before() {
    contentType = "text/html"
  }

  get("/") {
    jade("posts/index", "posts" -> PostService.all)
  }

  get("/new") {
    jade("posts/new")
  }

  post("/") {
    val post = Post(params("title"), params("content"))
    jade("posts/show", "post" -> post)
  }

}