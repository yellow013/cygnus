package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ServiceException;
import io.cygnuxltb.console.persistence.entity.InstrumentSettlementEntity;
import io.cygnuxltb.console.service.InstrumentService;
import io.cygnuxltb.console.service.dto.InstrumentPrice;
import io.cygnuxltb.console.controller.util.RequestUtil;
import io.cygnuxltb.console.controller.util.ResponseUtil;
import io.mercury.common.util.StringSupport;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RestController
@RequestMapping(path = "/instrument")
public final class InstrumentController {

    @Resource
    private InstrumentService service;

    /**
     * Get Settlement Price
     *
     * @param instrumentCode String
     * @param tradingDay     int
     * @return ResponseEntity<List < InstrumentSettlementEntity>>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping(path = "/settlement")
    public List<InstrumentSettlementEntity> getSettlementPrice(
            @RequestParam("instrumentCode") String instrumentCode,
            @RequestParam("tradingDay") int tradingDay) {
        if (RequestUtil.paramIsNull(instrumentCode, tradingDay)) {
            return null;
        }
        return service.getInstrumentSettlement(instrumentCode, tradingDay);
    }

    // LastPrices Cache
    private static final ConcurrentHashMap<String, InstrumentPrice> lastPriceMap = new ConcurrentHashMap<>();

    /**
     * Get LastPrices
     *
     * @param instrumentCodes String
     * @return ResponseEntity<List < InstrumentPrice>>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping(path = "/last_price")
    public ResponseEntity<List<InstrumentPrice>> getLastPrice(@RequestParam("instrumentCodes") String instrumentCodes) {
        if (StringSupport.isNullOrEmpty(instrumentCodes))
            return ResponseUtil.badRequest();
        var lastPrices = stream(instrumentCodes.split(",")).map(instrumentCode -> lastPriceMap
                        .putIfAbsent(instrumentCode, new InstrumentPrice().setInstrumentCode(instrumentCode)))
                .collect(Collectors.toList());
        return ResponseUtil.responseOf(lastPrices);
    }

    /**
     * Put LastPrice
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(ServiceException.class)
    @PutMapping(path = "/last_price")
    public ResponseEntity<Object> putLastPrice(@RequestBody HttpServletRequest request) {
        var price = RequestUtil.bodyToObject(request, InstrumentPrice.class);
        if (price == null)
            return ResponseUtil.badRequest();
        lastPriceMap.put(price.getInstrumentCode(), price);
        return ResponseUtil.ok();
    }

    /**
     * Get [SymbolTradingFee] for [symbol]
     *
     * @param instrumentCode String
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> getSymbolTradingFeeByName(@RequestParam("instrumentCode") String instrumentCode) {
        var instrument = service.getInstrument(instrumentCode);
        return ResponseUtil.responseOf(instrument);
    }

    /**
     * Get [TradableInstrument] for [symbol] and [tradingDay]
     *
     * @param symbol     String
     * @param tradingDay String
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping(path = "/tradable/{tradingDay}/{symbol}")
    public ResponseEntity<Object> getTradableInstrument(@PathVariable("tradingDay") int tradingDay,
                                                        @PathVariable("symbol") String symbol) {
        return ResponseUtil.responseOf(null);
    }

}
