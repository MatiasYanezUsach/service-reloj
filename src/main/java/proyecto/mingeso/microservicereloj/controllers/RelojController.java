package proyecto.mingeso.microservicereloj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import proyecto.mingeso.microservicereloj.entities.RelojEntity;
import proyecto.mingeso.microservicereloj.repositories.RelojRepository;
import proyecto.mingeso.microservicereloj.services.RelojService;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/marcas")
@CrossOrigin(origins = "*")
public class RelojController {
    @Autowired
    RelojService relojService;
    @Autowired
    RelojRepository relojRepository;

    @GetMapping
    public ResponseEntity<ArrayList<RelojEntity>> obtenerMarcas() {
        ArrayList<RelojEntity> marcas = relojService.obtenerMarcas();
        if(marcas.isEmpty()) {
            return ResponseEntity.ok(marcas);
        }
        else {
            return ResponseEntity.ok(marcas);
        }
    }
    @GetMapping("/byRut/{rut_dado}")
    public ResponseEntity<ArrayList<RelojEntity>> obtenerMarcasByRut(@PathVariable("rut_dado") String rut_dado) {
        ArrayList<RelojEntity> marcas = relojRepository.findByRut(rut_dado);
        if(marcas.isEmpty()){
            return ResponseEntity.ok(marcas);
        }
        else{
            return ResponseEntity.ok(marcas);
        }
    }
    @PostMapping("/importar")
    public ResponseEntity<String> importarArchivos(@RequestParam("file") MultipartFile file) {
        String nombreArchivo= file.getOriginalFilename();
        if (Objects.equals(nombreArchivo, "DATOS.txt") || Objects.equals(nombreArchivo, "DATA.txt") ) {
            relojService.lectura(relojService.save(file));
            return ResponseEntity.ok().body("Archivo subido correctamente");
        }
        else{
            return ResponseEntity.badRequest().body("El archivo ingresado no es valido");
        }
    }
}