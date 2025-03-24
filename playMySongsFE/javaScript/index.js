function buscarMusicas() {
    const resultado = document.getElementById("resultado");
    const URL = "http://localhost:8080/apis/retornar_musicas";
    const caminhoUpload = "http://localhost:8080/uploads/";
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
    event.preventDefault();
    event.stopPropagation();
    let busca = document.getElementById("busca")
    let erro = false;
    let nomeArquivo;
    fetch(URL, requestOptions)
        .then((response) => {
            if (!response.ok)
                erro = true;
            return response.json()
        })
        .then((result) => {
            if (erro)
                resultado.innerHTML = result.mensagem;
            else {
                let html = "";
                result.forEach(element => {
                    nomeArquivo = element.arquivo.toLowerCase();
                    if (nomeArquivo.includes(busca.value.toLowerCase())) {
                        html += '<audio controls>';
                        html += '<source src="' + element.arquivo + '" type="audio/mpeg">';
                        html += '</audio>';
                        html += '<p class="musica">';
                        html += element.arquivo.substring(caminhoUpload.length, element.arquivo.length);
                        html += '<p>';
                    }
                });
                resultado.innerHTML = html;
            }
        })
        .catch((error) => resultado.innerHTML = error);
}