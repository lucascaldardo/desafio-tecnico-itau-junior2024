package dev.lucascaldardo.desafio.itau.junior2024.Transacoes;

import lombok.Getter;

@Getter
public class EstatisticaDTO {

    private final long count;
    private final double avg;
    private final double max;
    private final double min;
    private final double sum;

    public EstatisticaDTO(long count, double avg, double max, double min, double sum) {
        this.count = count;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.sum = sum;
    }
}
