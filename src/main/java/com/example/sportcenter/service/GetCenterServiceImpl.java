package com.example.sportcenter.service;

import com.example.sportcenter.entity.Court;
import com.example.sportcenter.entity.SportCenter;
import com.example.sportcenter.repository.CourtRepository;
import com.example.sportcenter.repository.SportCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service("getCourtServiceImpl")
public class GetCenterServiceImpl {

    @Autowired
    CourtRepository courtRepository;

    @Autowired
    SportCenterRepository sportCenterRepository;

    public GetCenterServiceImpl() {
    }

    public GetCenterServiceImpl(CourtRepository courtRepository, SportCenterRepository sportCenterRepository) {
        this.courtRepository = courtRepository;
        this.sportCenterRepository = sportCenterRepository;
    }

    @Transactional
    public ResponseEntity<Object> addCenter(SportCenter sportCenter) {

        SportCenter newSportCenter=new SportCenter();
        newSportCenter.setCourts(sportCenter.getCourts());
        SportCenter saveSportCenter=sportCenterRepository.save(newSportCenter);
        if(sportCenterRepository.findById(saveSportCenter.getCenterId()).isPresent()){
            return ResponseEntity.accepted().body("Successfully Created SportCenter and Courts");
        } else{
            return ResponseEntity.unprocessableEntity().body("Failed to Create specified SportCenter");
        }
    }

    public  void addPlayer(Integer centerid, Integer playerAdd){
        SportCenter sportCenter=sportCenterRepository.findById(centerid).get();
        List<Court> courts=sportCenter.getCourts();

        extracted(playerAdd, courts);
    };

    @Transactional
    private void extracted(Integer playerAdd, List<Court> courts) {
        long totalReg=courts.stream().map(x->x.getPlayers()).mapToInt(y->y).summaryStatistics().getSum();
        if((12-totalReg)<playerAdd){
            System.out.println("sorry, no places availble for "+playerAdd+" players");
        }
        else{
            int remain= playerAdd;
            Iterator it = courts.iterator();
            while(remain>0&&it.hasNext()){
                Court court=(Court) it.next();
                int emptyPlace=4-court.getPlayers();
                if(remain>=emptyPlace){
                    court.setPlayers(4);
                    System.out.println("court "+court.getCourtId()+" is full");
                    remain= playerAdd -emptyPlace;
                    courtRepository.save(court);
                }
                else{
                    court.setPlayers(court.getPlayers()+remain);
                    remain=0;
                    courtRepository.save(court);
                }
            }
        }
    }
}
