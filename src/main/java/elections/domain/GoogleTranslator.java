package elections.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleTranslator implements Serializable, Cloneable {
    private String text;
    private String target;
    private String source;
}
