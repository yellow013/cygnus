package io.ffreedom.redstone.core.order;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.base.OrdQtyPrice;
import io.ffreedom.redstone.core.order.base.OrdTimestamps;
import io.ffreedom.redstone.core.order.base.TradeSet;
import io.ffreedom.redstone.core.order.enums.OrdRank;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;

public interface Order extends Comparable<Order> {

	/**
	 * ordSysId构成<br>
	 * 策略Id | 时间戳Second | 自增量Number<br>
	 * strategyId | epochSecond| increment<br>
	 * 922 | 3372036854 | 775807<br>
	 * 
	 * @return long
	 */
	long getOrdSysId();

	Instrument getInstrument();

	OrdQtyPrice getQtyPrice();

	OrdSide getSide();

	OrdType getType();

	OrdStatus getStatus();

	OrdTimestamps getTimestamps();

	Order setStatus(OrdStatus ordStatus);

	int getStrategyId();

	int getSubAccountId();

	OrdRank getRank();

	/**
	 * Order成交列表
	 * 
	 * @return TradeSet
	 */
	TradeSet getTradeSet();

	@Override
	default int compareTo(Order o) {
		return getOrdSysId() < o.getOrdSysId() ? -1 : getOrdSysId() > o.getOrdSysId() ? 1 : 0;
	}

}