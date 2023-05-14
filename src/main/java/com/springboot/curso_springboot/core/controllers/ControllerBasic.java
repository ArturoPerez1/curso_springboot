package com.springboot.curso_springboot.core.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.curso_springboot.core.configuration.Pages;
import com.springboot.curso_springboot.core.model.Post;

@Controller
@RequestMapping("/home") /* Esto es un endpoint que se utiliza para hacer un estilo de wrapper */
public class ControllerBasic {

    /*
     * @GetMapping hace una petición para traer a la vista el contenido de la url
     * especificada en el path
     */
    /*
     * En este caso la URL = "saludar/" saludar siendo el método que retorna a index
     */
    @GetMapping(path = { "/posts", "/" })
    public String saludar(Model model) {
        /* con el model mandamos data del modelo a la vista por medio del controller */
        model.addAttribute("posts", getPosts());
        return "index";
    }

    public List<Post> getPosts() {
        ArrayList<Post> post = new ArrayList<>();
        post.add(new Post(1,
                "(Fryderyk Franciszek Chopin; Zelazowa Wola, actual Polonia, 1810 - París, 1849) Compositor y pianista polaco.",
                "http://localhost:8081/img/chopinImage.jpg", "Chopin", new Date()));
        post.add(new Post(2,
                "(Fryderyk Franciszek Chopin; Zelazowa Wola, actual Polonia, 1810 - París, 1849) Compositor y pianista polaco.",
                "http://localhost:8081/img/chopinImage.jpg", "Chopin", new Date()));
        post.add(new Post(3,
                "(Fryderyk Franciszek Chopin; Zelazowa Wola, actual Polonia, 1810 - París, 1849) Compositor y pianista polaco.",
                "http://localhost:8081/img/chopinImage.jpg", "Chopin", new Date()));

        return post;
    }

    /* Aquí hacemos el filtrado para mandar un solo post a la vista post */
    /* Además hacemos uso de los parámetros de petición */
    @GetMapping(path = { "/post" })
    public ModelAndView getPostIndividual(@RequestParam(defaultValue = "1", name = "id", required = false) int id) {
        ModelAndView modelAndView = new ModelAndView(Pages.Post);
        Post postFiltrado = this.getPosts().stream().filter(p -> {
            return p.getId() == id;
        }).findFirst().get();
        modelAndView.addObject("post", postFiltrado);

        return modelAndView;
    }

    /*
     * Esta es una manera mas eficiente de mandar data a la vista, además una forma
     * de separar las
     * vistas usando el archivo pages
     */
    @GetMapping(path = "/public")
    public ModelAndView post() {
        /*
         * con el ModelAndView mandamos data del modelo a la vista por medio del
         * controller
         */
        ModelAndView modelAndView = new ModelAndView(Pages.Home);
        modelAndView.addObject("posts", getPosts());
        return modelAndView;
    }
}
