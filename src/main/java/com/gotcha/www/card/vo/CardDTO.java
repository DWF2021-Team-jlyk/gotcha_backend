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
    int card_id;
    String card_name;
    String card_desc;
    String card_end_date;
    String card_start_date;
    int list_id;
    int ws_id;
    int card_isdone;

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
