package danekerscode.akbulakserver.service;

import danekerscode.akbulakserver.exception.EntityPropertiesException;
import danekerscode.akbulakserver.model.Boutique;
import danekerscode.akbulakserver.payload.BoutiqueRequest;
import danekerscode.akbulakserver.repository.AddressRepository;
import danekerscode.akbulakserver.repository.BoutiqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoutiqueService {

    private final BoutiqueRepository repository;
    private final CategoryService categoryService;
    private final AddressRepository addressRepository;


    public List<Boutique> getAll() {
        return repository.findAll();
    }

    public Boutique save(BoutiqueRequest boutiqueRequest) {
        var boutiqueOptional = repository.findBoutiqueByAddressId(boutiqueRequest.addressId());
        if (boutiqueOptional.isPresent()){ // check if it was saved before in the database
           return this.update(boutiqueOptional.get().getId(),boutiqueRequest); // if true => just update exists boutique
        }else { // otherwise create new with fields from request body['boutiqueRequest']
            var address = addressRepository.findById(boutiqueRequest.addressId()).orElseThrow();
            Boutique boutique = Boutique
                    .builder()
                    .name(boutiqueRequest.name())
                    .comment(boutiqueRequest.comment())
                    .address(address)
                    .categories(categoryService.saveAll(boutiqueRequest.categories()))
                    .build();


            address.setContainer(boutiqueRequest.container());
            address.setBoutique(boutique);
            repository.saveAndFlush(boutique);
            addressRepository.save(address);
            return boutique;
        }
    }


    public Boutique getById(Integer id) { // get by id
        return repository.findById(id)
                .orElseThrow(() -> new EntityPropertiesException("invalid boutique id"));
    }

    public void release(Integer id) { // if boutique deleted from address
        var founded = repository.findById(id).orElseThrow();

        founded.getAddress().setBoutique(null);
        founded.getAddress().setContainer(String.valueOf(founded.getAddress().getId()));

        addressRepository.save(founded.getAddress());
        founded.setAddress(null);

        repository.deleteById(id);
    }

    public Boutique update(Integer id,
                           BoutiqueRequest boutiqueRequest) { // setting updated values
        Boutique boutique = repository.findById(id).orElseThrow(() -> new EntityPropertiesException("invalid boutique id"));

        boutique.setName(boutiqueRequest.name());
        boutique.setCategories(categoryService.saveAll(boutiqueRequest.categories()));
        boutique.setComment(boutiqueRequest.comment());

        var address = addressRepository.findById(boutiqueRequest.addressId()).orElseThrow();
        address.setContainer(boutiqueRequest.container());

        return repository.save(boutique);
    }

}
