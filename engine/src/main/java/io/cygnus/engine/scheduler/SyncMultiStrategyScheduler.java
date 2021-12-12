package io.cygnus.engine.scheduler;

import java.io.IOException;

import org.slf4j.Logger;

import io.cygnus.engine.trader.OrderKeeper;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.report.AdaptorReport;
import io.horizon.trader.report.OrderReport;
import io.mercury.common.log.CommonLoggerFactory;

/**
 * 
 * @author yellow013
 * 
 *         策略执行引擎与整体框架分离
 *
 */
public final class SyncMultiStrategyScheduler<M extends MarketData> extends AbstractMultiStrategyScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(SyncMultiStrategyScheduler.class);

	public SyncMultiStrategyScheduler() {

	}

	@Override
	public void onMarketData(M marketData) {
		MarketDataKeeper.onMarketDate(marketData);
		subscribedMap.get(marketData.getInstrumentId()).each(strategy -> {
			if (strategy.isEnabled()) {
				strategy.onMarketData(marketData);
			}
		});
	}

	@Override
	public void onOrderReport(OrderReport report) {
		log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", report.getBrokerUniqueId(),
				report.getOrdSysId());
		ChildOrder order = OrderKeeper.handleOrderReport(report);
		log.info("Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
				report.getBrokerUniqueId(), order.getStrategyId(), order.getInstrument().getInstrumentCode(),
				report.getOrdSysId());
		strategyMap.get(order.getStrategyId()).onOrder(order);
	}

	// TODO add pools
	@Override
	public void onAdaptorReport(AdaptorReport report) {
		log.info("Recv AdaptorReport -> {}", report);
	}


	@Override
	protected void close0() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
