
package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activos) {
        
        var lista = categoriaRepository.findAll();
        
        return lista;
        
    }
    
    @Transactional(readOnly=true)
    public Categoria getCategoria(Categoria categoria) {
        categoria=categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
        return categoria;
        
    }
    
    @Transactional
    public void delete(Categoria categoria) {
        //si el idCategoria pasado existe, se elimina ese 
        categoriaRepository.delete(categoria);
        
    }
    
    @Transactional
    public void save(Categoria categoria) {
        //si el objeto categoria tiene info en idCategoria, se actualiza el registro
        //de ese idCategoria, si el objeto categoria NO tiene info en idCategoria, 
        //se inserta un nuevo registro
        categoriaRepository.save(categoria);
        
    }
    
}
