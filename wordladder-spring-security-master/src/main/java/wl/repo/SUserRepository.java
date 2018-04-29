package wl.repo;

import wl.SUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SUserRepository extends JpaRepository<SUser, Integer> {

    @Query("select u from SUser u where u.name=?1 and u.password=?2")
    SUser login(String name, String password);

    SUser findByNameAndPassword(String name, String password);

    SUser findUserByName(String name);

}
