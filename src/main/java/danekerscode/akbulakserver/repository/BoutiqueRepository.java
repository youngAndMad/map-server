package danekerscode.akbulakserver.repository;

import danekerscode.akbulakserver.model.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoutiqueRepository extends
        JpaRepository<Boutique, Integer> {
    Optional<Boutique> findBoutiqueByAddressId(Integer id);
    Optional<Boutique> findBoutiqueByAddressBazarAndAddressContainerAndAddressDivId(String bazar, String container, Integer divId);
}
