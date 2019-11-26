package io.redstone.adaptor.jctp;

import java.util.stream.Collectors;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.jctp.JctpGateway;
import io.redstone.adaptor.jctp.converter.outbound.CtpOutboundCancelOrderConverter;
import io.redstone.adaptor.jctp.converter.outbound.CtpOutboundNewOrderConverter;
import io.redstone.adaptor.jctp.exception.OrderRefNotFoundException;
import io.redstone.adaptor.jctp.utils.JctpOrderRefKeeper;
import io.redstone.core.account.Account;
import io.redstone.core.adaptor.dto.SubscribeMarketData;
import io.redstone.core.adaptor.impl.OutboundAdaptor;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.impl.ChildOrder;

public class JctpOutboundAdaptor extends OutboundAdaptor {
	
	private final Logger logger = CommonLoggerFactory.getLogger(getClass());

	private Converter<Order, CThostFtdcInputOrderField> newOrderConverter = new CtpOutboundNewOrderConverter();

	private Converter<Order, CThostFtdcInputOrderActionField> cancelOrderConverter = new CtpOutboundCancelOrderConverter();

	private JctpGateway gateway;
	
	public JctpOutboundAdaptor(int adaptorId, String adaptorName, JctpGateway gateway) {
		super(adaptorId, adaptorName);
		this.gateway = gateway;
	}

	@Override
	public boolean close() {
		return false;
	}

	@Override
	public boolean newOredr(ChildOrder order) {
		try {
			CThostFtdcInputOrderField ctpNewOrder = newOrderConverter.convert(order);
			JctpOrderRefKeeper.put(ctpNewOrder.getOrderRef(), order.getOrdSysId());
			gateway.newOrder(ctpNewOrder);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean cancelOrder(ChildOrder order) {
		try {
			CThostFtdcInputOrderActionField ctpCancelOrder = cancelOrderConverter.convert(order);
			String orderRef = JctpOrderRefKeeper.getOrderRef(order.getOrdSysId());
			ctpCancelOrder.setOrderRef(orderRef);
			gateway.cancelOrder(ctpCancelOrder);
			return true;
		} catch (OrderRefNotFoundException e) {
			logger.error(e.getMessage());
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean subscribeMarketData(SubscribeMarketData subscribeMarketData) {
		try {
			gateway.subscribeMarketData(subscribeMarketData.getInstrumentSet().stream()
					.map(instrument -> instrument.getInstrumentCode()).collect(Collectors.toSet()));
			return true;
		} catch (Exception e) {
			logger.error("subscribeMarketData throw {}", e.getClass().getSimpleName(), e);
			return false;
		}
	}

	@Override
	public boolean queryPositions(Account account) {
		// TODO
		return false;
	}

	@Override
	public boolean queryBalance(Account account) {
		// TODO
		return false;
	}

}