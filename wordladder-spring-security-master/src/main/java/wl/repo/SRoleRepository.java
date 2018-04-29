package wl.repo;

import wl.SRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SRoleRepository extends JpaRepository<SRole,Integer> {

}