package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.BalanceMovementType;
import vaisselle.repositories.BalanceMovementTypeRepository;

@Service
public class BalanceMovementTypeService {
    @Autowired
    private BalanceMovementTypeRepository repository;

    public BalanceMovementType findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public BalanceMovementType save(BalanceMovementType type) {
        return repository.save(type);
    }
}
