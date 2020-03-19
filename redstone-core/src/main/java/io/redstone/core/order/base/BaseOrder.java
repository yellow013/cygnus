package io.redstone.core.order.base;

import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.order.Order;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.OrdTimestamps;
import io.redstone.core.order.utils.OrdSysIdGenerate;

public abstract class BaseOrder implements Order {

	/**
	 * ordSysId
	 */
	private long ordSysId;

	/**
	 * instrument
	 */
	private Instrument instrument;

	/**
	 * 数量
	 */
	private OrdQty ordQty;

	/**
	 * 价格
	 */
	private OrdPrice ordPrice;

	/**
	 * 订单方向
	 */
	private OrdSide ordSide;

	/**
	 * 订单类型
	 */
	private OrdType ordType;

	/**
	 * 订单状态
	 */
	private OrdStatus ordStatus;

	/**
	 * 时间戳
	 */
	private OrdTimestamps ordTimestamps;

	/**
	 * 策略Id
	 */
	private int strategyId;

	/**
	 * 子账户Id
	 */
	private int subAccountId;

	protected BaseOrder(Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId) {
		this.ordSysId = OrdSysIdGenerate.next(strategyId);
		this.instrument = instrument;
		this.ordQty = ordQty;
		this.ordPrice = ordPrice;
		this.ordSide = ordSide;
		this.ordType = ordType;
		this.ordStatus = OrdStatus.PendingNew;
		this.ordTimestamps = OrdTimestamps.generate();
		this.strategyId = strategyId;
		this.subAccountId = subAccountId;

	}

	@Override
	public long ordSysId() {
		return ordSysId;
	}

	@Override
	public Instrument instrument() {
		return instrument;
	}

	@Override
	public OrdQty ordQty() {
		return ordQty;
	}

	@Override
	public OrdPrice ordPrice() {
		return ordPrice;
	}

	@Override
	public OrdSide ordSide() {
		return ordSide;
	}

	@Override
	public OrdType ordType() {
		return ordType;
	}

	@Override
	public OrdStatus ordStatus() {
		return ordStatus;
	}

	@Override
	public OrdTimestamps ordTimestamps() {
		return ordTimestamps;
	}

	@Override
	public OrdStatus ordStatus(OrdStatus ordStatus) {
		this.ordStatus = ordStatus;
		return this.ordStatus;
	}

	@Override
	public int strategyId() {
		return strategyId;
	}

	@Override
	public int subAccountId() {
		return subAccountId;
	}

}
