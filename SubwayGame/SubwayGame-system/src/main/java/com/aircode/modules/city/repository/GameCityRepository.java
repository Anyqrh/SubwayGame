package com.aircode.modules.city.repository;

import com.aircode.modules.city.domain.GameCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameCityRepository extends JpaRepository<GameCity, Integer>, JpaSpecificationExecutor<GameCity> {
}