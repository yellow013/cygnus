package io.cygnux.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         Bar
 *
 */

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "st_bar")
@Entity(name = StBar.EntityName)
public final class StBar {

	public final static String EntityName = "StBar";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private long uid;

	/*
	 * CygID int
	 */
	@Column(name = "cyg_id")
	private int cygId;

	/*
	 * InstrumentID varchar 31
	 */
	@Column(name = "instrument_code")
	private String instrumentCode;

	/*
	 * TradingDay date
	 */
	@Column(name = "trading_day")
	private long tradingDay;

	/*
	 * Time datetime
	 */
	@Column(name = "time_point")
	private int timePoint;

	/*
	 * Open double 19_4
	 */
	@Column(name = "open")
	private double open;

	/*
	 * High double 19_4
	 */
	@Column(name = "high")
	private double high;

	/*
	 * Low double 19_4
	 */
	@Column(name = "low")
	private double low;

	/*
	 * Close double 19_4
	 */
	@Column(name = "close", nullable = false)
	private double close;

	/*
	 * VWAP double 19_4
	 */
	@Column(name = "vwap")
	private double vwap;

	/*
	 * VWAP double 19_4
	 */
	@Column(name = "volume")
	private double volume;

}