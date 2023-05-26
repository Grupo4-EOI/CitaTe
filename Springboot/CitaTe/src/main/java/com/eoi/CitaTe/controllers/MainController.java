package com.eoi.CitaTe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


   ///Esto es un follon porque tiene el retun tiene que ir al modal de login hay que hacerlo con fragments seguramente//
/*    @GetMapping("/login")
    public String login(Model model)
    {        return "login";    }*/

    @GetMapping("/")
    public String home(){
        return "Home.html";
    }

    @GetMapping("/citaTeP1")
    public String citaTeP1() {
        return "CitaTeP1.html";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "Perfil.html";
    }

    @GetMapping("/registroEmpresa1")
    public String regEmp1() {
        return "RegistroEmpresa1.html";
    }

    @GetMapping("/registroEmpresa2")
    public String regEmp2() {
        return "RegistroEmpresa2.html";
    }

    @GetMapping("/registroEmpresa3")
    public String regEmp3(){
        return "RegistroEmpresa3.html";
    }

    @GetMapping("/registroEmpresa4")
    public String regEmp4(){
        return "RegistroEmpresa4.html";
    }

    @GetMapping("/registroEmpresa5")
    public String regEmp5(){
        return "RegistroEmpresa5.html";
    }

    @GetMapping("/registroEmpresa6")
    public String regEmp6(){
        return "RegistroEmpresa6.html";
    }

    @GetMapping("/registroEmpresa7")
    public String regEmp7(){
        return "RegistroEmpresa7.html";
    }

    @GetMapping("/registroEmpresa8")
    public String regEmp8(){
        return "RegistroEmpresa8.html";
    }

    @GetMapping("/registroEmpresa9")
    public String regEmp9(){
        return "RegistroEmpresa9.html";
    }
    @GetMapping("/registroEmpresa10")
    public String regEmp10(){
        return "RegistroEmpresa10.html";
    }
    @GetMapping("/registroEmpresa11")
    public String regEmp11(){
        return "RegistroEmpresa11.html";
    }
    @GetMapping("/registroEmpresa12")
    public String regEmp12(){
        return "RegistroEmpresa12.html";
    }
    @GetMapping("/homepostregistro")
    public String homepregistro(){
        return "HomePostRegistro.html";
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }


}
