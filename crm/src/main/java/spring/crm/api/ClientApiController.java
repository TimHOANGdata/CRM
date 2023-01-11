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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import spring.crm.dao.Client;
import spring.crm.dao.DatabaseService;

@RestController
@RequestMapping("api")
public class ClientApiController {
    
    @Autowired
    DatabaseService databaseService;

    @GetMapping("clients")
    public List<Client> getClients(){
        return databaseService.getClients();
    }

    @GetMapping("clients/{id}")
    public ResponseEntity getClientById(@PathVariable Integer id){
        Optional<Client> client = databaseService.getClientById(id);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(client.get());
        }
    }

    @PostMapping("clients")
    public void addClient(@RequestBody Client client){
        databaseService.addClient(client);
    }
    
    @PutMapping("clients/{id}")
    public ResponseEntity updateClient(@RequestBody Client clientUpdate, @PathVariable Integer id){
        Optional<Client> client = databaseService.getClientById(id);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            if(clientUpdate.getId().equals(id)){
                databaseService.updateClient(clientUpdate, id);
                return ResponseEntity.ok(client.get());
            }else{
                return ResponseEntity.badRequest().build();
            }
            
        }
    }

    @DeleteMapping("clients/{id}")
    public ResponseEntity deleteClientById(@PathVariable Integer id){
        Optional<Client> client = databaseService.getClientById(id);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            databaseService.deleteClientById(id);
            return ResponseEntity.ok(null);
        }
    }
}
