package com.example.keycloakdemo.controller;

import com.example.keycloakdemo.domain.Song;
import com.example.keycloakdemo.constant.RoleNames;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/test")
public class SongController {
    private final List<Song> playList = new CopyOnWriteArrayList<>(List.of(
            new Song("Space Song", "Beach House"),
            new Song("Dangerous", "David Guetta")));

    @Secured(RoleNames.USER)
    @GetMapping("/list")
    public List<Song> getSongs() {
        var songs = new ArrayList<>(playList);
        Collections.shuffle(songs);
        return songs;
    }

    @Secured(RoleNames.ADMIN)
    @PostMapping
    public Song addSong(@RequestBody Song newSong) {
        playList.add(newSong);
        return newSong;
    }

}
