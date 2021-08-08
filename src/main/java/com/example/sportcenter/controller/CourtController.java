package com.example.sportcenter.controller;

import com.example.sportcenter.entity.Court;
import com.example.sportcenter.exception.ResourceNotFoundException;
import com.example.sportcenter.repository.CourtRepository;
import com.example.sportcenter.repository.SportCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourtController {
    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private SportCenterRepository sportCenterRepository;

    public CourtController(CourtRepository courtRepository, SportCenterRepository sportCenterRepository) {
        this.courtRepository = courtRepository;
        this.sportCenterRepository = sportCenterRepository;
    }

    @GetMapping("/court/details/{id}")
    public Court getCourt(@PathVariable Integer id) {
        if(courtRepository.findById(id).isPresent())
        return courtRepository.findById(id).get();
        else return  null;
    }

    @GetMapping("/court/all")
    public List<Court> getCourt() {
        return courtRepository.findAll();
    }

    //it's to add players to the court
    @PutMapping("/sportcenters/{centerid}/courts/{courtid}/playerAdd/{playerAdd}")
    public Court updateComment(@PathVariable (value = "centerid") Integer centerid,
                               @PathVariable (value = "courtid") Integer courtid,
                               @PathVariable (value = "playerAdd") Integer playerAdd) {
        if(!sportCenterRepository.existsById(centerid)) {
            throw new ResourceNotFoundException("Centerid " + centerid + " not found");
        }

        return courtRepository.findById(courtid).map(court -> {
            if(court.getPlayers()+playerAdd<=4){
                if(court.getPlayers()+playerAdd==4)
                {
                    System.out.println("thank you.court"+courtid+" is fully booked now");
                }
                court.setPlayers(court.getPlayers()+playerAdd);
                return courtRepository.save(court);
            }
            else{
                System.out.println("###Sorry, the selected court currently has " + court.getPlayers() +" players registered already, " +
                        "so we can't accommodate"+ playerAdd+ " more players, please choose another court");
                return courtRepository.save(court);
            }
        }).orElseThrow(() ->
        new ResourceNotFoundException("Courtid " + courtid + "not found"));
    }
}
