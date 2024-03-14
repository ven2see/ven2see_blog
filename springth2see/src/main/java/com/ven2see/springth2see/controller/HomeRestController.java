package com.ven2see.springth2see.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ven2see.springth2see.model.Category;
import com.ven2see.springth2see.model.ModelDto.MarketplaceDto;
import com.ven2see.springth2see.model.ModelDto.PostDto;
import com.ven2see.springth2see.model.ModelDto.UserDto;
import com.ven2see.springth2see.service.IUserService;
import com.ven2see.springth2see.service.PostService;
import com.ven2see.springth2see.service.MPlaceService;
import com.ven2see.springth2see.service.categoService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HomeRestController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private PostService pservice;

    @Autowired
    private categoService cService;
    

    @Autowired
    private MPlaceService sService;

    @GetMapping("/public/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> result = pservice.getAllPost();
        
        return ResponseEntity.ok(result);
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getCategos() {
        var result = cService.getAllCatego();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/public/products")
    public ResponseEntity<List<MarketplaceDto>> getProducts(){
        var products = sService.getAllProduct();

        return new ResponseEntity<>(products ,HttpStatus.OK);
    }
    
    @GetMapping("/public/post/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDto postDto = pservice.findPostById(id);
        if (postDto!=null) {
            return ResponseEntity.ok(postDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/single-product/{id}")
    public ResponseEntity<MarketplaceDto> getProductById(@PathVariable Integer id){
        
        if(id!=null){
            MarketplaceDto prod = sService.getProductById(id);
            return ResponseEntity.ok(prod);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping("/user/post-create")
    // public ResponseEntity<Map<String, String>> createPost(@RequestParam("post") String strPost,
    //         @RequestParam("fichero") MultipartFile multipartFile) throws IOException {
    //     try {
    //         String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    //         String uploadDir = "images";
    //         // Establecemos el directorio donde se subiran nuestros ficheros
    //         Gson gson = new GsonBuilder().setPrettyPrinting()
    //                 .excludeFieldsWithoutExposeAnnotation()
    //                 .create();

    //         Post post = gson.fromJson(strPost, Post.class);

    //         post.setImage(fileName);
    //         pservice.insertPost(post);
    //         FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    //         Map<String, String> response = new HashMap<>();
    //         response.put("message", "post");
    //         return ResponseEntity.ok(response);
    //     } catch (Exception e) {
    //         Map<String, String> errorResponse = new HashMap<>();
    //         errorResponse.put("error", e.getMessage());
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    //     }
    // }

   
 

    @GetMapping("/public/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        try {
            UserDto user = iUserService.userById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/username/{username}")
    public ResponseEntity<UserDto> getUserbyUsername(@PathVariable String username) {
        try {
            UserDto user = iUserService.findbyUsername(username);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/app-control/users")
    public List<UserDto> getAllUsers() {
        return iUserService.findAll();
    }

    // @PostMapping("/admin/users/save")
    // public ResponseEntity<String> saveUser(@ModelAttribute("user") User user) {
    //     iUserService.save(user);
    //     return ResponseEntity.ok("Usuario guardado exitosamente");
    // }

    // @PostMapping("/signup")
    // public ResponseEntity<Map<String, String>> signUp(@RequestBody User user) {
    //     if (iUserService.getByUsername(user.getUsername())!=null) {
    //         // El usuario ya existe, devuelve un código de estado 409 y un mensaje
    //         Map<String, String> response = new HashMap<>();
    //         response.put("error", "El usuario ya existe");
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    //     }

    //     // El usuario no existe, guárdalo y devuelve un código de estado 201 (Created)
    //     iUserService.save(user);

    //     Map<String, String> response = new HashMap<>();
    //     response.put("resp", "Usuario creado exitosamente");
    //     return ResponseEntity.status(HttpStatus.CREATED).body(response);
    // }

    // @PostMapping("/update")
    // public ResponseEntity<String> updateUser(@ModelAttribute User user) {
    //     Boolean result = iUserService.save(user);
    //     if (result) {
    //         return ResponseEntity.ok("Usuario actualizado exitosamente");
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @DeleteMapping("/admin/users/{id}")
    // public ResponseEntity<String> deleteUser(@PathVariable int id) {
    //     Boolean result = iUserService.deleteById(id);
    //     if (result) {
    //         return ResponseEntity.ok("Usuario eliminado exitosamente");
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

}
