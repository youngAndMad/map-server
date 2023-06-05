package danekerscode.akbulakserver.repository;

import danekerscode.akbulakserver.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByBazarAndFloor(String bazar, Integer floor);
}