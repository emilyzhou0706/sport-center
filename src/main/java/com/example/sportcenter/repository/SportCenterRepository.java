package com.example.sportcenter.repository;

import com.example.sportcenter.entity.SportCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportCenterRepository extends JpaRepository<SportCenter,Integer> {
}
