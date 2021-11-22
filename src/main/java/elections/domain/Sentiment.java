package elections.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sentiment implements Cloneable, Serializable {
    private Float neutral;
    private Float positive;
    private Float negative;
}
