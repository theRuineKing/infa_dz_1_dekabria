package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ApiController {
    private final List<String> messages = new ArrayList<>();

    /* @GetMapping("getall")
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok(messages);
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<String> getMessageById(@PathVariable int id) {
        return ResponseEntity.ok(messages.get(id));
    }
    @GetMapping("deleteById/{id}")
    public void deleteMessageById(@PathVariable int id) {
        messages.remove(id);
    }
    @PostMapping("createMessage")
    public void createMessage(@RequestBody String myText) {
        messages.add(myText);
    }
    @PutMapping("update/{id}")
    public void updateMessageById(@PathVariable int id, @RequestBody String text) {
        messages.remove(id);
        messages.add(id, text);
    } */

    // curl --location 'localhost:8081/messages'
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }

    // curl --location 'localhost:8081/messages' \
    //--header 'Content-Type: text/plain' \
    //--data 'aboba'
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl --location 'localhost:8081/messages/0'
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }

    //curl --location --request DELETE 'localhost:8081/messages/0'
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl --location --request PUT 'localhost:8081/messages/0' \
    //--header 'Content-Type: text/plain' \
    //--data 'abobus'
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //curl --location 'localhost:8081/messages/search/EASPORTS'
    @GetMapping("/messages/search/{text}")
    public ResponseEntity<Integer> getTextIndex(@PathVariable String text) {
        int foundedIndex = 0;
        for (String s : messages) {
            if (s.equals(text)) {
                return ResponseEntity.ok(foundedIndex);
            }
            foundedIndex++;
        }
        return ResponseEntity.ok(-1);
    }

    //curl --location 'localhost:8081/messages/count'
    @GetMapping("/messages/count")
    public ResponseEntity<Integer> howManyMessages() {
        return ResponseEntity.ok(messages.size());
    }

    //curl --location 'localhost:8081/messages/1/create' \
    //--header 'Content-Type: application/json' \
    //--data 'EASPORTS'
    @PostMapping("/messages/{index}/create")
    public ResponseEntity<Void> createMessage(@PathVariable int index, @RequestBody String text) {
        messages.add(index, text);
        return ResponseEntity.noContent().build();
    }

    //curl --location --request DELETE 'localhost:8081/messages/search/EASPORTS'
    @DeleteMapping("/messages/search/{text}")
    public ResponseEntity<Void> deleteSubstring(@PathVariable String text) {
        int i = 0;
        for (String s : messages) {
            if (s.contains(text)) {
                messages.remove(i);
            }
        }
        return ResponseEntity.noContent().build();
    }
}