package za.co.douglasmedia.product.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import za.co.douglasmedia.product.Entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p Where p.deleted = false")
    Optional<List<Product>> findAllProductsNotDeleted();

    @Query("SELECT p FROM Product p Where p.name = ?1 AND p.deleted = false")
    Optional<Product> findProductByNameAndNotDeleted(String name);

    @Query("SELECT p FROM Product p WHERE p.productId = ?1 AND p.deleted = false")
    Optional<Product> findProductByIdAndNotDeleted(long productId);

    @Modifying
    @Query("UPDATE Product p SET p.description = ?1, p.price = ?2, p.category = ?3, p.deleted = ?4 WHERE p.name = ?5 AND p.deleted = false")
    void updateProductByName(String description, double price, Product.Category category, boolean deleted, String name);


}
