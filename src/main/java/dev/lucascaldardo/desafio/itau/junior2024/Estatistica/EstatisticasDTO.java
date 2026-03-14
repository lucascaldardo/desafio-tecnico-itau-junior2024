package dev.lucascaldardo.desafio.itau.junior2024.Estatistica;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EstatisticasDTO {

    private final long count;
    private final double avg;
    private final double max;
    private final double min;
    private final double sum;

    public EstatisticasDTO(long count, double avg, double max, double min, double sum) {

        this.count = count;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.sum = sum;
    }
}
