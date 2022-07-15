package in.purabtech.jpaentitymanager.controller;

import in.purabtech.jpaentitymanager.model.Tutorial;
import in.purabtech.jpaentitymanager.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public List<Tutorial> getAllTutorials() {
        List<Tutorial> tutorials = new ArrayList<>();
        tutorials = tutorialRepository.findAll();
        return tutorials;
    }

    @GetMapping("/tutorial/{tutorialId}")
    public Tutorial getTutorialDetails(@PathVariable Long tutorialId) {
        return tutorialRepository.findById(tutorialId);
    }

    @PostMapping("/addTutorial")
    public Tutorial addTutorial(@RequestBody Tutorial tutorial) {

        return tutorialRepository.save(tutorial);
    }

    @PutMapping("/updateTutorial/{tutorialId}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable Long tutorialId, @RequestBody Tutorial tutorial) {
        Tutorial tutorial1  = tutorialRepository.findById(tutorialId);
            if(tutorial1 != null) {
                tutorial = tutorialRepository.update(tutorial);
                return new ResponseEntity<Tutorial>(tutorial, HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

    @DeleteMapping("/deleteTutorial/{tutorialId}")
    public Tutorial deleteTutorial(@PathVariable Long tutorialId) {
        return tutorialRepository.deleteById(tutorialId);
    }
}