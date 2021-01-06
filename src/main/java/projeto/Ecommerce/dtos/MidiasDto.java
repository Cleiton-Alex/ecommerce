package projeto.Ecommerce.dtos;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class MidiasDto {

    private Optional<Long> id = Optional.empty();
    private String nome;
    private String url;
    private Long duracao;
    private String dataUpload;
    private String deletadoMidias;


    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }

    @NotEmpty(message = "Nome não pode ser vazio. ")
    @Length(min = 1, max = 512, message = "Nome deve conter entre 1 e 512 caracteres.")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    @NotEmpty(message = "Data de upload não pode ser vazia. ")
    public String getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(String dataUpload) {
        this.dataUpload = dataUpload;
    }

    @NotEmpty(message = "Deleted midias não pode ser vazio.")
    public String getDeletadoMidias() {
        return deletadoMidias;
    }

    public void setDeletadoMidias(String deletadoMidias) {
        this.deletadoMidias = deletadoMidias;
    }

    @Override
    public String toString() {
        return "MidiasDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", url='" + url + '\'' +
                ", duracao=" + duracao +
                ", dataUpload='" + dataUpload + '\'' +
                ", deletadoMidias='" + deletadoMidias + '\'' +
                '}';
    }
}
