package com.example.demo.todo;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;
import javax.servlet.ServletException;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@RestController
class TodoHandler {

  private TodoRepository todoRepository;

  public TodoHandler(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  ServerResponse createTodo(ServerRequest request) throws ServletException, IOException {
    var todo = request.body(Todo.class);
    return ok().body(todoRepository.save(todo));
  }

  ServerResponse getTodoById(ServerRequest request) {
    String id = request.pathVariable("id");
    return todoRepository.findById(Long.parseLong(id))
        .map(todo -> ok().body(todo))
        .orElse(ServerResponse.notFound().build());
  }

  ServerResponse getTodos(ServerRequest request) {
    return ok().body(todoRepository.findAll());
  }


  ServerResponse addItem(ServerRequest request) {
    return ok().build();
  }

  ServerResponse updateItem(ServerRequest request) {
    return ok().build();
  }

  @Bean
  RouterFunction<ServerResponse> routes(TodoHandler handler) {
    return route()
        .path("/todos", b -> b
            .GET("/{id}", handler::getTodoById)
            .GET("", handler::getTodos)
            .POST("", handler::createTodo)
            .POST("/items", handler::addItem)
            .POST("/items/{id}", handler::updateItem)
        ).build();
  }

}


