package ru.netology.nmedia.controller

import org.springframework.web.bind.annotation.*
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.service.PostService

@RestController
@RequestMapping("/api/posts", "/api/slow/posts")
class PostController(private val service: PostService) {

    @GetMapping
    fun getAll(): List<Post> {
        simulateServerError() // Имитация ошибки
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Post {
        simulateServerError() // Имитация ошибки
        return service.getById(id)
    }

    @PostMapping
    fun save(@RequestBody dto: Post) = service.save(dto)

    @DeleteMapping("/{id}")
    fun removeById(@PathVariable id: Long) = service.removeById(id)

    @PostMapping("/{id}/likes")
    fun likeById(@PathVariable id: Long) = service.likeById(id)

    @DeleteMapping("/{id}/likes")
    fun unlikeById(@PathVariable id: Long) = service.unlikeById(id)
}

private fun simulateServerError() {
    if (Math.random() < 0.5) { // 50% вероятность ошибки
        throw RuntimeException("Серверная ошибка")
    }
}
