package study.mf.AuthAndRolesWithJwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static study.mf.AuthAndRolesWithJwt.fakeData.FakeGames.games;

@RestController
@RequestMapping("/games")
public class GameFakeController {

    @GetMapping
    public ResponseEntity<List<String>> getAll(){
        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<List<String>> addGame(@RequestBody String title){
        games.add(title);
        return ResponseEntity.status(HttpStatus.CREATED).body(games);
    }
}
