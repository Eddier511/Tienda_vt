package com.tienda.service;

import com.tienda.domain.Item;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    //Se utiliza una variable de session para guardar la informacion
    //del carrito de compras
    @Autowired
    private HttpSession session;
    
    //El siguiente metodo crea un item en la variable de session
    //Si la variable de session no existe.. se crea.
    public void save(Item item) {
        //Se recupera la variable de session
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        
        //Se valida si la lista existe...
        if(lista==null) {
            //Si no esta se crea...
            lista = new ArrayList<Item>();
        }
        
        //Se busca el idProducto en la lista
        boolean existe=false;
        for(Item i : lista) {
            if(Objects.equals(i.getIdProducto(),item.getIdProducto())) {
                existe=true;
                if(i.getCantidad()<i.getExistencias()) {
                    //aun puedo comprar...
                    i.setCantidad(i.getCantidad()+1);
                }
                break;
            }
        }
        
        //Validamos si es la primera vez que se ingresa el producto en el carrito
        if(!existe) {
            item.setCantidad(1);
            lista.add(item);
        }
        session.setAttribute("listaItems", lista);
    }
    
    //El siguiente metodo recupera un item de la variable de session
    //Si no existe retorna null.
    public Item getItem(Item item) {
        //Se recupera la variable de session
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        
        //Se valida si la lista existe...
        if(lista==null) {
            return null;
        }
        
        //Se busca el idProducto en la lista
        for(Item i : lista) {
            if(Objects.equals(i.getIdProducto(),item.getIdProducto())) {
                return i;
            }
        }
        return null;
    }
    
    //El siguiente metodo retorna el total de comprar del carrito
    public double getTotal() {
        //Se recupera la variable de session
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        
        //Se valida si la lista existe...
        if(lista==null) {
            return 0;
        }
        
        //Se acumula el total en el ciclo
        double total=0;
        for(Item i : lista) {
            total+=i.getCantidad()*i.getPrecio();
            }
        return total;
    }
    
    //El siguiente metodo retorna la lista completa del carrito
    public List<Item> getItems() {
        //Se recupera la variable de session
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        
        return lista;
    }
    

}
