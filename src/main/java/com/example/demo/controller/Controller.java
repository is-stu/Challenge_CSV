package com.example.demo.controller;

import com.example.demo.Player;
import com.example.demo.inter.PlayerReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private PlayerReactiveRepository repository;

    @GetMapping("/players")
    public String players(Model model){
        Flux<Player> flux = repository.findAll(); // recuperamos todos los registros de forma reactiva
        model.addAttribute("players", flux);
        return "players"; // direccionamos al students.html
    }

    @GetMapping("/players-reactive")
    public String playersReactive(Model model)
    {
        Flux<Player> userFlux = repository.findAll().delayElements(Duration.ofSeconds(1));
        model.addAttribute("players", new ReactiveDataDriverContextVariable(userFlux, 1));
        return "players";
    }
}
