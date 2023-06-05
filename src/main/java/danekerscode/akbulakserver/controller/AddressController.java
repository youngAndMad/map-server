package danekerscode.akbulakserver.controller;

import danekerscode.akbulakserver.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("address")
@CrossOrigin(origins = "http://localhost:4200/")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("find")
    public ResponseEntity<?> getByDetails(
            @RequestParam("floor") Integer floor,
            @RequestParam("bazar") String bazar
    ) {
        return ResponseEntity.ok(addressService.getByDetails(floor, bazar));
    }

    @PostMapping("initialization")
    public ResponseEntity<?> initialization(
            @RequestParam("floor") Integer floor,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("bazar") String bazar
            ) {
        this.addressService.initialization(bazar,floor,quantity);
        return ResponseEntity.ok(this.getByDetails(floor , bazar));
    }
}
