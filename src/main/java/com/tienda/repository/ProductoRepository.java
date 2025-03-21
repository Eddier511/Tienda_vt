
package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductoRepository extends JpaRepository<Producto,Long> {
    
    //Consulta AMpliada para filtrar productos en un rango de precios, ordenados
    //por precios ascendentes
    public List<Producto> findByPrecioBetweenOrderByPrecio(double precioInf, double precioSup); 
    
    //Consulta JPQL para filtrar productos en un rango de precios, ordenados por precios ascendentes
    @Query(value="SELECT a FROM Producto a WHERE a.precio BETWEEN :precioInf and :precioSup ORDER BY a.precio")
    public List<Producto> consultaJPQL(
            @Param("precioInf") double precioInf, 
            @Param("precioSup") double precioSup); 
    
    //Consulta SQL para filtrar productos en un rango de precios, ordenados por precios ascendentes
    @Query(nativeQuery=true,
            value="SELECT * FROM producto a WHERE a.precio BETWEEN :precioInf and :precioSup ORDER BY a.precio")
    public List<Producto> consultaSQL(
            @Param("precioInf") double precioInf, 
            @Param("precioSup") double precioSup); 
    
}
