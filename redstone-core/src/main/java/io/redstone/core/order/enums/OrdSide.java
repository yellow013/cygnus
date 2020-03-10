package io.redstone.core.order.enums;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.redstone.core.trade.enums.TrdDirection;

public enum OrdSide {

	Invalid(-1, TrdDirection.Invalid),

	Buy(0x01, TrdDirection.Long),

	MarginBuy(0x02, TrdDirection.Long),

	Sell(0x04, TrdDirection.Short),

	ShortSell(0x08, TrdDirection.Short),

	;

	private int code;
	private TrdDirection direction;

	private static Logger logger = CommonLoggerFactory.getLogger(OrdStatus.class);

	private OrdSide(int code, TrdDirection direction) {
		this.code = code;
		this.direction = direction;
	}

	public int code() {
		return code;
	}

	public TrdDirection direction() {
		return direction;
	}

	public static OrdSide valueOf(int code) {
		switch (code) {
		case 1:
			return Buy;
		case 2:
			return Sell;
		case 3:
			return MarginBuy;
		case 4:
			return ShortSell;
		default:
			logger.error("OrdSide.valueOf(code=={}) -> is no matches, return OrdSide.Invalid", code);
			return Invalid;
		}
	}

}