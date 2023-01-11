package spring.crm.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import spring.crm.dao.DatabaseService;
import spring.crm.dao.Order;

@RestController
@RequestMapping("api")
public class OrderApiController {
    
    @Autowired
    DatabaseService databaseService;

    @GetMapping("orders")
    public List<Order> getOrders(){
        return databaseService.getOrders();
    }

    @GetMapping("orders/{id}")
    public ResponseEntity getOrderById(@PathVariable Integer id){
        Optional<Order> order = databaseService.getOrderById(id);
        if(order.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(order.get());
        }
    }

    @PostMapping("orders")
    public void addOrder(@RequestBody Order order){
        databaseService.addOrder(order);
    }

    @PutMapping("orders/{id}")
    public ResponseEntity updateOrder(@RequestBody Order orderUpdate, @PathVariable Integer id){
        Optional<Order> order = databaseService.getOrderById(id);
        if(order.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            if(orderUpdate.getId().equals(id)){
                databaseService.updateOrder(orderUpdate, id);
                return ResponseEntity.ok(order.get());
            }else{
                return ResponseEntity.badRequest().build();
            }
            
        }
    }
    @DeleteMapping("orders/{id}")
    public ResponseEntity deleteOrderById(@PathVariable Integer id){
        Optional<Order> order = databaseService.getOrderById(id);
        if(order.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            databaseService.deleteOrderById(id);
            return ResponseEntity.ok(null);
        }
    }
}
