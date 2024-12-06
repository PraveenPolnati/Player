package com.example.player.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.player.model.Player;
import com.example.player.service.PlayerService;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    
    @GetMapping("/players")
    public ArrayList<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/player/{playerId}")
    public Player getPlayerById(@PathVariable int playerId) {
        return playerService.getPlayerById(playerId);
    }

    @PostMapping("/player")
    public Player addPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }

    @PutMapping("/player/{playerId}")
    public Player updatePlayer(@PathVariable int playerId, @RequestBody Player player) {
        return playerService.updatePlayer(playerId, player);
    }
    @DeleteMapping("/deletePlayer/{playerId}")
    public String deletePlayer(@PathVariable int playerId) {
        return playerService.deletePlayer(playerId);
    }
}
