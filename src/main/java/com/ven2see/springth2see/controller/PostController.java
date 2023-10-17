package com.ven2see.springth2see.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ven2see.springth2see.model.Favorite;
import com.ven2see.springth2see.model.Post;
import com.ven2see.springth2see.model.User;
import com.ven2see.springth2see.repository.LoginRepository;
import com.ven2see.springth2see.service.FavoriteService;
import com.ven2see.springth2see.service.PostService;

@Controller
public class PostController {

    @Autowired
    private LoginRepository userRepository;

    @Autowired
    private PostService pservice;

    @Autowired
    private FavoriteService fService;

    public User userAuth() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        String username = userDetails.getUsername();
        System.out.println(username);
        Optional<User> opUser = userRepository.findByUsername(username);
        User user = opUser.get();
        return user;
    }

    @PostMapping("/post-create")
    public String insertPost(@ModelAttribute("post") Post Newpost) throws IOException {
        String ubicacionImagenes = "src//main//resources//static//imagenes//";
        MultipartFile image = Newpost.getFile();

        if (!image.isEmpty()) {
            try {
                // Leer la imagen como BufferedImage
                BufferedImage originalImage = ImageIO.read(image.getInputStream());

                // Comprimir la imagen en formato JPEG con una calidad específica (ajusta la
                // calidad según tus necesidades)
                ByteArrayOutputStream compressedOutputStream = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "jpeg", compressedOutputStream);

                // Obtener los bytes de la imagen comprimida
                byte[] compressedBytes = compressedOutputStream.toByteArray();

                // Nombre del archivo
                String nombreArchivo = image.getOriginalFilename();

                // Ruta donde se guardará la imagen comprimida
                Path rutaImagen = Paths.get(ubicacionImagenes + nombreArchivo);

                // Guardar la imagen comprimida
                Files.write(rutaImagen, compressedBytes);

                // Establecer el nombre del archivo comprimido en el objeto Post
                Newpost.setImage(nombreArchivo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        pservice.insertPost(Newpost);
        System.out.println(Newpost.getImage());
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, Model model, Principal prin) {
        Optional<Post> postOptional = pservice.findPostById(id);
        if (prin != null) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            model.addAttribute("post", post);
            return "post";
        } else {
            // Manejo de la situación en la que no se encuentra el post
            return "error"; // Puedes redirigir a una página de error o tomar alguna otra acción apropiada.
        }
    }

    @GetMapping("/favorite/{id}")
    public String saveFavorite(@PathVariable int id, Principal prin, Favorite fav, Model model) {
        User user = userAuth();

        fav.setUser_id(user.getId());
        fav.setPost_id(id);
        fService.insertFav(fav);

        return "redirect:/";
    }

}
