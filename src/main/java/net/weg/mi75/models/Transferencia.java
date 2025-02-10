package net.weg.mi75.models;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor // Construtor com todos os parâmetros
@NoArgsConstructor // Construtor sem parâmetros
@Data // Data para criar todos os gets e sets e tbm um toString padrão
public class Transferencia {
    @NonNull
    private int remetente;
    @NonNull
    private int destinatario;
    @NonNull
    private double valor;
    @NonNull
    private Timestamp data;

    @Override
    public String toString() {
        return "\nTransferencia:" +
                "\nRemetente: " + remetente +
                "\nDestinatario: " + destinatario +
                "\nValor: " + valor +
                "\nData: " + data +
                "\n";
    }

}