package net.weg.mi75.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor // Construtor com todos os parâmetros
@NoArgsConstructor // Construtor sem parâmetros
@Data // Data para criar todos os gets e sets e tbm um toString padrão
@Entity
@Table(name = "tb_transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private Integer remetente;
    @NonNull
    private Integer destinatario;
    @NonNull
    private Double valor;
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