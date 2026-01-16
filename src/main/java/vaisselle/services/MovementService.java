package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.Movement;
import vaisselle.models.tables.Product;
import vaisselle.repositories.MovementRepository;

import java.util.List;

@Service
public class MovementService {

    @Autowired
    private MovementRepository movementRepository;

    public MovementService() {
    }

    public double getStock(Product product) {
        return movementRepository.getStockByProduct(product.getId());
    }

    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    public Movement saveMovement(Movement Movement) {
        return movementRepository.save(Movement);
    }

    public Movement getMovement(Long idMovement) {
        return movementRepository.findById(idMovement).orElse(null);
    }

    public Movement updateMovement(Movement Movement) {
        return movementRepository.save(Movement);
    }

    public void deleteMovement(Long idMovement) {
        movementRepository.deleteById(idMovement);
    }
}
