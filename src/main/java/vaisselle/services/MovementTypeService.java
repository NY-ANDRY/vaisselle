package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.MovementType;
import vaisselle.repositories.MovementTypeRepository;

import java.util.List;

@Service
public class MovementTypeService {

    @Autowired
    private MovementTypeRepository movementTypeRepository;

    public MovementTypeService() {
    }

    public List<MovementType> getAllMovements() {
        return movementTypeRepository.findAll();
    }

    public MovementType saveMovement(MovementType movementType) {
        return movementTypeRepository.save(movementType);
    }

    public MovementType getMovement(Long idMovement) {
        return movementTypeRepository.findById(idMovement).orElse(null);
    }

    public MovementType updateMovement(MovementType movementType) {
        return movementTypeRepository.save(movementType);
    }

    public void deleteMovement(Long idMovement) {
        movementTypeRepository.deleteById(idMovement);
    }
}
