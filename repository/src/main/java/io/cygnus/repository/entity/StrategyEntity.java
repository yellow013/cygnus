package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.CommonColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_strategy")
@Entity(name = "cyg_strategy")
public final class StrategyEntity {

	@Id
	@Column(name = CommonColumn.STRATEGY_ID)
	private int strategyId;

	@Column(name = "strategy_name")
	private int strategyName;

}
