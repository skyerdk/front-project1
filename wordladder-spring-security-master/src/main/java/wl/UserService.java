package wl;

import wl.repo.SUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private SUserRepository suserRepository;//code10

    public List<SUser> findAll() {
        return suserRepository.findAll();
    }

    public SUser create(SUser user) {
        return suserRepository.save(user);
    }

    public SUser findUserById(int id) {
        return suserRepository.findOne(id);

    }

    public SUser login(String name, String password) {
        return suserRepository.findByNameAndPassword(name, password);
    }

    public SUser update(SUser user) {
        return suserRepository.save(user);
    }

    public void deleteUser(int id) {
        suserRepository.delete(id);
    }

    public SUser findUserByName(String name) {
        return suserRepository.findUserByName(name);
    }
}
