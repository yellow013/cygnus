package io.cygnuxltb.console.service.dto.pack;

import io.mercury.common.codec.Envelope;
import io.mercury.common.collections.ImmutableMaps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

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

    private static final ImmutableIntObjectMap<InboxTitle> Map =
            ImmutableMaps.toImmutableIntObjectMap(InboxTitle::getCode, InboxTitle.values());

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
