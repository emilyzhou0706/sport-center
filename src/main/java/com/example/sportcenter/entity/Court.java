package com.example.sportcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_court")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courtId;

    private int players;

    @JsonIgnore
    @ManyToOne
    private SportCenter sportCenter;

    @Transient
    private Integer linkId;
    public Integer getLinkId() {
        return getSportCenter().getCenterId();
    }
    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public boolean checkCourtFull(int joinPlayers){
        if(this.players+joinPlayers>4){
            System.out.println("sorry court: "+courtId+" is already full, please choose other court/day");
            return false;
        }
        else{
            System.out.println("congrads, court "+courtId+ " registered sucessfully");
            return true;
        }
    }

    public int registerCourt(int joinPlayers){
        this.players=this.players+joinPlayers;
        return players;
    }
}
