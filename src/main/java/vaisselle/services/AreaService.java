package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vaisselle.models.tables.Area;
import vaisselle.repositories.AreaRepository;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public AreaService() {
    }

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    public Area saveArea(Area area) {
        return areaRepository.save(area);
    }

    public Area getarea(Long idArea) {
        return areaRepository.findById(idArea).orElse(null);
    }

    public Area updatearea(Area Area) {
        return areaRepository.save(Area);
    }

    public void deletearea(Long idArea) {
        areaRepository.deleteById(idArea);
    }
}
