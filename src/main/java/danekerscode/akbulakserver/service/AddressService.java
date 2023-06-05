package danekerscode.akbulakserver.service;

import danekerscode.akbulakserver.model.Address;
import danekerscode.akbulakserver.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAll() {
        return addressRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void initialization(String bazar, Integer floor, Integer quantity) {
        Stream.iterate(1, i -> i <= quantity, i -> i + 1)
                .forEach(id -> {
                            Address address = new Address(id, bazar, floor, String.valueOf(id), null);
                            this.addressRepository.save(address);
                        }
                );
    }

    public List<Address> getByDetails(Integer floor, String bazar) {
        return addressRepository.findAllByBazarAndFloor(bazar, floor);
    }

}
