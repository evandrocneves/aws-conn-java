package br.com.awsSqs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sentiment implements Cloneable, Serializable {
    private String text;
    private Float neutral;
    private Float positive;
    private Float negative;
}
