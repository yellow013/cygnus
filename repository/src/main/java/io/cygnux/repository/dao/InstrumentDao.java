package io.cygnux.repository.dao;

import io.cygnux.repository.entity.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Instrument DAO
 *
 * @author yellow013
 */
@Repository
public interface InstrumentDao extends JpaRepository<InstrumentEntity, Long> {

    @Query("SELECT '*' FROM #{#entityName} e "
            + " WHERE "
            + "e.instrumentCode LIKE :instrumentCode ")
    List<InstrumentEntity> query(@Nullable String instrumentCode);

}
