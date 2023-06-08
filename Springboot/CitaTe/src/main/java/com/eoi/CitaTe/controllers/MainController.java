package com.eoi.CitaTe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


   ///Esto es un follon porque tiene el retun tiene que ir al modal de login hay que hacerlo con fragments seguramente//
    @GetMapping("/login")
    public String login(Model model)
    {        return "login.html";    }
    @GetMapping("/logout")
    public String logout(Model model)
    {        return "/citaTeP1";    }

    @GetMapping("/")
    public String home(){
        return "home/Home.html";
    }

    @GetMapping("/citaTeP1")
    public String citaTeP1() {
        return "perfil/CitaTeP1.html";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "perfil/Perfil.html";
    }



}
