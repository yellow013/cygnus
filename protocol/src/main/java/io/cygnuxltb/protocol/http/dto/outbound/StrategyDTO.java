package io.cygnuxltb.protocol.http.dto.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 策略表
 * Strategy Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class StrategyDTO {

    private long uid;

    private int strategyId;

    private String strategyName;

    private String strategyOwner;

    private String strategyInfo;

}
