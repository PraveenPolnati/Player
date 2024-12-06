package com.example.player.service;

import java.util.*;

import com.example.player.model.Player;
import com.example.player.model.PlayerRowMapper;
import com.example.player.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service

public class PlayerService implements PlayerRepository{

    @Autowired
    private JdbcTemplate db;


    @Override
    public ArrayList<Player> getAllPlayers() {
        List<Player> playersList = db.query("select * from team",new PlayerRowMapper());
        return new ArrayList<>(playersList);
    }

    @Override
    public Player getPlayerById(int playerId) {
        Player player = db.queryForObject("select * from team where playerId = ?", new PlayerRowMapper(), playerId);
        if(player != null){
            return player;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Player addPlayer(Player player) {
        db.update("insert into team (playerName,jerseyNumber,role) values (?,?,?)",player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        Player savedPlayer = db.queryForObject("select * from team where playerName=? and jerseyNumber=? and role=?",new PlayerRowMapper(),player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        return savedPlayer;
    }

    @Override
    public Player updatePlayer(int playerId,Player player) {
        try{
            db.queryForObject("select * from team where playerId = ?", new PlayerRowMapper(), playerId);
            if(player.getPlayerName() != null){
                db.update("update team set playerName = ? where playerId = ?",player.getPlayerName(),playerId);
            }
            if(player.getRole() != null){
                db.update("update team set role = ? where playerId = ?",player.getRole(),playerId);
            }
            return getPlayerById(playerId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String deletePlayer(int playerId) {
        try{
            db.queryForObject("select * from team where playerId = ?", new PlayerRowMapper(), playerId);
            db.update("delete from team where playerId = ?",playerId);
            return "delete completed";
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    
}
