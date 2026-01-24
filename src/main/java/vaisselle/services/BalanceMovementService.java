package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.BalanceMovement;
import vaisselle.repositories.BalanceMovementRepository;

@Service
public class BalanceMovementService {
    @Autowired
    private BalanceMovementRepository repository;

    public BalanceMovement save(BalanceMovement movement) {
        return repository.save(movement);
    }

    public double getTotalIn() {
        Double total = repository.sumValueIn();
        return total != null ? total : 0.0;
    }

    public double getTotalOut() {
        Double total = repository.sumValueOut();
        return total != null ? total : 0.0;
    }
}
