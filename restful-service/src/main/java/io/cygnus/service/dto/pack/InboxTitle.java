package io.cygnus.service.dto.pack;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.codec.Envelope;
import io.mercury.common.collections.MutableMaps;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum InboxTitle implements Envelope {

	Unknown(-1),

	Heartbeat(0),

	LastPrice(1),

	Bar(2),

	Order(3),

	MetricData(7),

	;

	@Getter
	private final int code;

	private static final MutableIntObjectMap<InboxTitle> Map = MutableMaps.newIntObjectHashMap();

	static {
		for (InboxTitle value : InboxTitle.values())
			Map.put(value.code, value);
	}

	public static InboxTitle checkout(int code) {
		InboxTitle value;
		if ((value = Map.get(code)) != null)
			return value;
		throw new IllegalArgumentException("checkout with code -> " + code + " is null");
	}

	@Override
	public int getVersion() {
		return 1;
	}

}
