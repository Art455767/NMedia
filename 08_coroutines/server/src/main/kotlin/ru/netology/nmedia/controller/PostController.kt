package ru.netology.nmedia.controller

import org.springframework.web.bind.annotation.*
import ru.netology.nmedia.dto.Comment
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.service.CommentService
import ru.netology.nmedia.service.PostService

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService,
    private val commentService: CommentService
) {

    @GetMapping
    fun getAllPosts(): List<Post> {
        return postService.getAllWithAuthors()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Post {
        return postService.getById(id)
    }

    @PostMapping
    fun save(@RequestBody dto: Post): Post {
        return postService.save(dto)
    }

    @DeleteMapping("/{id}")
    fun removeById(@PathVariable id: Long) {
        postService.removeById(id)
    }

    @PostMapping("/{id}/likes")
    fun likeById(@PathVariable id: Long): Post {
        return postService.likeById(id)
    }

    @DeleteMapping("/{id}/likes")
    fun unlikeById(@PathVariable id: Long): Post {
        return postService.unlikeById(id)
    }

    @GetMapping("/{id}/comments")
    fun getCommentsByPostId(@PathVariable id: Long): List<Comment> {
        return commentService.getAllByPostIdWithAuthors(id)
    }
}