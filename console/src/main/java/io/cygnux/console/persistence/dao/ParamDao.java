package io.cygnux.console.persistence.dao;

import io.cygnux.console.persistence.entity.ParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StrategyParam DAO
 *
 * @author yellow013
 */
@Repository
public interface ParamDao extends JpaRepository<ParamEntity, Long> {

    List<ParamEntity> queryByStrategyId(int strategyId);

    List<ParamEntity> queryByStrategyName(String strategyName);

}
