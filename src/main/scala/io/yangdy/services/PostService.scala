package io.yangdy.services

import io.yangdy.models.Post

object PostService {
  val all = List(
    Post("Post 1", "This is a testing post."),
    Post("Post 2", "Another testing post.")
  )
}
