package io.mercury.indicator.pools.base;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.mercury.common.annotation.lang.AbstractFunction;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.JointKeyParams;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.api.Indicator;

public abstract class MultipleIndicatorPool<I extends Indicator<?, ?, M>, M extends MarketData>
		extends IndicatorPool<I, M> {
	
	protected final Logger log = CommonLoggerFactory.getLogger(getClass());

	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> s1IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> s2IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> s5IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> s10IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> s15IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> s30IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> m1IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> m2IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> m5IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> m10IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	
	@SuppressWarnings("unused")
	private MutableLongObjectMap<I> m15IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);

	@Nonnull
	@AbstractFunction
	protected abstract I newIndicator(TimePeriod period, int cycle, Instrument instrument);

	public I getIndicator(TimePeriod period, int cycle, Instrument instrument) {
		MutableLongObjectMap<I> indicatorMap = getIndicatorMap(period);
		long index = calculateIndex(cycle, instrument);
		return indicatorMap.getIfAbsentPut(index, newIndicator(period, cycle, instrument));
	}

	public boolean putIndicator(TimePeriod period, int cycle, Instrument instrument, I indicator) {
		MutableLongObjectMap<I> indicatorMap = getIndicatorMap(period);
		long index = calculateIndex(cycle, instrument);
		I saved = indicatorMap.get(index);
		if (saved != null) {
			log.warn("Indicator existed. period==[{}], instrumentCode==[{}], cycle==[{}]", period, instrument.id(),
					cycle);
			return false;
		}
		indicatorMap.put(index, indicator);
		return indicators.add(indicator);
	}

	private long calculateIndex(int cycle, Instrument instrument) {
		return JointKeyParams.mergeJointKey(cycle, instrument.id());
	}

	private MutableLongObjectMap<I> getIndicatorMap(TimePeriod period) {
		// TODO
		throw new IllegalArgumentException("period : " + period.seconds() + " is not found");
	}

}