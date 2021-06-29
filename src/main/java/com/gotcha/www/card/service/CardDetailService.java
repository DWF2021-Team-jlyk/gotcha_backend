package com.gotcha.www.card.service;

import com.gotcha.www.card.vo.CardDetailVO;

import java.util.Optional;

public interface CardDetailService {
    CardDetailVO getCardInfo(int card_id);
}
