package cliente.com.bantads.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable{
    private String mensagem;
    private Object data;
    private boolean erro;
}
