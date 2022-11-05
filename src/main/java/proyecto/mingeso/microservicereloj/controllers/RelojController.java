package proyecto.mingeso.microservicereloj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyecto.mingeso.microservicereloj.entities.RelojEntity;
import proyecto.mingeso.microservicereloj.services.RelojService;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/marcas")
public class RelojController {
    @Autowired
    RelojService relojService;

    public ResponseEntity<ArrayList<RelojEntity>> obtenerMarcas() {
        ArrayList<RelojEntity> marcas = relojService.obtenerMarcas();
        if(marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(marcas);
        }
    }
    @PostMapping("/importar")
    public String importarArchivos(@RequestParam("archivos") MultipartFile file, RedirectAttributes ms) {
        String nombreArchivo= file.getOriginalFilename();
        if (Objects.equals(nombreArchivo, "DATOS.txt") || Objects.equals(nombreArchivo, "DATA.txt") ) {
            relojService.lectura(relojService.save(file));
            ms.addFlashAttribute("mensaje", "Archivo importado correctamente");
        }
        else{
            ms.addFlashAttribute("mensaje", "El archivo ingresado no puede ser importado");
        }
        return "redirect:/";
    }
}