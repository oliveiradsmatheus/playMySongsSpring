package proj.playmysongsbe.entities;

public class Musica {
    private String titulo, artista, estilo, arquivo;

    public Musica(String titulo, String artista, String estilo, String arquivo) {
        this.titulo = titulo;
        this.artista = artista;
        this.estilo = estilo;
        this.arquivo = arquivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
