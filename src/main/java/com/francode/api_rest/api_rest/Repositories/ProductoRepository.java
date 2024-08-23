package com.francode.api_rest.api_rest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.francode.api_rest.api_rest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
