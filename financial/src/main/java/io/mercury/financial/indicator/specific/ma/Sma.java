package io.mercury.financial.indicator.specific.ma;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.common.collections.list.FixedLengthRecorder;
import io.mercury.financial.indicator.base.FixedPeriodIndicator;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.time.TimePeriodPool;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;

public final class Sma extends FixedPeriodIndicator<SmaPoint, SmaEvent> {

	private FixedLengthRecorder historyPriceRecorder;

	public Sma(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period, cycle);

		this.historyPriceRecorder = FixedLengthRecorder.newRecorder(cycle);
		ImmutableSortedSet<TimePeriodSerial> timePeriodSet = TimePeriodPool.Singleton.getTimePeriodSet(instrument, period);
		int i = -1;
		for (TimePeriodSerial timePeriod : timePeriodSet)
			pointSet.add(SmaPoint.with(++i, instrument, period, timePeriod, cycle, historyPriceRecorder));
		currentPoint = pointSet.getFirst();

	}

	public static Sma with(Instrument instrument, TimePeriod period, int cycle) {
		return new Sma(instrument, period, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}