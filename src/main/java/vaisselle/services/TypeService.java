package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.Type;
import vaisselle.repositories.TypeRepository;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public TypeService() {
    }

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    public Type getType(Long idType) {
        return typeRepository.findById(idType).orElse(null);
    }

    public Type updateType(Type type) {
        return typeRepository.save(type);
    }

    public void deleteType(Long idType) {
        typeRepository.deleteById(idType);
    }
}
