package vaisselle.services;

import org.springframework.stereotype.Service;
import vaisselle.models.tables.Matiere;
import vaisselle.repositories.MatiereRepository;

import java.util.List;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;

    public MatiereService(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public Matiere saveMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public Matiere getMatiere(Long idMatiere) {
        return matiereRepository.findById(idMatiere).orElse(null);
    }

    public Matiere updateMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public void deleteMatiere(Long idMatiere) {
        matiereRepository.deleteById(idMatiere);
    }
}
