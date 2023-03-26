package io.cygnuxltb.protocol.http.dto.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易产品表
 * Product Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ProductDTO {

    private long uid;

    private int productId;

    private String productName;

    private String subAccountId;

    private String userId;

    private String interfaceType;

}
