package com.aircode.modules.site.repository;

import com.aircode.modules.site.domain.GameSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zeta
* @date 2020-02-28
*/
public interface GameSiteRepository extends JpaRepository<GameSite, Integer>, JpaSpecificationExecutor<GameSite> {
}