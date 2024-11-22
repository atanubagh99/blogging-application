package com.example.blogging_application.controller;

import com.example.blogging_application.bean.Post;
import com.example.blogging_application.service.PostService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogging")
@OpenAPIDefinition(
        info = @Info(
                title = "Blogging Application",
                description = "It's a simple Blogging Application",
                summary = "It has create, read, update & delete operation",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "Atanu Kumar Bagh",
                        email = "atanubagh99@gmail.com"
                ),
                license = @License(
                        name = "https://github.com/atanubagh99/"
                ),
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "Development",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production",
                        url = "http://localhost:8080"
                )
        },
        security = @SecurityRequirement(
                name = "auth"
        )
)
@SecurityScheme(
        name = "auth",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "security description"
)

public class PostController {
    @Autowired
    private PostService postService;

    @Operation(
            tags = "CREATE POST",
            description = "Let's Create A New Post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Data Not Found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500"
                    )
            }
    )
    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody Post post){
        Post response = postService.createPost(post);

        return new ResponseEntity<>("Post created successfully. Id -> "+response.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/getPost/{id}")
    public Optional<Post> getPostById(@PathVariable Long id){
       // Post response = postService.getPostById(id);
       // return ResponseEntity.ok(response);
       return postService.getPostById(id);
       // return new ResponseEntity<>(" ID not found", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getAllPost")
    public List<Post> getAllPost(){
        return postService.getAllPost();
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable Long id){
        postService.updatePostById(post, id);
        return new ResponseEntity<>("Id no -> "+ post.getId()+" updated Successfully" , HttpStatus.OK);
    }
}