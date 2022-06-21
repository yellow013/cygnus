package io.cygnux.repository.dao;

import java.util.List;

import javax.annotation.Nullable;

import io.cygnux.repository.entities.internal.InInstrumentStatic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * InstrumentSettlement DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface InstrumentSettlementDao extends JpaRepository<InInstrumentStatic, Long> {

	@Query("SELECT * FROM #{#entityName} e " 
			+ " WHERE "
			+ " e.instrumentCode LIKE :instrumentCode% " 
			+ " AND "
			+ " e.tradingDay = :tradingDay ")
	List<InInstrumentStatic> query(@Nullable String instrumentCode, int tradingDay);
	
}
