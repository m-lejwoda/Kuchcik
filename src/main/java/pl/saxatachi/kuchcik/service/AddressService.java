package pl.saxatachi.kuchcik.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.Address;
import pl.saxatachi.kuchcik.repository.AddressRepository;
@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    public void saveAddress(Address address){
        addressRepository.save(address);
    }

}
