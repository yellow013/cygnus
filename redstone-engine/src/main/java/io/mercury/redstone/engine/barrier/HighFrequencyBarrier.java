package io.mercury.redstone.engine.barrier;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.redstone.core.order.specific.ChildOrder;
import io.mercury.redstone.core.risk.OrderBarrier;

public final class HighFrequencyBarrier implements OrderBarrier<ChildOrder> {

	private Logger log = CommonLoggerFactory.getLogger(getClass());

	@Override
	public boolean filter(ChildOrder order) {
		switch (order.direction()) {
		case Long:
			
			return false;
		
		case Short:

			return false;

		default:
			log.error("");
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(Integer.MAX_VALUE);
		
		
	}

}