package com.adjt.acompanhamentoservice.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UnidadeMedida {

    // Frequência Cardíaca
    BPM("bpm", "Batimentos por minuto", "Frequência Cardíaca"),

    // Pressão Arterial
    MMHG("mmHg", "Milímetros de mercúrio", "Pressão Arterial"),

    // Frequência Respiratória
    IRPM("irpm", "Incursões respiratórias por minuto", "Frequência Respiratória"),

    // Temperatura
    CELSIUS("°C", "Graus Celsius", "Temperatura"),
    FAHRENHEIT("°F", "Graus Fahrenheit", "Temperatura"),

    // Oximetria (SpO2)
    PORCENTAGEM("%", "Porcentagem", "Saturação de Oxigênio"),

    // Glicemia
    MG_DL("mg/dL", "Miligramas por decilitro", "Glicemia"),
    MMOL_L("mmol/L", "Milimoles por litro", "Glicemia"),

    // Medidas Antropométricas (Opcionais, mas comuns no mesmo prontuário)
    KG("kg", "Quilogramas", "Peso"),
    CM("cm", "Centímetros", "Altura"),

    // Escala de Dor (Geralmente de 0 a 10, não tem unidade física, mas é um "sinal vital")
    ESCALA("Escala", "Escala Numérica (0-10)", "Dor"),

    OUTROS("Outros", "Outra unidade de medida", "Diversos");

    private final String simbolo;
    private final String descricao;
    private final String tipoMedicao;

    UnidadeMedida(String simbolo, String descricao, String tipoMedicao) {
        this.simbolo = simbolo;
        this.descricao = descricao;
        this.tipoMedicao = tipoMedicao;
    }

    /**
     * Método utilitário para buscar a unidade pelo símbolo (ex: "mmHg" -> MMHG)
     */
    public static UnidadeMedida fromSimbolo(String simbolo) {
        return Arrays.stream(values())
                .filter(unidade -> unidade.getSimbolo().equalsIgnoreCase(simbolo)
                        || unidade.name().equalsIgnoreCase(simbolo)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Símbolo de unidade de medida não encontrado: " + simbolo));
    }
}
