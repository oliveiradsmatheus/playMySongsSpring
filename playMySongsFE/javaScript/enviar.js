const envio = document.getElementById("file");
const nomeArq = document.getElementById("nomearquivo");
const tam = document.getElementById("tam");

function validarEnvio() {
    let nome = document.getElementById("titulo").value.replaceAll(" ", "").toLowerCase();
    let artista = document.getElementById("artista").value.replaceAll(" ", "").toLowerCase();
    let i = 0;

    if (nomeArq.innerHTML.endsWith(".mp3")) {
        while (i < nome.length && ((nome[i] >= 'a' && nome[i] <= 'z')))
            i++;
        if (i === nome.length) {
            i = 0;
            while (i < artista.length && ((artista[i] >= 'a' && artista[i] <= 'z')))
                i++;
            if (i === artista.length) {
                enviarArquivo();
            } else
                document.getElementById("mensagem").innerHTML = "Nome do Artista Inválido!";
        } else
            document.getElementById("mensagem").innerHTML = "Nome da Música Inválido!";
    } else
        document.getElementById("mensagem").innerHTML = "Formato de arquivo não é .mp3!";
}

function enviarArquivo() {
    const URL = "http://localhost:8080/apis/adicionar_musica";
    let formData = new FormData(document.getElementById("fform"));
    const mensagem = document.getElementById("mensagem");
    const requestOptions = {
        method: "POST",
        body: formData,
        redirect: "follow"
    };

    fetch(URL, requestOptions)
        .then(response => {
            return response.json();
        })
        .then(dados => {
            mensagem.innerHTML = "Arquivo " + '"' + dados.arquivo + '"' + " enviado!";
        })
        .catch(error => mensagem.innerHTML = error);
}

envio.addEventListener("change", () => {
    if (envio.files.length > 0) {
        const file = envio.files[0];
        nomeArq.textContent = `Nome do arquivo: ${file.name}`;
        tam.textContent = `Tamanho: ${Math.floor(file.size / 1024)} KB`;
    } else {
        nomeArq.textContent = "Nenhum arquivo selecionado";
        tam.textContent = "";
    }
});