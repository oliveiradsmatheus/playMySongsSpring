package proj.playmysongsbe.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import proj.playmysongsbe.entities.Erro;
import proj.playmysongsbe.entities.Musica;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class MusicRestController {
    @Autowired
    private HttpServletRequest request;
    final String PASTA_UPLOAD = "src/main/resources/static/uploads/";

    @PostMapping(value = "adicionar_musica")
    public ResponseEntity<Object> addUsuario(@RequestParam("file") MultipartFile file,
                                             @RequestParam("titulo") String titulo,
                                             @RequestParam("artista") String artista,
                                             @RequestParam("estilo") String estilo) {
        String nomeArq;
        if(!file.isEmpty()) {
            try {
                titulo = titulo.replaceAll(" ", "");
                artista = artista.replaceAll(" ", "");
                estilo = estilo.replaceAll(" ", "");
                nomeArq = titulo + "_" + estilo + "_" + artista + ".mp3";
                // Cria uma pasta na área estática para acomodar os arquivos recebidos, caso não exista.
                File pastaUpload = new File(PASTA_UPLOAD);
                if (!pastaUpload.exists())
                    pastaUpload.mkdir();
                // Criar um nome para o arquivo com a regra da atividade.
                // Utilizar o nome no lugar do nome original.
                file.transferTo(new File(pastaUpload.getAbsolutePath() + "/" + nomeArq));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new Erro("Erro ao armazenar o arquivo. " + e.getMessage()));
            }
            return ResponseEntity.ok(new Musica(titulo, artista, estilo, nomeArq));
        } else
            return ResponseEntity.badRequest().body(new Erro("Nenhum arquivo enviado."));
    }

    @GetMapping(value = "retornar_musicas")
    public ResponseEntity<Object> retornarMusicas() {
        File pastaUpload = new File(PASTA_UPLOAD);
        String[] musicas = pastaUpload.list();
        List<Musica> listaMusicas = new ArrayList<>();
        for (String musica : musicas) {
            listaMusicas.add(new Musica("titulo", "artista", "estilo", getHostStatic() + musica));
        }
        if (!listaMusicas.isEmpty())
            return ResponseEntity.ok(listaMusicas);
        return ResponseEntity.badRequest().body(new Erro("Nenhuma música encontrada."));
    }

    public String getHostStatic() {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + "/uploads/";
    }
}