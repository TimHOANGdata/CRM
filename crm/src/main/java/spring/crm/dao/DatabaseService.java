package spring.crm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    @Autowired 
    ClientRepository clientRepository;

    @Autowired
    OrderRepository orderRepository;


    // CLIENTS
    public void addClient(Client client){
        clientRepository.save(client);
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public void updateClient(Client client, Integer id){
        clientRepository.save(client);
    }
    
    public Optional<Client> getClientById(Integer Id){
        return clientRepository.findById(Id);
    }

    public void deleteClientById(Integer id){
        clientRepository.deleteById(id);
    }

    // ORDERS

    public void addOrder(Order order){
        orderRepository.save(order);
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public void updateOrder(Order order, Integer id){
        orderRepository.save(order);
    }
    
    public Optional<Order> getOrderById(Integer Id){
        return orderRepository.findById(Id);
    }

    public void deleteOrderById(Integer id){
        orderRepository.deleteById(id);
    }
}
