package com.aircode.modules.subwaypath.repository;

import com.aircode.modules.subwaypath.domain.GameSubwaypath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameSubwaypathRepository extends JpaRepository<GameSubwaypath, Integer>, JpaSpecificationExecutor<GameSubwaypath> {
}