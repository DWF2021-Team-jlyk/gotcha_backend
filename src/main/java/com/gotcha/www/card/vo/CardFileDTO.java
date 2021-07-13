package com.gotcha.www.card.vo;

import java.nio.file.Path;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardFileDTO {
    private int card_id;
    private int file_id;
    private String file_name;
    private char file_ischecked;
    private String file_path;
}
