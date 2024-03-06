package di.service.boat;


import di.repository.boat.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoatService {
    @Autowired
    private final BoatRepository repository;

    public BoatService(BoatRepository repository) {
        this.repository = repository;
    }


}
