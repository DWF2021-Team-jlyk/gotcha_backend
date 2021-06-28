package com.gotcha.www.card.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    private int card_id;
    private String card_name;
    private String card_desc;
    private String card_start_date;
    private String card_end_date;
    private int list_id;
    private int ws_id;
    private int card_isdone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return card_id == cardDTO.card_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_id);
    }
}
