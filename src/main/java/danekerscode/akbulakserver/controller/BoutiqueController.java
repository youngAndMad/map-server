package danekerscode.akbulakserver.controller;

import danekerscode.akbulakserver.payload.BoutiqueRequest;
import danekerscode.akbulakserver.service.BoutiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequiredArgsConstructor
@RequestMapping("boutique")
@CrossOrigin(origins = "http://localhost:4200/")
public class BoutiqueController {

    private final BoutiqueService boutiqueService;

    @GetMapping("all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(boutiqueService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(boutiqueService.getById(id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(ACCEPTED)
    public void delete(@PathVariable Integer id) {
        boutiqueService.release(id);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody BoutiqueRequest boutiqueRequest) {
        return ResponseEntity.ok(boutiqueService.save(boutiqueRequest));
    }

}
