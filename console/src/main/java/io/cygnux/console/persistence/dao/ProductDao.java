package io.cygnux.console.persistence.dao;

import io.cygnux.console.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Long> {

    ProductEntity queryByProductId(int productId);

}
