package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Viagem {
    private int codigo;
    private Onibus onibus;
    private Motorista motorista;
    private String hora_saida;
    private String hora_chegada;
    private String partida;
    private String destino;
}
